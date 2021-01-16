package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.PrincipalView;
import eus.healthit.bchef.core.view.panels.center.CenterStepView;
import eus.healthit.bchef.core.view.panels.center.CenterView;
import eus.healthit.bchef.core.view.panels.center.CenterViewBchef;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewList;
import eus.healthit.bchef.core.view.panels.center.CenterViewProfile;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewShopList;
import eus.healthit.bchef.core.view.panels.center.CenterViewVisitProfile;

public class CenterViewController implements ActionListener {

	PrincipalView principalView;

	CenterViewList listView;
	CenterViewProfile profileView;
	CenterViewRecipe recipeView;
	CenterViewBchef bchefView;
	CenterViewShopList shopListView;
	CenterViewCreateRecipe createRecipeView;
	CenterStepView stepView;
	CenterViewVisitProfile visitProfile;

	CenterView centerView;
	User user;

	public CenterViewController(PrincipalView principalView, CenterView centerView, User user) {
		this.principalView = principalView;
		this.centerView = centerView;
		this.user = user;

		initViews();
	}

	private void initViews() {
		listView = new CenterViewList(this);
		profileView = new CenterViewProfile(user, this);
		recipeView = new CenterViewRecipe(this, user);
		bchefView = new CenterViewBchef();
		shopListView = new CenterViewShopList(user);
		createRecipeView = new CenterViewCreateRecipe(user);
		stepView = new CenterStepView();
		visitProfile = new CenterViewVisitProfile(user);
	}

	public void setStartView() {
		principalView.changeCenterView(listView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case CenterControllerAC.HOME:
			principalView.changeCenterView(listView);
			break;

		case CenterControllerAC.LIST:
			principalView.changeCenterView(shopListView);
			break;

		case CenterControllerAC.PROFILE:
			profileView.updateView();
			principalView.changeCenterView(profileView);
			break;

		case CenterControllerAC.PROFILE_VISIT:
			principalView.changeCenterView(visitProfile);
			break;

		case CenterControllerAC.BCHEF:
			principalView.changeCenterView(bchefView);
			break;

		case CenterControllerAC.CREATE_RECIPE:
			principalView.changeCenterView(createRecipeView);
			break;

		case CenterControllerAC.RECIPE_STEP:
			principalView.changeCenterView(stepView);
			break;
		}
	}

	public void setRecipeView(Recipe recipe) {
		recipeView.setRecipe(recipe);
		principalView.changeCenterView(recipeView);
	}

	public void setVisitProfileView(User visitUser) {
		visitProfile.setVisitUser(visitUser);
		System.out.println("Tienes que usar el metodo setVIsitUser para setear todas las cosas que te pasa con el ");
		principalView.changeCenterView(visitProfile);
	}

	public void setStepView(RecipeStep step) {
		stepView.setStep(step);
		principalView.changeCenterView(stepView);
	}

}
