package eus.healthit.bchef.core.enums;

public enum KitchenUtil {
	OVEN("Horno", "Hornillo"),
	STOVE ("Fuego", "Inducci�n", "Vitro", "Vitrocer�mica", "Fog�n"),
	//LIGHT("Luz", "L�mpara", "Foco", "Bombilla", "Lamparilla"),
	//VENT("Extractor", "Ventilador", "Ventila", "Aire"),
	//MICROWAVE("Microondas", "Micro"),
	MISUNDERSTOOD;

	private String[] keywords;
	
	KitchenUtil(String... keywords) {
		this.keywords = keywords;
	}

	public String[] getKeywords() {
		return keywords;
	}
	
	
	
}
