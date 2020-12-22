package eus.healthit.bchef.core.controllers;

import java.text.Collator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eus.healthit.bchef.core.Configuration;
import eus.healthit.bchef.core.enums.VoiceCommand;

public class CommandController {
	
	private static CommandController obj = new CommandController();
	
	private CommandController() {
	}
	
	public static CommandController getCommandController() {
		return obj;
	}

	public static VoiceCommand parseCommand(String command) {
		Collator c = Collator.getInstance(new Locale(Configuration.getLocale()));
		c.setStrength(Collator.SECONDARY);
		for(VoiceCommand cmd : VoiceCommand.values()) {
			for(String str : cmd.getCommands()) {
				Pattern p = Pattern.compile("\\b" + str + "\\b", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(command);
				if(m.find()) return cmd;
			}
		}
		
		try {
			System.out.println(parseInt(command)); 
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
		switch (command) {
		case ALARM:
		case SEARCH_RECIPE:
			break;

		default:
			break;
		}
		
		
		return true;
	}
	
}
