package eus.healthit.bchef.core.app.controllers.centerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.PrincipalView;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.panels.center.CenterView;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewBchef;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewCreateRecipe;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewList;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewProfile;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewProfileSettings;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewRecipe;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewRecipeRating;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewShopList;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewStep;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewVisitProfile;
import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;

public class CenterViewController implements ActionListener {

	PrincipalView principalView;

	CenterViewList listView;
	CenterViewProfile profileView;
	CenterViewRecipe recipeView;
	CenterViewBchef bchefView;
	CenterViewShopList shopListView;
	CenterViewCreateRecipe createRecipeView;
	CenterViewStep stepView;
	CenterViewVisitProfile visitProfile;
	CenterViewProfileSettings settingsView;
	CenterViewRecipeRating recipeRatingView;

	User user;

	public CenterViewController(PrincipalView principalView, User user, WindowFrameController windowController,
			WindowFrame window) {

		this.principalView = principalView;
		this.user = user;

		initViews(windowController, window);
	}

	private void initViews(WindowFrameController windowController, WindowFrame window) {
		listView = new CenterViewList(this);
		profileView = new CenterViewProfile(user, this);
		recipeView = new CenterViewRecipe(this, user);
		bchefView = new CenterViewBchef();
		shopListView = new CenterViewShopList(user);
		createRecipeView = new CenterViewCreateRecipe(user, window, this);
		stepView = new CenterViewStep(this, user);
		visitProfile = new CenterViewVisitProfile(user, this);
		settingsView = new CenterViewProfileSettings(user, windowController, window);
		recipeRatingView = new CenterViewRecipeRating(this, user);
	}

	public void setStartView() {
		principalView.changeCenterView(listView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case CenterViewControllerAC.HOME:
			principalView.changeCenterView(listView);
			BChefController.getInstance().stopVoiceRecon();
			break;

		case CenterViewControllerAC.LIST:
			principalView.changeCenterView(shopListView);
			BChefController.getInstance().stopVoiceRecon();
			break;

		case CenterViewControllerAC.PROFILE:
			profileView.updateView();
			principalView.changeCenterView(profileView);
			BChefController.getInstance().stopVoiceRecon();
			break;

		case CenterViewControllerAC.PROFILE_VISIT:
			principalView.changeCenterView(visitProfile);
			BChefController.getInstance().stopVoiceRecon();
			break;

		case CenterViewControllerAC.BCHEF:
			RecipeStep step = BChefController.getInstance().getCurrentStep();
			if (step != null) {
				principalView.changeCenterView(stepView);
			} else
				principalView.changeCenterView(bchefView);
			BChefController.getInstance().startVoiceRecon();
			break;

		case CenterViewControllerAC.SETTINGS:
			settingsView.updateView();
			principalView.changeCenterView(settingsView);
			BChefController.getInstance().stopVoiceRecon();
			break;

		case CenterViewControllerAC.CREATE_RECIPE:
			principalView.changeCenterView(createRecipeView);
			BChefController.getInstance().stopVoiceRecon();
			break;

		case CenterViewControllerAC.RECIPE_STEP:
			principalView.changeCenterView(stepView);
			BChefController.getInstance().stopVoiceRecon();
			break;
		}
	}

	public void setRecipeView(Recipe recipe) {
		recipeView.setRecipe(recipe);
		principalView.changeCenterView(recipeView);
	}

	public void setVisitProfileView(User visitUser) {
		visitProfile.setVisitUser(visitUser);
		principalView.changeCenterView(visitProfile);
	}

	public void setStepView(Recipe recipe) {
		stepView.setRecipe(recipe);
		principalView.changeCenterView(stepView);
	}

	public void rateRecipe(Recipe recipe) {
		recipeRatingView.setRecipe(recipe);
		principalView.changeCenterView(recipeRatingView);
	}

	public void setProfileView() {
		profileView.updateView();
		principalView.changeCenterView(profileView);
	}

	public void setListView() {
		principalView.changeCenterView(listView);
	}

	public void setBChefView() {
		principalView.changeCenterView(bchefView);
	}

}
