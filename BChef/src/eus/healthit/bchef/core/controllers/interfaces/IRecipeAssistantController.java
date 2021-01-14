package eus.healthit.bchef.core.controllers.interfaces;

import java.time.Duration;

import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public interface IRecipeAssistantController {

	public RecipeStep nextStep();
	
	public RecipeStep prevStep();
	
	public Recipe getRecipe();
	
	public void setRecipe(Recipe recipe);
	
	public void setAlarm(KitchenUtil util, int index, Duration time);
	
	public void finishRecipe();
	
	public int getCurrentStep();

}