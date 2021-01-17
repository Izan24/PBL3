package eus.healthit.bchef.core.controllers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eus.healthit.bchef.core.controllers.implementations.OutputController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.enums.VoiceCommand;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.util.StringParser;

public class CommandController {
	
	private static CommandController instance = new CommandController();
	
	private BChefController bChefController;
	
	//Variables para guardar el comando anterior
	boolean expectingConfirmation;
	boolean expectingNumber;
	int searchIndex;
	List<Recipe> foundRecipes;
	
	private CommandController() {
		bChefController = BChefController.getInstance();
	}
	
	public static CommandController getInstance() {
		return instance;
	}
	
	public static Integer [] parseInt(String string) throws NumberFormatException, IllegalStateException {
		Matcher matcher = Pattern.compile("\\d+").matcher(string);
		List<Integer> numsList = new ArrayList<>();
		while(matcher.find()) {
			numsList.add(Integer.valueOf(matcher.group()));
		}
		return numsList.toArray(new Integer [0]);
	}

	public boolean selectCommand(VoiceCommand command, String string) {
		System.out.println("command: " + command);
		switch (command) {
		case SEARCH_INGREDIENT:
			searchIngredient(string);
			break;
		case SEARCH_RECIPE:
			//TODO: Queryes
			OutputController.getInstance().send("Buscando receta de: " + string);
			break;
		case SWITCH_KITCHEN:
		case POWER_OFF:
			switchKitchen(string);
			break;
		case RECIPE_PREVIOUS:
			bChefController.prevStep();
			break;
		case RECIPE_NEXT:
			bChefController.nextStep();
			break;
		case ALARM:
			setAlarm(string);
			break;
		case LIST_ADD:
			OutputController.getInstance().send("He añadido " + string + " a la lista.");
			break;
		case LIST_REMOVE:
			OutputController.getInstance().send("He quitado " + string + " de la lista.");
			break;
		case YES:
			bChefController.confirmCall();
			break;
		case NO:
			bChefController.cancellCall();
			break;
		case NUMBER:
			if(expectingNumber) {
				
				expectingNumber = false;
			}
			else bChefController.errorMessage("MISSUNDERSTOOD");
			break;
		default:
			bChefController.errorMessage("MISSUNDERSTOOD");
			break;
		}
		return true;
	}
	

	private boolean searchIngredient(String string) {
		Set<String> ingredients = StringParser.parseIngredients(string);
		OutputController outputController = OutputController.getInstance();
		outputController.send("Buscando recetas con: " + String.join(", ", ingredients));
		System.out.println("Ingres: ");
		ingredients.stream().forEach(System.out::println);
		return true;
	}
	
	private void switchKitchen(String string) {
		KitchenUtil util = StringParser.parseKitchenUtil(string);
		Integer value = null;
		Integer index = null;
		if(util != KitchenUtil.MISUNDERSTOOD) {
			Integer[] nums = parseInt(string);
			if(nums.length == 0) {
				bChefController.errorMessage("MISSUNDERSTOOD");
				return;
			}
			if(nums.length == 1) value = nums[0];
			else {
				index = nums[0];
				value = nums[1];
			}
			bChefController.switchKitchen(util, index, value);
			OutputController.getInstance().send("He puesto " + util.getKeywords()[0] + " a " + nums[0]);

		}
		else bChefController.errorMessage("MISSUNDERSTOOD");
	}
	
	private void setAlarm(String string) {
		KitchenUtil util = StringParser.parseKitchenUtil(string);
		int index = 0;
		if(util.equals(KitchenUtil.MISUNDERSTOOD)) {
			util = null;
			index = -1;
		}
		
		Duration time = StringParser.parseTime(string);
		if(time.isZero() || time.isNegative()) {
			bChefController.errorMessage("INVALID_TIME");
			return;
		}
		
		bChefController.setAlarm(util, index, time);	
	}
		
}
