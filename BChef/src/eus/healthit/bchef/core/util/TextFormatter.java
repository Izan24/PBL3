package eus.healthit.bchef.core.util;

import com.google.api.client.util.Strings;

public class TextFormatter {

	public static String format(String text, int count) {
		String[] array = text.split("[ ]");
		int i = 0;
		StringBuilder sBuilder = new StringBuilder();
		for (String string : array) {
			i += string.length();
			if (i > count) {
				i = 0;
				string = string + "\n";
			}
			sBuilder.append((!string.endsWith("\n"))?string+" ":string);
		}
	    System.out.println(sBuilder.toString());
		return sBuilder.toString();
	}

}
