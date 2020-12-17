package eus.healthit.bchef.core.controllers;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import eus.healthit.bchef.core.Configuration;
import eus.healthit.bchef.core.enums.VoiceCommand;

public class CommandController {

	private static final String[] PREPOSICIONES = {"a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "durante", "en", "entre", "hacia", "hasta", "mediante", "para", "por", "segun", "sin", "so", "sobre", "tras", "versus", "via"};
	private static final String[] ARTICULOS = {"el", "la", "los", "las", "un", "uno", "una", "unos", "unas"};
	private static final String[] PALABRAS = {"receta", "cocina", "y", "e", "recetas", "algo", ",", "poco", "mucho"};
	
	
	BChefController controller;
	
	public CommandController(BChefController controller) {
		this.controller = controller;
	}
	public CommandController() {
		
	}
	
	public static VoiceCommand parseCommand(StringBuilder command) {
		Collator c = Collator.getInstance(new Locale(Configuration.getLocale()));
		c.setStrength(Collator.SECONDARY);
		for (VoiceCommand cmd : VoiceCommand.values()) {
			for (String str : cmd.getCommands()) {
				str = str.toLowerCase();
				Pattern p = Pattern.compile("\\b" + str + "\\b", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(command);
				if (m.find()) {
					if(str.equals("con")) {
						int index1 = command.indexOf("con");
						command.delete(index1, index1 + "con".length() + 1);
						return parseCommand(command);
					}
					int index = command.indexOf(str);
					command.delete(0, index + str.length() + 1);
					
					return cmd;
				}
			}
		}

		try {
			System.out.println(parseInt(command.toString()));
			return VoiceCommand.NUMBER;
		} catch (Exception e) {
			return VoiceCommand.MISUNDERSTOOD;
		}
	}

	public static int parseInt(String string) throws NumberFormatException, IllegalStateException {
		Matcher matcher = Pattern.compile("\\d+").matcher(string);
		matcher.find();
		return Integer.valueOf(matcher.group());
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
			break;
		case RECIPE_PREVIOUS:
			break;
		case RECIPE_NEXT:
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
		Set<String> ingredients = parseIngredients(string);
		ingredients.stream().forEach(System.out::println);
		return true;
	}
	
	private Set<String> parseIngredients(String string) {
		String numberless = string.replace("\\d", "");
		Set<String> ingredients = Arrays.stream(numberless.split(" +")).collect(Collectors.toSet());
		ingredients.removeIf(s -> Arrays.stream(PREPOSICIONES).anyMatch(m -> m.equals(s)));
		//ingredients.stream().forEach(System.out::println);
		ingredients.removeIf(s -> Arrays.stream(ARTICULOS).anyMatch(m -> m.equals(s)));
		ingredients.removeIf(s -> Arrays.stream(PALABRAS).anyMatch(m -> m.equals(s)));
		return ingredients;
	}

}
