package eus.healthit.bchef.core.assistant.implementations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import eus.healthit.bchef.core.assistant.interfaces.IRecipeAssistantController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.KitchenAlarm;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public class RecipeAssistantController implements IRecipeAssistantController {

	Recipe recipe;
	int currentStepCount;
	List<KitchenAlarm> alarms;
	boolean finished = false;

	public RecipeAssistantController() {
		recipe = null;
		alarms = new ArrayList<>();
	}

	@Override
	public RecipeStep nextStep() {
		System.out.println("---------------------------ASSISTANT CONTROLLER -----------------------");
		if (recipe == null)
			return null;
		List<RecipeStep> steps = recipe.getSteps();
		if (steps.size() > currentStepCount + 1) {
			RecipeStep current = steps.get(++currentStepCount);
//			if (current.getAction() != null) {
//				// TODO: Switch + Alarma
//			}
			return recipe.getSteps().get(currentStepCount);
		}
		finishRecipe();
		return null;
	}

	@Override
	public RecipeStep prevStep() {
		List<RecipeStep> steps = recipe.getSteps();
		if (--currentStepCount > steps.size())
			return null;
		return steps.get(currentStepCount);
	}

	@Override
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		currentStepCount = 0;
		finished = false;
	}

	@Override
	public KitchenAlarm setAlarm(KitchenUtil util, Integer index, Duration time) {
		// TODO: Listener panel
		if(index == null) index = 0;
		KitchenAlarm alarm = new KitchenAlarm(util, index, time);
		alarms.add(alarm);
		alarm.start();
		return alarm;
	}

	@Override
	public void finishRecipe() {
		// recipe = null;
		finished = true;
	}

	@Override
	public RecipeStep getCurrentStep() {
		if (recipe == null) {
			return null;
		}
		return recipe.getSteps().get(currentStepCount);
	}

	@Override
	public Recipe getRecipe() {
		return recipe;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public int getCurrentStepCount() {
		return currentStepCount;
	}

}
