package eus.healthit.bchef.core.controllers.implementations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import eus.healthit.bchef.core.controllers.BChefController;
import eus.healthit.bchef.core.controllers.interfaces.IRecipeAssistantController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.KitchenAlarm;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public class RecipeAssistantController implements IRecipeAssistantController {

	Recipe recipe;
	int currentStep;
	List<KitchenAlarm> alarms;
	
	public RecipeAssistantController() {
		recipe = null;
		alarms = new ArrayList<>();
	}
	
	@Override
	public RecipeStep nextStep() {
		if(recipe == null) return null;
		RecipeStep current = recipe.getSteps().get(currentStep++);
		if(current.getAction() != null) {
			//TODO: Switch + Alarma
		}
		return recipe.getSteps().get(currentStep);
	}

	@Override
	public RecipeStep prevStep() {
		return recipe.getSteps().get(++currentStep);
	}

	@Override
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		currentStep = 0;
	}

	@Override
	public void setAlarm(KitchenUtil util, int index, Duration time) {
		//TODO: Listener panel
		KitchenAlarm alarm = new KitchenAlarm(util, index, time, BChefController.getInstance());
		alarms.add(alarm);
		alarm.start();
	}

	@Override
	public void finishRecipe() {
		recipe = null;
	}
	
	@Override
	public int getCurrentStep() {
		return currentStep;
	}

	@Override
	public Recipe getRecipe() {
		return recipe;
	}

}
