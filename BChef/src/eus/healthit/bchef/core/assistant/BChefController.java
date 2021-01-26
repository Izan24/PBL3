package eus.healthit.bchef.core.assistant;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import eus.healthit.bchef.core.api.API;
import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.assistant.implementations.BoardController;
import eus.healthit.bchef.core.assistant.implementations.KitchenController;
import eus.healthit.bchef.core.assistant.implementations.OutputController;
import eus.healthit.bchef.core.assistant.implementations.RecipeAssistantController;
import eus.healthit.bchef.core.assistant.implementations.input.AudioInputController;
import eus.healthit.bchef.core.assistant.interfaces.IBoardController;
import eus.healthit.bchef.core.assistant.interfaces.IKitchenController;
import eus.healthit.bchef.core.assistant.interfaces.IOutputController;
import eus.healthit.bchef.core.assistant.interfaces.IRecipeAssistantController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.enums.VoiceCommand;
import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.models.KitchenAlarm;
import eus.healthit.bchef.core.models.Oven;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.Stove;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.util.StringParser;
import eus.healthit.bchef.core.util.TextBuilder;
import eus.healthit.bchef.core.util.func.FunctionCall;
import eus.healthit.bchef.core.util.func.KitchenSwitchCall;
import eus.healthit.bchef.core.util.func.NewAlarmCall;
import eus.healthit.bchef.core.util.func.NextRecipeCall;

public class BChefController implements PropertyChangeListener {

	PropertyChangeSupport connector;
	User user;

	private static BChefController instance = new BChefController();

	private BChefController() {
		outputController = OutputController.getInstance();
		recipeAssitantController = new RecipeAssistantController();
		kitchenController = new KitchenController();
		connector = new PropertyChangeSupport(this);
		inputController = AudioInputController.getInstance();
		commandController = CommandController.getInstance();
		boardController = new BoardController();
		boardController.addPropertyChangeListener(this);
		boardController.updateKitchen(kitchenController.getKitchen());
	}

	public static BChefController getInstance() {
		return instance;
	}

	IBoardController boardController;
	AudioInputController inputController;
	IOutputController outputController;
	IKitchenController kitchenController;
	CommandController commandController;
	IRecipeAssistantController recipeAssitantController;

	List<Recipe> searchedRecipes;
	int searchedRecipesIndex;
	int searchedRecipesPage;
	FunctionCall nextFunction;
	Set<String> lastSearchIngredients;
	String lastSearch;

	public void switchKitchen(KitchenUtil util, Integer index, Integer value) {
		switch (util) {
		case OVEN:
			if (value == null) {
				errorMessage("MISSUNDERSTOOD");
				return;
			}
			if (index == null)
				kitchenController.setOven(0, value);
			else
				kitchenController.setOven(index, value);
			break;
		case STOVE:
			if (value == null) {
				errorMessage("MISSUNDERSTOOD");
				break;
			}
			if (index == null)
				kitchenController.setStove(0, value);
			else
				kitchenController.setStove(index, value);
			break;
		case MISUNDERSTOOD:
		default:
			errorMessage("MISSUNDERSTOOD");
			return;
		}

		OutputController.getInstance().send(TextBuilder.kitchenSwitchMessage(util, value, index));
	}

	public void nextStep() {
		System.out.println("next ->>>>>>>>>>>>>>>>> step");
		if (recipeAssitantController.getRecipe() == null) {
			errorMessage("MISSING_RECIPE");
			return;
		}

		RecipeStep nextStep = recipeAssitantController.nextStep();
		if (nextStep == null) {
			if (recipeAssitantController.isFinished()) {
				connector.firePropertyChange("FINISH_RECIPE", null, null);
			}
			errorMessage("MISSING_NEXTSTEP");
			return;
		}
		connector.firePropertyChange("UPDATE_STEP", null, nextStep);
		outputController.send(nextStep.getText());
		checkStepAlarm(nextStep);
	}

	public void checkStepAlarm(RecipeStep step) {
		Duration stepDuration = step.getDuration();
		if (stepDuration != null && stepDuration != Duration.ZERO) {
			switch (step.getAction()) {
			case OVEN:
				System.out.println("ALARM IS NEW OVEN");
				nextFunction = new NewAlarmCall(KitchenUtil.OVEN, null, step.getTime());
				outputController.send(TextBuilder.askAlarmMessage(stepDuration, KitchenUtil.OVEN, step.getValue()));
				break;
			case STOVE:
				System.out.println("ALARM IS NEW STOVE");
				nextFunction = new NewAlarmCall(KitchenUtil.STOVE, null, step.getTime());
				outputController.send(TextBuilder.askAlarmMessage(stepDuration, KitchenUtil.STOVE, step.getValue()));
				break;
			case TIMER:
				System.out.println("ALARM IS NEW TIMER");
				nextFunction = new NewAlarmCall(null, null, step.getTime());
				outputController.send(TextBuilder.askAlarmMessage(stepDuration, null, null));
				break;
			default:
				break;
			}
		} else {
			int value = step.getValue();
			switch (step.getAction()) {
			case OVEN:
				outputController.send(TextBuilder.askKitchenSwitch(KitchenUtil.OVEN, value));
				nextFunction = new KitchenSwitchCall(KitchenUtil.OVEN, null, value);
				break;
			case STOVE:
				outputController.send(TextBuilder.askKitchenSwitch(KitchenUtil.STOVE, value));
				nextFunction = new KitchenSwitchCall(KitchenUtil.STOVE, null, value);
				break;
			default:
				break;
			}
		}
	}

	public RecipeStep getCurrentStep() {
		return recipeAssitantController.getCurrentStep();
	}

	public void prevStep() {
		if (recipeAssitantController.getRecipe() == null) {
			errorMessage("MISSING_RECIPE");
			return;
		}

		RecipeStep prevStep = recipeAssitantController.prevStep();
		if (prevStep == null) {
			errorMessage("MISSING_PREVSTEP");
			return;
		}
		connector.firePropertyChange("UPDATE_STEP", null, prevStep);
	}

	public void startRecipe(Recipe recipe) {
		connector.firePropertyChange("START_RECIPE", null, recipe);
		recipeAssitantController.setRecipe(recipe);
		RecipeStep firstStep = recipeAssitantController.getCurrentStep();
		outputController.send(firstStep.toString());
		checkStepAlarm(firstStep);
		lastSearch = null;
		lastSearchIngredients = null;
		searchedRecipesIndex = 0;
		searchedRecipesPage = 0;
	}

	public void startSearchOffer() {
		searchedRecipesIndex = 0;
		if (searchedRecipes.size() == 0)
			errorMessage("RECIPES_NOT_FOUND");
		else {
			nextRecipe();
			nextFunction = new NextRecipeCall();
		}
	}

	public void searchRecipe(String string) {
		OutputController.getInstance().send(TextBuilder.findingRecipeMessage(string));
		searchedRecipes = JSONCalls.search(string, searchedRecipesPage);
		lastSearch = string;
		lastSearchIngredients = null;
		startSearchOffer();
	}

	public void searchRecipeByIngredient(Set<String> ingredients) {
		outputController.send(TextBuilder.findingRecipeByIngredientMessage(ingredients));
		searchedRecipes = JSONCalls.searchByIngredient(ingredients);
		lastSearchIngredients = ingredients;
		lastSearch = null;
		startSearchOffer();
	}

	public void nextRecipe() {
		if (searchedRecipesIndex < searchedRecipes.size()) {
			outputController.send(TextBuilder.recipeFoundMessage(searchedRecipes.get(searchedRecipesIndex)));
		} else {
			searchedRecipesPage++;
			if (lastSearch != null) {
				searchRecipe(lastSearch);
			} else if (lastSearchIngredients != null) {
				searchRecipe(lastSearch);
			}
			searchedRecipesIndex = 0;
		}
		if (searchedRecipes == null || searchedRecipes.size() == 0)
			errorMessage("NO_MORE_RECIPES");
	}

	public void confirmCall() {
		if (nextFunction != null)
			switch (nextFunction.getId()) {
			case NextRecipeCall.ID_STRING:
				startRecipe(searchedRecipes.get(searchedRecipesIndex));
				nextFunction = null;
				break;
			default:
				nextFunction.executeCall();
				nextFunction = null;
				break;
			}
		else
			errorMessage("MISSUNDERSTOOD");
	}

	public void cancellCall() {
		if (nextFunction != null) {
			switch (nextFunction.getId()) {
			case NextRecipeCall.ID_STRING:
				searchedRecipesIndex++;
				nextFunction.executeCall();
				break;
			default:
				nextFunction = null;
				break;
			}
		} else
			errorMessage("MISSUNDERSTOOD");
	}

	public void errorMessage(String reason) {
		outputController.send(TextBuilder.errorMessage(reason));
	}

	public void setAlarm(KitchenUtil util, Integer index, Duration time) {
		KitchenAlarm alarm = recipeAssitantController.setAlarm(util, index, time);
		outputController.send(TextBuilder.newAlarmMessage(time, util, index));
		connector.firePropertyChange("ALARM_NEW", null, alarm);
	}

	public void processNewCommand(String string) {
		VoiceCommand command = StringParser.parseCommand(string);
		commandController.selectCommand(command, string);
	}

	public void addToList(String string) {
		Item item = new Item(string);
		connector.firePropertyChange("LIST_ADD", null, item);
		JSONCalls.shoplistAdd(item, user.getId());
		OutputController.getInstance().send(TextBuilder.addedToList(string));
	}

	public void deleteFromList(String string) {
		String reg = "\\s*\\b" + string.toLowerCase() + "\\b\\s*";
		Pattern pattern = Pattern.compile(reg);
		Item deletedItem = null;
		for (Item item : user.getShopList()) {
			Matcher matcher = pattern.matcher(item.getName().toLowerCase());
			if (matcher.find()) {
				connector.firePropertyChange("LIST_REMOVE", null, item);
				JSONCalls.shoplistRemove(item);
				deletedItem = item;
			}
		}

		if (deletedItem == null)
			outputController.send(TextBuilder.listItemNotFound(string));
		else
			OutputController.getInstance().send(TextBuilder.removedFromList(deletedItem.getName()));
	}

	public void readList() {
		List<String> complete = user.getShopList().stream().filter(o -> !o.isBought()).map(x -> x.getName())
				.collect(Collectors.toList());
		List<String> incomplete = user.getShopList().stream().filter(o -> o.isBought()).map(x -> x.getName())
				.collect(Collectors.toList());

		outputController.send(TextBuilder.readListMessage(complete, incomplete));
	}

	public void startVoiceRecon() {
		if (!inputController.isAlive())
			inputController.start();
		inputController.startRecon();
	}

	public void stopVoiceRecon() {
		if (inputController.isAlive())
			inputController.stopRecon();
	}

	public void resumeVoiceRecon() {
		inputController.startRecon();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "NEW_COMMAND":
			processNewCommand((String) evt.getNewValue());
			break;
		case "ALARM_FINISH":
			outputController.soundAlarm();
			KitchenAlarm alarm = (KitchenAlarm) evt.getNewValue();
			if (alarm.getUtil() != null) {
				KitchenUtil util = alarm.getUtil();
				Integer index = alarm.getUtilIndex();
				outputController.send(TextBuilder.alarmDeactivateMessage(util, index));
				nextFunction = new KitchenSwitchCall(util, index, 0);
			}
		case "ALARM_UPDATE":
			connector.firePropertyChange(evt);
			break;
		case "UPDATE_OVENS":
			kitchenController.setOvens((List<Oven>) evt.getNewValue());
			break;
		case "UPDATE_STOVES":
			kitchenController.setStoves((List<Stove>) evt.getNewValue());
			break;
		case "KITCHEN_UPDATE":
			boardController.updateKitchen(kitchenController.getKitchen());
			break;
		default:
			break;
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		connector.addPropertyChangeListener(listener);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void lumbra() {
		outputController.send(API.lumbra());
	}

	public void sayTime() {
		outputController.send(TextBuilder.timeMessage(LocalTime.now()));
	}

	public void sayWelcome() {
		outputController.send(TextBuilder.thankMessage());
	}
}
