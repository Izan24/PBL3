package eus.healthit.bchef.core.util;

import java.time.Duration;

import com.google.api.LabelDescriptor.ValueType;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.protobuf.NullValue;

import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.Recipe;
import io.grpc.alts.internal.AltsContextProto;

public class TextBuilder {

	public static String newAlarmMessage(Duration time, KitchenUtil util, Integer index) {
		String texto = "He puesto una alarma para dentro de: ";
		texto = texto + durationToString(time);
		if(util != null) {
			texto = texto + "para " + util.getKeywords()[0];
			if(index != null && index > 0)
				texto = texto + " " + index;
		}
		return texto;
	}
	
	public static String alarmDeactivateMessage(KitchenUtil util, Integer index) {
		String texto = "¿Quieres que apague el " + util.getKeywords()[0];
		if(index != null && index != -1) {
			texto = texto + " " + index;
		}
		texto = texto + "?";
		return texto;
	}
	
	public static String recipeFoundMessage(Recipe recipe) {
		String textoString = "He encontrado: " + recipe.getName() + ". ¿Quieres empezarlo?";
		return textoString;
	}

	public static String errorMessage(String reason) {
		switch (reason) {
		case "INVALID_TIME":
			return "Tiempo no válido";
		case "MISSING_RECIPE":
			return "Lo siento, aún no has empezado una receta.";
		case "MISSING_NEXTSTEP":
			return "Ya has completado la receta.";
		case "MISSING_PREVSTEP":
			return "Estas en el primer paso.";
		case "RECIPES_NOT_FOUND":
			return "Lo siento, no he encontrado ninguna receta.";
		case "NO_MORE_RECIPES":
			return "No he encontrado más recetas";
		default:
			return "Lo siento, no te he entendido";
		}
	}
	
	
	public static String askAlarmMessage(Duration time, KitchenUtil util, Integer value) {
		String durationString;
				
		String txt = "¿Quieres que ";
		if(util != null) {
			if(value != null)
			txt = txt + "ponga ";
			switch(util) {
			case OVEN:
				txt = txt + " el horno a " + value + " grados ";
				break;
			case STOVE:
				txt = txt + " el fuego al " + value;
				break;
			default:
				break;
			}
		}
		else {
			txt = txt + "ponga una alarma para dentro de ";
		}
		
		if(time != null && time.isZero()) txt = txt + durationToString(time) + "?";

		return txt;
	}
	

	public static String durationToString(Duration time) {
		String durationString = "";
		if(time.toHoursPart() != 0)
			durationString = durationString + time.toHoursPart() + " horas ";
		if(time.toMinutesPart() != 0) 
			durationString = durationString + time.toMinutesPart() + " minutos ";
		if(time.toSecondsPart() != 0) 
			durationString = durationString + time.toSecondsPart() + " segundos ";
		
		return durationString;
	}


	public static String askKitchenSwitch(KitchenUtil util, Integer value) {
		String txt = "¿Quieres que ponga ";
		switch(util) {
		case OVEN:
			txt = txt + " el horno a " + value + " grados?";
			break;
		case STOVE:
			txt = txt + " el fuego al " + value + "?";
			break;
		}
		
		return null;
	}
	
}
