package eus.healthit.bchef.core.enums;

public enum RecipeStepActions {
	OVEN("Horno"), STOVE("Fuego"), TIMER("Tiempo");

	private String keyword;

	RecipeStepActions(String keyword) {
		this.keyword = keyword;
	}

	public String getKeywords() {
		return keyword;
	}
}
