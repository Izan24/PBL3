package eus.healthit.bchef.core.assistant.implementations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.assistant.interfaces.IRecipeAssistantController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.KitchenAlarm;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public class RecipeAssistantController implements IRecipeAssistantController {

	Recipe recipe;
	int currentStep;
	List<KitchenAlarm> alarms;
	boolean finished = false;

	public RecipeAssistantController() {
		recipe = null;
		alarms = new ArrayList<>();
	}

	@Override
	public RecipeStep nextStep() {
		if (recipe == null)
			return null;
		List<RecipeStep> steps = recipe.getSteps();
		if (steps.size() > currentStep + 1) {
			RecipeStep current = steps.get(++currentStep);
//			if (current.getAction() != null) {
//				// TODO: Switch + Alarma
//			}
			return recipe.getSteps().get(currentStep);
		}
		finishRecipe();
		return null;
	}

	@Override
	public RecipeStep prevStep() {
		return recipe.getSteps().get(--currentStep);
	}

	@Override
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		currentStep = 0;
		finished = false;
	}

	@Override
	public KitchenAlarm setAlarm(KitchenUtil util, int index, Duration time) {
		// TODO: Listener panel
		KitchenAlarm alarm = new KitchenAlarm(util, index, time, BChefController.getInstance());
		alarms.add(alarm);
		alarm.start();
		return alarm;
	}

	@Override
	public void finishRecipe() {
		//recipe = null;
		finished = true;
	}

	@Override
	public int getCurrentStep() {
		return currentStep;
	}

	@Override
	public Recipe getRecipe() {
		return recipe;
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}

}
