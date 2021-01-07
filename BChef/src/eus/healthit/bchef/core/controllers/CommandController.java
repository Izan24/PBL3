package eus.healthit.bchef.core.controllers;

import java.util.List;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import eus.healthit.bchef.core.Configuration;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.enums.VoiceCommand;

public class CommandController {
	
	BChefController controller;
	
	private static CommandController obj = new CommandController();
	
	private CommandController() {
		controller = BChefController.getBChefController();
	}
	
	public static CommandController getCommandController() {
		return obj;
	}

	private static final String[] PREPOSICIONES = {"a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "durante", "en", "entre", "hacia", "hasta", "mediante", "para", "por", "segun", "sin", "so", "sobre", "tras", "versus", "via"};
	private static final String[] ARTICULOS = {"el", "la", "los", "las", "un", "uno", "una", "unos", "unas"};
	private static final String[] PALABRAS = {"receta", "cocina", "y", "e", "recetas", "algo", ",", "poco", "mucho"};
	
	
	public static VoiceCommand parseCommand(String command) {
		Collator c = Collator.getInstance(new Locale(Configuration.getLocale()));
		c.setStrength(Collator.SECONDARY);
		for (VoiceCommand cmd : VoiceCommand.values()) {
			for (String str : cmd.getCommands()) {
				str = str.toLowerCase();
				Pattern p = Pattern.compile("\\b" + str + "\\b", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(command);
				if (m.find()) {				
					return cmd;
				}
			}
		}

		try {
			return VoiceCommand.NUMBER;
		} catch (Exception e) {
			return VoiceCommand.MISUNDERSTOOD;
		}
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
			searchIngredient(string);
			break;
		case SWITCH_KITCHEN:
			switchKitchen(string);
			break;
		case RECIPE_PREVIOUS:
			controller.previousStep();
			break;
		case RECIPE_NEXT:
			controller.nextStep();
			break;
		case ALARM:
			break;
		case LIST_ADD:
			break;
		case LIST_REMOVE:
			break;
		case POWER_OFF:
			break;
		case YES:
			break;
		case NO:
			break;
		case NUMBER:
			break;
		case MISUNDERSTOOD:
			controller.notifyMisunderstood();
			break;
		default:

			break;
		}

		return true;
	}
	
	private boolean searchIngredient(String string) {
		Set<String> ingredients = parseWords(string);
		return true;
	}
	
	private static Set<String> parseWords(String string) {
		String numberless = string.replace("\\d", "");
		Set<String> ingredients = Arrays.stream(numberless.split(" +")).collect(Collectors.toSet());
		ingredients.removeIf(s -> Arrays.stream(PREPOSICIONES).anyMatch(m -> m.equals(s)));
		ingredients.removeIf(s -> Arrays.stream(ARTICULOS).anyMatch(m -> m.equals(s)));
		ingredients.removeIf(s -> Arrays.stream(PALABRAS).anyMatch(m -> m.equals(s)));
		return ingredients;
	}
	
	private void switchKitchen(String string) {
		KitchenUtil util = getKitchenUtil(parseWords(string));
		if(util != KitchenUtil.MISUNDERSTOOD) {
			try {
				Integer[] nums = parseInt(string);
				controller.switchKitchen(util, nums);
			} catch (Exception e) {
				//TODO: preguntar numero
			}
		}
		else controller.notifyMisunderstood();
	}
	
	private KitchenUtil getKitchenUtil(Set<String> words) {
		for(KitchenUtil util : KitchenUtil.values()) {
			for(String keyword : util.getKeywords()) {
				if(words.contains(keyword)) return util;
			}
		}
		
		return KitchenUtil.MISUNDERSTOOD;
		
	}

}
