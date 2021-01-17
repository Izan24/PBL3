package eus.healthit.bchef.core.util;

import java.time.Duration;

import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.Recipe;

public class TextBuilder {

	public static String newAlarmMessage(Duration time, KitchenUtil util, int index) {
		String texto = "He puesto una alarma para dentro de: ";
		if(time.toHoursPart() != 0)
			texto = texto + time.toHoursPart() + " horas ";
		if(time.toMinutesPart() != 0) 
			texto = texto + time.toMinutesPart() + " minutos ";
		if(time.toSecondsPart() != 0) 
			texto = texto + time.toSecondsPart() + " segundos ";
		if(util != null) {
			texto = texto + "para " + util.getKeywords()[0];
			if(index != -1 )
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
		case "NO_MORE_RECIPES":
			return "No he encontrado más recetas";
		default:
			return "Lo siento, no te he entendido";
		}
	}
	
	
}
