 package eus.healthit.bchef.core.enums;

public enum VoiceCommand {
	ALARM("Alarma", "Cuenta atras", "Cronometro", "Aviso", "Temporizador", "Temporizar", "Contador", "Cronometrar", "Avisame", "Avisar"),
	LIST_READ("Leeme", "Lee", "Leer"),
	LIST_ADD("A�ade", "A�adir", "Escribe", "Escribir", "Anota", "Anotar", "Apunta", "Apuntar", "Agrega", "Agregar", "A la lista", "En la lista"),
	LIST_REMOVE("Elimina", "Eliminar", "Eliminame", "Borrar", "Borra", "Borrame", "Quitar", "Quita", "Quitame", "Desapunta", "Desapuntar", "Desapuntame", "De la lista"),
	SEARCH_INGREDIENT("Que tenga", "Que contenga", "Usando", "Receta con", "Con", "Contenga", "Tenga", "Posea", "Use", "Utilice", "Receta con"),
	SEARCH_RECIPE ("Busca", "Buscar", "Buscame", "Busques", "Encuentra", "Receta de", "Recetas de", "Quiero", "Busca receta", "Busca recetas", "Buscar recetas", "Buscar receta"),
	SWITCH_KITCHEN("Apaga", "Enciende", "Fuego", "Horno", "Induccion", "Vitroceramica", "Vitro"),
	RECIPE_PREVIOUS("Anterior", "Antes", "Repite", "Repeticion", "Olvidado"),
	RECIPE_NEXT("Siguiente", "Ahora que", "Tengo que hacer", "Continua"),
	POWER_OFF("Apagate", "Adios", "Desconecta", "Desconectate"),
	YES("Si", "Afirmativo", "Correcto", "Bien", "Vale", "Ok", "Okay", "Dale"),
	NO("No", "Negativo", "Que va", "Cancela", "Cancelar", "Calla", "Silencio", "Para"),
	THANKS("Gracias", "Agradezco"),
	TIME("Que hora", "hora es", "Dime hora", "Dime la hora", "la hora"),
	LUMBRA("Lumbra"),
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
