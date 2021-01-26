package eus.healthit.bchef.core.util;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.Recipe;

public class TextBuilder {
	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	public static String newAlarmMessage(Duration time, KitchenUtil util, Integer index) {
		String texto = rb.getString("new_alarm_1") + " ";
		texto = texto + durationToString(time);
		if (util != null) {
			texto = texto + rb.getString("new_alarm_2") + " " + util.getKeywords()[0];
			if (index != null && index > 0)
				texto = texto + " " + (index + 1);
		}
		return texto;
	}

	public static String alarmDeactivateMessage(KitchenUtil util, Integer index) {
		String texto = rb.getString("alarm_deactivate_1") + " " + util.getKeywords()[0];
		if (index != null) {
			texto = texto + " " + (index + 1);
		}
		texto = texto + "?";
		return texto;
	}

	public static String findingRecipeMessage(String string) {
		return rb.getString("find_recipe") + " " + string;
	}

	public static String findingRecipeByIngredientMessage(Set<String> ingredients) {
		return rb.getString("find_recipe_ingredients") + " " + String.join(", ", ingredients);
	}

	public static String recipeFoundMessage(Recipe recipe) {
		String textoString = rb.getString("recipe_found_1") + " " + recipe.getName() + rb.getString("recipe_found_2");
		return textoString;
	}

	public static String errorMessage(String reason) {
		switch (reason) {
		case "INVALID_TIME":
			return rb.getString("error_invalid_time");
		case "MISSING_RECIPE":
			return rb.getString("error_missing_recipe");
		case "MISSING_NEXTSTEP":
			return rb.getString("error_missing_nextstep");
		case "MISSING_PREVSTEP":
			return rb.getString("error_missing_prevstep");
		case "RECIPES_NOT_FOUND":
			return rb.getString("error_recipes_not_found");
		case "NO_MORE_RECIPES":
			return rb.getString("error_no_more_recipes");
		default:
			return rb.getString("error_default");
		}
	}

	public static String askAlarmMessage(Duration time, KitchenUtil util, Integer value) {
		String txt = rb.getString("ask_alarm_1") + " ";
		if (util != null) {
			if (value != null)
				txt = txt + " " + rb.getString("ask_alarm_2") + " ";
			switch (util) {
			case OVEN:
				txt = txt + " " + rb.getString("ask_alarm_3") + " " + value + " " + rb.getString("ask_alarm_4") + " ";
				break;
			case STOVE:
				txt = txt + " " + rb.getString("ask_alarm_5") + " " + value;
				break;
			default:
				break;
			}
		} else {
			txt = txt + " " + rb.getString("ask_alarm_6");
		}

		if (time != null && !time.isZero())
			txt = txt + durationToString(time);

		txt = txt + "?";
		return StringParser.stripSpaces(txt);
	}

	public static String durationToString(Duration time) {
		String durationString = "";
		if (time.toHours() % 24 != 0)
			durationString = durationString + time.toHours() % 24 + " " + rb.getString("hours") + " ";
		if (time.toMinutes() % 60 != 0)
			durationString = durationString + time.toMinutes() % 60 + " " + rb.getString("minutes") + " ";
		if (time.getSeconds() % 60 != 0)
			durationString = durationString + time.getSeconds() % 60 + " " + rb.getString("seconds") + " ";

		return durationString;
	}

	public static String askKitchenSwitch(KitchenUtil util, Integer value) {
		String txt = rb.getString("ask_kitchen_switch_1");
		switch (util) {
		case OVEN:
			txt = txt + " " + rb.getString("ask_kitchen_switch_2") + " " + value + " "
					+ rb.getString("ask_kitchen_switch_3");
			break;
		case STOVE:
			txt = txt + "  " + rb.getString("ask_kitchen_switch_4") + " " + value + "?";
			break;
		default:
			break;
		}

		return null;
	}

	public static String kitchenSwitchMessage(KitchenUtil util, Integer value, Integer index) {
		StringBuilder txt = new StringBuilder(rb.getString("kitchen_switch_1") + " ");
		txt.append(((value > 0) ? rb.getString("kitchen_switch_2") : rb.getString("kitchen_switch_3")) + " ");
		txt.append(util.getKeywords()[0]);
		if (value != null && value >= 0)
			if (index != null)
				txt.append(" " + (index + 1));
		if (value > 0)
			switch (util) {
			case OVEN:
				txt.append(
						" " + rb.getString("kitchen_switch_4") + " " + value + " " + rb.getString("kitchen_switch_5"));
				break;
			case STOVE:
				txt.append(" " + rb.getString("kitchen_switch_6") + " " + value);
				break;
			default:
				break;
			}
		txt.append(".");

		return StringParser.stripSpaces(txt.toString());
	}

	public static String addedToList(String string) {
		return rb.getString("list_item_add_1") + " " + string + " " + rb.getString("list_item_add_2");
	}

	public static String removedFromList(String string) {
		return rb.getString("list_item_remove_1") + " " + string + " " + rb.getString("list_item_remove_2");
	}

	public static String listItemNotFound(String string) {
		return StringParser.stripSpaces(
				rb.getString("list_item_not_found_1") + " " + string + " " + rb.getString("list_item_not_found_2"));
	}

	public static String readListMessage(List<String> complete, List<String> incomplete) {
		boolean empty = true;
		String txt = "";
		if (complete.size() > 0) {
			empty = false;
			txt = rb.getString("list_item_read_complete") + " " + String.join(", ", complete) + ". ";
		}
		if (incomplete.size() > 0) {
			empty = false;
			txt = txt + rb.getString("list_item_read_incomplete") + " " + String.join(", ", incomplete) + ".";
		}
		return (empty) ? rb.getString("list_item_read_empty") : txt;
	}

	public static String timeMessage(LocalTime time) {
		return String.join(":", String.valueOf(time.getHour()), String.valueOf(time.getMinute()));
	}

	public static String thankMessage() {
		return rb.getString("you_are_welcome");
	}
}
