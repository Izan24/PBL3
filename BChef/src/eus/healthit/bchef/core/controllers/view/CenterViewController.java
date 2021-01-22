package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.PrincipalView;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.panels.center.CenterView;
import eus.healthit.bchef.core.view.panels.center.CenterViewBchef;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewList;
import eus.healthit.bchef.core.view.panels.center.CenterViewProfile;
import eus.healthit.bchef.core.view.panels.center.CenterViewProfileSettings;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipeRating;
import eus.healthit.bchef.core.view.panels.center.CenterViewShopList;
import eus.healthit.bchef.core.view.panels.center.CenterViewStep;
import eus.healthit.bchef.core.view.panels.center.CenterViewVisitProfile;

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

	CenterView centerView;
	User user;

	public CenterViewController(PrincipalView principalView, CenterView centerView, User user,
			WindowFrameController windowController, WindowFrame window) {

		this.principalView = principalView;
		this.centerView = centerView;
		this.user = user;

		initViews(windowController, window);
	}

	private void initViews(WindowFrameController windowController, WindowFrame window) {
		listView = new CenterViewList(this);
		profileView = new CenterViewProfile(user, this);
		recipeView = new CenterViewRecipe(this, user);
		bchefView = new CenterViewBchef();
		shopListView = new CenterViewShopList(user);
		createRecipeView = new CenterViewCreateRecipe(user, window);
		stepView = new CenterViewStep(this, user);
		visitProfile = new CenterViewVisitProfile(user, this);
		settingsView = new CenterViewProfileSettings(user, windowController, window);
		recipeRatingView = new CenterViewRecipeRating(this);
	}

	public void setStartView() {
		principalView.changeCenterView(recipeRatingView);
//		try {
//
//			RecipeStep step = new RecipeStep(RecipeStepActions.OVEN, 100,
//					ImageIO.read(new File("resources/recipeIcons/calentarHorno.jpg")).getScaledInstance(200, 200,
//							Image.SCALE_SMOOTH),
//					"Calienta el horno durante 10 minutos hasta que el pollo se queme esto es una prueba para ver el "
//							+ "slide y a ver que tal va a ver si va bien porfa porfa porfa porfa parece que tengo que es"
//							+ "cribir un pooco mas para que se active a ver ahora",
//					1);
//			step.setId(1);

		try {
			List<RecipeStep> steps = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				RecipeStep step = new RecipeStep(RecipeStepActions.OVEN, 2,
						ImageIO.read(new File("resources/recipeIcons/calentarHorno.jpg")), "Esti es ek texto " + i, i);
				step.setId(i);
				Duration duration = Duration.ofSeconds(20);
				duration = duration.plusMinutes(90);
				duration = duration.plusHours(23);
				step.setDuration(duration);
				steps.add(step);
			}

			Recipe recipe = new Recipe(UUID.randomUUID(), "Prueba", "Rkolay", 2, "REceta de prueba woo", 10, null,
					null, steps, ImageIO.read(new File("resources/recipeIcons/recetaBonita.jpg")));
			recipeRatingView.setRecipe(recipe);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		case CenterControllerAC.SETTINGS:
			settingsView.updateView();
			principalView.changeCenterView(settingsView);
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

	public void setStepView(Recipe recipe) {
		stepView.setRecipe(recipe);
		principalView.changeCenterView(stepView);
	}

	public void rateRecipe(Recipe recipe) {
		recipeRatingView.setRecipe(recipe);
		principalView.changeCenterView(recipeRatingView);
	}

}
