package eus.healthit.bchef.core.controllers.view.recipeRating;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.controllers.view.centerView.CenterViewController;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipeRating;

public class RecipeRatingController implements ChangeListener, IRoundButtonListener {

	CenterViewRecipeRating ratingView;
	CenterViewController centerController;

	public RecipeRatingController(CenterViewRecipeRating ratingView, CenterViewController centerController) {
		this.ratingView = ratingView;
		this.centerController = centerController;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();
		ratingView.setStarView(slider.getValue());
		System.out.println(slider.getValue());

	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case RecipeRatingControllerAC.SUBMIT_RATING:
			System.out.println("Rated");
			break;
		case RecipeRatingControllerAC.SKIP_RATING:
			System.out.println("rating skipped");
			break;

		default:
			break;
		}
	}

}
