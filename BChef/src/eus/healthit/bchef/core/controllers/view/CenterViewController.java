package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.PrincipalView;
import eus.healthit.bchef.core.view.panels.center.CenterBchefView;
import eus.healthit.bchef.core.view.panels.center.CenterShopListView;
import eus.healthit.bchef.core.view.panels.center.CenterView;
import eus.healthit.bchef.core.view.panels.center.CenterViewList;
import eus.healthit.bchef.core.view.panels.center.CenterViewProfile;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;

public class CenterViewController implements ActionListener {

	PrincipalView principalView;

	CenterViewList listView;
	CenterViewProfile profileView;
	CenterViewRecipe recipeView;
	CenterBchefView bchefView;
	CenterShopListView shopListView;

	CenterView centerView;
	User user;

	public CenterViewController(PrincipalView principalView, CenterView centerView, User user) {
		this.principalView = principalView;
		this.centerView = centerView;
		this.user = user;

		initViews();
	}

	private void initViews() {
		listView = new CenterViewList();
		profileView = new CenterViewProfile(user, centerView);
		recipeView = new CenterViewRecipe();
		bchefView = new CenterBchefView();
		shopListView = new CenterShopListView(user);
	}
	
	public void setStartView() {
		principalView.changeCenterView(listView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case CenterControllerAC.HOME:
			principalView.changeCenterView(listView);
			break;
			
		case CenterControllerAC.LIST:
			principalView.changeCenterView(shopListView);
			break;
			
		case CenterControllerAC.PROFILE:
			principalView.changeCenterView(profileView);
			break;
			
		case CenterControllerAC.BCHEF:
			principalView.changeCenterView(bchefView);
			break;
		}
	}
	
	public void setRecipeView(Recipe recipe) {
		recipeView.setRecipe(recipe);
		principalView.changeCenterView(recipeView);

	}

}
