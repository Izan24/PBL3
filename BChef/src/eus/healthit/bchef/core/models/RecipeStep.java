package eus.healthit.bchef.core.models;

import eus.healthit.bchef.core.enums.RecipeStepActions;

public class RecipeStep {

	String text;
	int value;
	RecipeStepActions action;
	// Time time;

	public RecipeStep(String text, int value, RecipeStepActions action) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
