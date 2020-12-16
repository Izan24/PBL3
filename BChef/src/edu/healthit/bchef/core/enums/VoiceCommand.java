package edu.healthit.bchef.core.enums;
import java.text.Collator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum VoiceCommand {
	SEARCH_RECIPE ("Busca", "Buscar", "Buscame", "Encuentra"),
	SEARCH_INGREDIENT("Que tenga", "Que contenga", "Usando", "Con"),
	SWITCH_KITCHEN("Apaga", "Enciende", "Fuego", "Horno", "Induccion", "Vitroceramica", "Vitro"),
	RECIPE_PREVIOUS("Anterior", "Antes", "Repite", "Repeticion", "Olvidado"),
	RECIPE_NEXT("Siguiente", "Ahora que", "Tengo que hacer", "Continua"),
	ALARM("Alarma", "Cuenta atras", "Cronometro", "Aviso", "Temporizador", "Temporizar", "Contador", "Cronometrar", "Avisame", "Avisar"),
	LIST_ADD("Añade", "Añadir", "Escribe", "Escribir", "Anota", "Anotar", "Apunta", "Apuntar", "Agrega", "Agregar"),
	LIST_REMOVE("Elimina", "Eliminar", "Eliminame", "Borrar", "Borra", "Borrame", "Quitar", "Quita", "Quitame", "Desapunta", "Desapuntar", "Desapuntame"),
	POWER_OFF("Apagate", "Adios", "Desconecta", "Desconectate"),
	YES("Si", "Afirmativo", "Correcto", "Bien", "Vale", "Ok", "Okay", "Dale"),
	NO("No", "Negativo", "Que va"),
	NUMBER,
	MISUNDERSTOOD;
	
	//Algo que tenga una zanahoria
	

	private String[] commands;
	
	VoiceCommand(String... commands) {
		this.commands = commands;
	}
	
	public static VoiceCommand parseCommand(String command) {
		
		Collator c = Collator.getInstance(new Locale("es"));
		c.setStrength(Collator.SECONDARY);
		for(VoiceCommand cmd : values()) {
			for(String str : cmd.getCommands()) {
				String pattern = "\\b" + cmd + "\\b";
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(str);
				if(m.find()) return cmd;
			}
		}
		return MISUNDERSTOOD;
	}

	public String[] getCommands() {
		return commands;
	}
	
	
	
}
