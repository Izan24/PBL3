package eus.healthit.bchef.core.enums;

public enum VoiceCommand {
	SEARCH_INGREDIENT("Que tenga", "Que contenga", "Usando", "Con", "Contenga", "Tenga", "Posea", "Use", "Utilice"),
	SEARCH_RECIPE ("Busca", "Buscar", "Buscame", "Busques", "Encuentra"),
	SWITCH_KITCHEN("Apaga", "Enciende", "Fuego", "Horno", "Induccion", "Vitroceramica", "Vitro"),
	RECIPE_PREVIOUS("Anterior", "Antes", "Repite", "Repeticion", "Olvidado"),
	RECIPE_NEXT("Siguiente", "Ahora que", "Tengo que hacer", "Continua"),
	ALARM("Alarma", "Cuenta atras", "Cronometro", "Aviso", "Temporizador", "Temporizar", "Contador", "Cronometrar", "Avisame", "Avisar"),
	LIST_ADD("Añade", "Añadir", "Escribe", "Escribir", "Anota", "Anotar", "Apunta", "Apuntar", "Agrega", "Agregar"),
	LIST_REMOVE("Elimina", "Eliminar", "Eliminame", "Borrar", "Borra", "Borrame", "Quitar", "Quita", "Quitame", "Desapunta", "Desapuntar", "Desapuntame"),
	POWER_OFF("Apagate", "Adios", "Desconecta", "Desconectate"),
	YES("Si", "Afirmativo", "Correcto", "Bien", "Vale", "Ok", "Okay", "Dale"),
	NO("No", "Negativo", "Que va", "Cancela", "Cancelar", "Calla", "Silencio", "Para"),
	NUMBER,
	MISUNDERSTOOD;	

	private String[] commands;
	
	VoiceCommand(String... commands) {
		this.commands = commands;
	}

	public String[] getCommands() {
		return commands;
	}
	
	
	
}
