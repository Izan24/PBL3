package eus.healthit.bchef.core.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.LabelDescriptor.ValueType;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.protobuf.NullValue;

import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.Recipe;
import io.grpc.alts.internal.AltsContextProto;

public class TextBuilder {
	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	public static String newAlarmMessage(Duration time, KitchenUtil util, Integer index) {
		String texto = rb.getString("new_alarm_1") + " ";
		texto = texto + durationToString(time);
		if (util != null) {
			texto = texto + rb.getString("new_alarm_2") + " " + util.getKeywords()[0];
			if (index != null && index > 0)
				texto = texto + " " + index;
		}
		return texto;
	}

	public static String alarmDeactivateMessage(KitchenUtil util, Integer index) {
		String texto = rb.getString("alarm_deactivate_1") + " " + util.getKeywords()[0];
		if (index != null && index != -1) {
			texto = texto + " " + index;
		}
		texto = texto + "?";
		return texto;
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

		if (time != null && time.isZero())
			txt = txt + durationToString(time) + "?";

		return StringParser.stripSpaces(txt);
	}

	public static String durationToString(Duration time) {
		String durationString = "";
		if (time.toHoursPart() != 0)
			durationString = durationString + time.toHoursPart() + " " + rb.getString("hours") + " ";
		if (time.toMinutesPart() != 0)
			durationString = durationString + time.toMinutesPart() + " " + rb.getString("minutes") + " ";
		if (time.toSecondsPart() != 0)
			durationString = durationString + time.toSecondsPart() + " " + rb.getString("seconds") + " ";

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
			txt.append(" " + (index + 1));
		if (value > 0)
			switch (util) {
			case OVEN:
				txt.append(" " + rb.getString("kitchen_switch_4") + " " + value + " " + rb.getString("kitchen_switch_5")
						+ " ");
				break;
			case STOVE:
				txt.append(" " + rb.getString("kitchen_swtich_6") + " " + value);
				break;
			default:
				break;
			}
		txt.append(".");

		return StringParser.stripSpaces(txt.toString());
	}

	public static String listItemNotFound(String string) {
		return StringParser.stripSpaces(
				rb.getString("list_item_not_found_1") + " " + string + " " + rb.getString("list_item_not_found_2"));
	}

}
