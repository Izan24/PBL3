package eus.healthit.bchef.core.enums;

public enum RecipeStepActions {

	TEXT("Texto"), OVEN("Horno"), STOVE("Fuego"), TIMER("Tiempo");

	private String keyword;

	RecipeStepActions(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	@Override
	public String toString() {
		return getKeyword();
	}
}
