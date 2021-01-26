package eus.healthit.bchef.core.assistant;

import java.time.Duration;
import java.util.Set;

import com.google.common.base.Enums;

import eus.healthit.bchef.core.assistant.implementations.OutputController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.enums.VoiceCommand;
import eus.healthit.bchef.core.util.StringParser;

public class CommandController {

	private static CommandController instance = new CommandController();

	// private BChefController BChefController.getInstance();

	// Variables para guardar el comando anterior

	private CommandController() {
		// BChefController.getInstance() = BChefController.getInstance();
	}

	public static CommandController getInstance() {
		return instance;
	}

	public boolean selectCommand(VoiceCommand command, String string) {
		System.out.println("command: " + command);
		// BChefController.getInstance().stopVoiceRecon();
		switch (command) {
		case SEARCH_INGREDIENT:
			searchIngredient(StringParser.deleteCommandWords(string, command));
			break;
		case SEARCH_RECIPE:
			// TODO: Queryes PETA
			BChefController.getInstance().searchRecipe(StringParser.deleteCommandWords(string, command));
			break;
		case SWITCH_KITCHEN:
			// TODO: POWER OFF DIFERENTE PQ SIEMPRE ES VALUE 0
		case POWER_OFF:
			switchKitchen(string);
			break;
		case RECIPE_PREVIOUS:
			BChefController.getInstance().prevStep();
			break;
		case RECIPE_NEXT:
			BChefController.getInstance().nextStep();
			System.out.println("SWITCH");
			break;
		case ALARM:
			setAlarm(string);
			break;
		case LIST_ADD:
			// TODO
			String txtString = "";
			try {
				txtString = StringParser.deleteCommandWords(string, command);
			} catch (ArrayIndexOutOfBoundsException e) {
				BChefController.getInstance().errorMessage("MISSUNDERSTOOD");
			}
			System.out.println(txtString);
			BChefController.getInstance().addToList(txtString);
			break;
		case LIST_REMOVE:
			// TODO
			try {
				BChefController.getInstance().deleteFromList(StringParser.deleteCommandWords(string, command));
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case LIST_READ:
			BChefController.getInstance().readList();
			break;
		case YES:
			BChefController.getInstance().confirmCall();
			break;
		case NO:
			BChefController.getInstance().cancellCall();
			break;
		case TIME:
			BChefController.getInstance().sayTime();
			break;
		case THANKS:
			BChefController.getInstance().sayWelcome();
			break;
		case LUMBRA:
			BChefController.getInstance().lumbra();
			break;
		case NUMBER:
			// En desuso
		default:
			BChefController.getInstance().errorMessage("MISSUNDERSTOOD");
			break;
		}
//		BChefController.getInstance().resumeVoiceRecon();
		return true;
	}

	private void searchIngredient(String string) {
		Set<String> ingredients = StringParser.parseIngredients(string);
		BChefController.getInstance().searchRecipeByIngredient(ingredients);
		System.out.println("Ingres: ");
		ingredients.stream().forEach(System.out::println);
	}

	private void switchKitchen(String string) {
		KitchenUtil util = StringParser.parseKitchenUtil(string);
		Integer value = null;
		Integer index = null;
		if (util != KitchenUtil.MISUNDERSTOOD) {
			Integer[] nums = StringParser.parseInt(string);
			for (int i : nums)
			if (nums.length == 0) {
				BChefController.getInstance().errorMessage("MISSUNDERSTOOD");
				return;
			}
			if (nums.length == 1)
				value = nums[0];
			else {
				index = nums[0] - 1;
				value = nums[1];
			}
			BChefController.getInstance().switchKitchen(util, index, value);
		} else
			BChefController.getInstance().errorMessage("MISSUNDERSTOOD");
	}

	private void setAlarm(String string) {
		KitchenUtil util = StringParser.parseKitchenUtil(string);
		int index = 0;
		if (util.equals(KitchenUtil.MISUNDERSTOOD)) {
			util = null;
			index = -1;
		}

		Duration time = StringParser.parseTime(string);
		if (time.isZero() || time.isNegative()) {
			BChefController.getInstance().errorMessage("INVALID_TIME");
			return;
		}

		BChefController.getInstance().setAlarm(util, index, time);
	}

}
