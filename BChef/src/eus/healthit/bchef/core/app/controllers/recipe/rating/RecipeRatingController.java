package eus.healthit.bchef.core.app.controllers.recipe.rating;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewRecipeRating;
import eus.healthit.bchef.core.models.User;

public class RecipeRatingController implements ChangeListener, IRoundButtonListener {

	CenterViewRecipeRating ratingView;
	CenterViewController centerController;

	User user;

	public RecipeRatingController(CenterViewRecipeRating ratingView, CenterViewController centerController, User user) {
		this.ratingView = ratingView;
		this.centerController = centerController;
		this.user = user;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();
		ratingView.setStarView(slider.getValue());
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case RecipeRatingControllerAC.SUBMIT_RATING:
			JSONCalls.rateRecipe(user.getId(), ratingView.getRecipe().getUUID(), ratingView.getRating());
			centerController.setBChefView();
			break;
		case RecipeRatingControllerAC.SKIP_RATING:
			centerController.setBChefView();
			break;

		default:
			return;
		}
	}

}
