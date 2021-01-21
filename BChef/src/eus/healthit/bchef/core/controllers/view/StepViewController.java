package eus.healthit.bchef.core.controllers.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipeRating;

public class StepViewController implements PropertyChangeListener {

	CenterViewRecipe recipeView;
	CenterViewController centerController;

	public StepViewController(CenterViewRecipe recipeView, CenterViewController centerController) {
		this.recipeView = recipeView;
		this.centerController = centerController;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}

	public void recipeEnded() {
		centerController.rateRecipe(recipeView.getRecipe());
	}

}