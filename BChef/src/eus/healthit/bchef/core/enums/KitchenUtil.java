package eus.healthit.bchef.core.enums;

public enum KitchenUtil {
	OVEN("Horno", "Hornillo"),
	STOVE ("Fuego", "Induccion", "Vitro", "Vitroceramica", "Fogon"),
	MISUNDERSTOOD;

	private String[] keywords;
	
	KitchenUtil(String... keywords) {
		this.keywords = keywords;
	}

	public String[] getKeywords() {
		return keywords;
	}
}
