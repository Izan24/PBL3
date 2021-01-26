package eus.healthit.bchef.core.app.controllers;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.ui.components.CustomTimer;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewStep;
import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.models.KitchenAlarm;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;

public class StepViewController implements PropertyChangeListener {

	Font textFont = new Font("Segoe UI", Font.PLAIN, 25);

	CenterViewStep stepView;
	CenterViewController centerController;
	User user;

	int currentStep;

//	List<KitchenAlarm> alarms;
	List<CustomTimer> alarms;

	public StepViewController(CenterViewStep recipeView, CenterViewController centerController, User user) {
		this.stepView = recipeView;
		this.centerController = centerController;
		this.user = user;
		BChefController.getInstance().addPropertyChangeListener(this);
		currentStep = 0;
		alarms = new ArrayList<>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "ALARM_UPDATE":
			updateAlarm((KitchenAlarm) evt.getNewValue());
			break;
		case "ALARM_NEW":
			addAlarm((KitchenAlarm) evt.getNewValue());
			break;
		case "ALARM_FINISH":
			removeAlarm((KitchenAlarm) evt.getNewValue());
			break;
		case "UPDATE_STEP":
			updateStep((RecipeStep) evt.getNewValue());
			System.out.println("updated en el contrl");
			break;
		case "FINISH_RECIPE":
			System.out.println("termino usted su recipe maestro");
			recipeEnded();
			break;
		case "START_RECIPE":
			centerController.setStepView((Recipe) evt.getNewValue());
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
		JSONCalls.addToHistory(user.getId(), stepView.getRecipe().getUUID());
		centerController.rateRecipe(stepView.getRecipe());
	}

	private void addAlarm(KitchenAlarm alarm) {
		CustomTimer timer = new CustomTimer(alarm, textFont);
		alarms.add(timer);
		stepView.addNewAlarm(timer);
	}

	private void removeAlarm(KitchenAlarm alarm) {
		System.out.println("length antes de: " + alarms.size());
		alarms.removeIf(x -> x.getAlarm().equals(alarm));
		stepView.removeAlarm(alarms);
		System.out.println("length despues de: " + alarms.size());
	}

	private void updateAlarm(KitchenAlarm alarm) {
		CustomTimer timer = alarms.stream().filter(x -> x.getAlarm().equals(alarm)).findFirst().orElse(null);
		if (timer != null) {
			timer.updateTime(alarm.getResTime());
		}
	}

	private void updateStep(RecipeStep step) {
		stepView.setStep(step);
	}

}