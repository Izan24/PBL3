package eus.healthit.bchef.core.util;

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
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.enums.VoiceCommand;

public class StringParser {

	private static final String SEPARATOR = "y";

	static final String[] HOUR = { "hora", "horas" };
	static final String[] MINUTE = { "minuto", "minutos" };
	static final String[] SECOND = { "segundo", "segundos" };

	public static Duration parseTime(String string) {
		Duration time = Duration.ZERO;
		for (String keyword : HOUR) {
			String reg = "\\w+\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(string);
			if (matcher.find()) {
				try (Scanner scanner = new Scanner(matcher.group(0))) {
					time = time.plusHours(scanner.nextInt());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (String keyword : MINUTE) {
			String reg = "\\w+\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(string);
			if (matcher.find()) {
				try (Scanner scanner = new Scanner(matcher.group(0))) {
					time = time.plusMinutes(scanner.nextInt());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (String keyword : SECOND) {
			String reg = "\\w+\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(string);
			if (matcher.find()) {
				try (Scanner scanner = new Scanner(matcher.group(0))) {
					time = time.plusSeconds(scanner.nextInt());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return time;
	}
	
	public static String durationToString(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}

	public static String deleteCommandWords(String text, VoiceCommand command) throws ArrayIndexOutOfBoundsException {
		for (String cmd : command.getCommands()) {
			String reg = "\\s*\\b" + cmd.toLowerCase() + "\\b\\s*";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				String[] subStrings = text.split(reg);
				text = subStrings[subStrings.length - 1];
			}
		}
		return text;

	}

	public static String deleteWordsFromArray(String text, String[] array) {
		for (String word : array) {
			String reg = "\\s*\\b" + word + "\\b\\s*";
			text = text.replaceAll(reg, " ");
		}
		return text;
	}

	public static Set<String> parseIngredients(String string) {
		String numberless = string.replace("\\d", "");
		String reg = "\\s*\\b" + SEPARATOR + "\\b\\s*";
		Set<String> ingredients = Arrays.stream(numberless.split(reg)).collect(Collectors.toSet());
		return ingredients;
	}

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

		if (command.matches(".*\\d.*"))
			return VoiceCommand.NUMBER;
		else
			return VoiceCommand.MISUNDERSTOOD;
	}

	public static KitchenUtil parseKitchenUtil(String string) {
		for (KitchenUtil util : KitchenUtil.values()) {
			for (String keyword : util.getKeywords()) {
				String reg = "\\s*\\b" + keyword.toLowerCase() + "\\b\\s*";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(string.toLowerCase());
				if (matcher.find())
					return util;
			}
		}

		return KitchenUtil.MISUNDERSTOOD;
	}

	public static Integer[] parseInt(String string) throws NumberFormatException, IllegalStateException {
		Matcher matcher = Pattern.compile("\\d+").matcher(string);
		List<Integer> numsList = new ArrayList<>();
		while (matcher.find()) {
			numsList.add(Integer.valueOf(matcher.group()));
		}
		return numsList.toArray(new Integer[0]);
	}

	public static String stripSpaces(String string) {
		return string.replaceAll("\\s+", " ").trim();
	}

}
