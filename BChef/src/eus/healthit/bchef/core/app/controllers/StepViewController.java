package eus.healthit.bchef.core.app.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewStep;
import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.models.User;

public class StepViewController implements PropertyChangeListener {

	CenterViewStep stepView;
	CenterViewController centerController;
	User user;

	int currentStep;

	public StepViewController(CenterViewStep recipeView, CenterViewController centerController, User user) {
		this.stepView = recipeView;
		this.centerController = centerController;
		this.user = user;
//		BChefController.getInstance().addPropertyChangeListener(this);
		currentStep = 0;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "ALARM_UPDATE":
			
			break;
			
		case "ALARM_NEW":
			break;
		case "ALARM_FINISH":
			break;

		default:
			break;
		}
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