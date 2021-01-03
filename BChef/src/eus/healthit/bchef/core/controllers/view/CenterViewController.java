package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.PrincipalView;
import eus.healthit.bchef.core.view.panels.center.CenterView;
import eus.healthit.bchef.core.view.panels.center.CenterViewBchef;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewList;
import eus.healthit.bchef.core.view.panels.center.CenterViewProfile;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewShopList;

public class CenterViewController implements ActionListener {

	PrincipalView principalView;

	CenterViewList listView;
	CenterViewProfile profileView;
	CenterViewRecipe recipeView;
	CenterViewBchef bchefView;
	CenterViewShopList shopListView;
	CenterViewCreateRecipe createRecipeView;

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
		profileView = new CenterViewProfile(user);
		recipeView = new CenterViewRecipe();
		bchefView = new CenterViewBchef();
		shopListView = new CenterViewShopList(user);
		createRecipeView = new CenterViewCreateRecipe(user);
	}

	public void setStartView() {
		principalView.changeCenterView(recipeView);

		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Pan", "2 barras"));

		List<RecipeStep> steps = new ArrayList<>();
		steps.add(new RecipeStep("Prepara un pollo", 12, null));

		recipeView.setRecipe(new Recipe("Pollo a la bielorrusia ", "Izan", (int) (Math.random() * 10) + 1, null, null,
				ingredients, steps, new ImageIcon("resources/recipeIcons/pollo-asado.jpg")));
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

		case CenterControllerAC.CREATE_RECIPE:
			principalView.changeCenterView(createRecipeView);
			break;
		}
	}

	public void setRecipeView(Recipe recipe) {
		recipeView.setRecipe(recipe);
		principalView.changeCenterView(recipeView);
	}

}
