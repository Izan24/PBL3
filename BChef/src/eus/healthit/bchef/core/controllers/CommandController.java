package eus.healthit.bchef.core.controllers;

import java.text.Collator;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import eus.healthit.bchef.core.Configuration;
import eus.healthit.bchef.core.controllers.implementations.OutputController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.enums.VoiceCommand;

public class CommandController {
	
	private static CommandController obj = new CommandController();
	
	private BChefController bChefController;
	
	private CommandController() {
		bChefController = BChefController.getBChefController();
	}
	
	public static CommandController getCommandController() {
		return obj;
	}

	private static final String[] PREPOSICIONES = {"a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "durante", "en", "entre", "hacia", "hasta", "mediante", "para", "por", "segun", "sin", "so", "sobre", "tras", "versus", "via"};
	private static final String[] ARTICULOS = {"el", "la", "los", "las", "un", "uno", "una", "unos", "unas"};
	private static final String[] PALABRAS = {"bchef", "receta", "cocina", "y", "e", "recetas", "algo", ",", "poco", "mucho"};
	private static final String SEPARATOR = "y";
	
	
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
		
		if(command.matches(".*\\d.*")) return VoiceCommand.NUMBER;
		else return VoiceCommand.MISUNDERSTOOD;
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
			//TODO: 
			OutputController.getOutputController().send("Buscando receta de: " + string);
			break;
		case SWITCH_KITCHEN:
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
			bChefController.notifyMisunderstood();
			break;
		default:
			System.out.println("La vdd que no se como he acabado aqui");
			bChefController.notifyMisunderstood();
			break;
		}

		return true;
	}
	

	private boolean searchIngredient(String string) {
		Set<String> ingredients = parseIngredients(string);
		OutputController outputController = OutputController.getOutputController();
		outputController.send("Buscando recetas con: " + String.join(", ", ingredients));
		System.out.println("Ingres: ");
		ingredients.stream().forEach(System.out::println);
		return true;
	}
	
	private static Set<String> parseIngredients(String string) {
		
		String numberless = string.replace("\\d", "");
		String reg = "\\s*\\b" + SEPARATOR + "\\b\\s*";

		//numberless = deleteFromArray(numberless, PREPOSICIONES);
		//numberless = deleteFromArray(numberless, ARTICULOS);
		//numberless = deleteFromArray(numberless, PALABRAS);
		
		
		Set<String> ingredients = Arrays.stream(numberless.split(reg)).collect(Collectors.toSet());
		return ingredients;
	}
	
	private static String deleteFromArray(String text, String[] array) {
		for(String word : array) {
			String reg = "\\s*\\b" + word + "\\b\\s*";
		    text = text.replaceAll(reg, " ");
		}
		//text.trim().replaceAll(" +", " ");
		return text;
	}
	
	public static String deleteCommandWords(String text, VoiceCommand command) {
		for(String cmd : command.getCommands()) {
			String reg = "\\s*\\b" + cmd.toLowerCase() + "\\b\\s*";
			//System.out.println(cmd);
			Pattern pattern = Pattern.compile(reg);
		    Matcher matcher = pattern.matcher(text);
			if(matcher.find()) {
				String[] subStrings = text.split(reg);
				text = subStrings[subStrings.length-1];
			}
		}
		//text.trim().replaceAll(" +", " ");
		//Arrays.stream(command.getCommands()).forEach(System.out::println);
		return text;
		
	}
	
	private void switchKitchen(String string) {
		KitchenUtil util = getKitchenUtil(string);
		if(util != KitchenUtil.MISUNDERSTOOD) {
			try {
				Integer[] nums = parseInt(string);
				bChefController.switchKitchen(util, nums);
				OutputController.getOutputController().send("He puesto " + util.getKeywords()[0] + " a " + nums[0]);
			} catch (Exception e) {
				//TODO: preguntar numero
				e.printStackTrace();
			}
		}
		else bChefController.notifyMisunderstood();
	}
	
	private KitchenUtil getKitchenUtil(String string) {
		for(KitchenUtil util : KitchenUtil.values()) {
			for(String keyword : util.getKeywords()) {
				String reg = "\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
				//System.out.println(cmd);
				Pattern pattern = Pattern.compile(reg);
			    Matcher matcher = pattern.matcher(string);
				if(matcher.find()) return util;
			}
		}
		
		return KitchenUtil.MISUNDERSTOOD;
		
	}
	
	private void setAlarm(String string) {
		KitchenUtil util = getKitchenUtil(string);
		if(util.equals(KitchenUtil.MISUNDERSTOOD)) util = null;
		Duration time = parseTime(string);
		
		if(time.isZero() || time.isNegative()) {
			OutputController.getOutputController().send("Hora no válida");
			return;
		}
		String texto = "He puesto una alarma para dentro de: ";
		if(time.toHoursPart() != 0)
			texto = texto + time.toHoursPart() + " horas ";
		if(time.toMinutesPart() != 0) 
			texto = texto + time.toMinutesPart() + " minutos ";
		if(time.toSecondsPart() != 0) 
			texto = texto + time.toSecondsPart() + " segundos";
		
		bChefController.setAlarm(util, 0, time);
		
		OutputController.getOutputController().send(texto);
				
				
		
	}
	
	static final String[] HOUR = {"hora", "horas"};
	static final String[] MINUTE = {"minuto", "minutos"};
	static final String[] SECOND = {"segundo", "segundos"};
	private Duration parseTime(String string) {
		Duration time = Duration.ZERO;
		for(String keyword : HOUR) {
			String reg = "\\w+\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
			Pattern pattern = Pattern.compile(reg);
		    Matcher matcher = pattern.matcher(string);
		    if(matcher.find()) {
				try (Scanner scanner = new Scanner(matcher.group(0))){
					time = time.plusHours(scanner.nextInt());				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		for(String keyword : MINUTE) {
			String reg = "\\w+\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
			Pattern pattern = Pattern.compile(reg);
		    Matcher matcher = pattern.matcher(string);
		    if(matcher.find()) {
				try (Scanner scanner = new Scanner(matcher.group(0))){
					time = time.plusMinutes(scanner.nextInt());				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		for(String keyword : SECOND) {
			String reg = "\\w+\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
			Pattern pattern = Pattern.compile(reg);
		    Matcher matcher = pattern.matcher(string);
			if(matcher.find()) {
				try (Scanner scanner = new Scanner(matcher.group(0))){
					time = time.plusSeconds(scanner.nextInt());				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return time;
		
	}
	

}
