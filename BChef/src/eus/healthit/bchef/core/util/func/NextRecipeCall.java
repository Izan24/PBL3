package eus.healthit.bchef.core.util.func;

import eus.healthit.bchef.core.assistant.BChefController;

public class NextRecipeCall implements FunctionCall {

	public static final String ID_STRING = "NEXT_RECIPE";
	
	int index;
	
	public NextRecipeCall(int index) {
		this.index = index;
	}
	
	@Override
	public void executeCall() {
		BChefController.getInstance().nextRecipe();
	}

	@Override
	public String getId() {
		return ID_STRING;
	}
	
}
