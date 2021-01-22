package eus.healthit.bchef.core.controllers.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipeRating;
import eus.healthit.bchef.core.view.panels.center.CenterViewStep;

public class StepViewController implements PropertyChangeListener {

	CenterViewStep stepView;
	CenterViewController centerController;
	User user;

	int currentStep;

	public StepViewController(CenterViewStep recipeView, CenterViewController centerController, User user) {
		this.stepView = recipeView;
		this.centerController = centerController;
		this.user = user;

		currentStep = 0;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}

	public void displayNexStep() {
		stepView.setStep(stepView.getRecipe().getSteps().get(currentStep + 1));
		currentStep++;
	}

	public void recipeEnded() {
		currentStep = 0;
		user.addHistory(stepView.getRecipe());
		System.out.println("Falta Mandar El user a la Dataaaabase o meterle a la rel de user historial eso");
		centerController.rateRecipe(stepView.getRecipe());
	}

}