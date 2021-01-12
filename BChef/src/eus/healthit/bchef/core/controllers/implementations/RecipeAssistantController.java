package eus.healthit.bchef.core.controllers.implementations;

import java.sql.Time;
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
		currentStep++;
		RecipeStep current = recipe.getSteps().get(currentStep);
		if(current.getAction() != null) {
			
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
	}

	@Override
	public void setAlarm(KitchenUtil util, int index, Time time) {
		alarms.add(new KitchenAlarm(util, index, time, BChefController.getBChefController()));
		
	}

	@Override
	public void finishRecipe() {
		recipe = null;
		
	}
	
	@Override
	public int getCurrentStep() {
		return currentStep;
	}

}
