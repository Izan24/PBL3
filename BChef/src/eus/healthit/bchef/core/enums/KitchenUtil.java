package eus.healthit.bchef.core.enums;

public enum KitchenUtil {
	OVEN("Horno", "Hornillo"),
	STOVE ("Fuego", "Inducción", "Vitro", "Vitrocerámica", "Fogón"),
	//LIGHT("Luz", "Lámpara", "Foco", "Bombilla", "Lamparilla"),
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
