package eus.healthit.bchef.core.controllers.view;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.view.panels.center.CenterViewVisitProfile;

public class ProfileVisitController implements IRoundButtonListener, IClickable {

	CenterViewVisitProfile visitProfile;
	CenterViewController centerController;

	public ProfileVisitController(CenterViewVisitProfile visitProfile, CenterViewController centerController) {
		this.visitProfile = visitProfile;
		this.centerController = centerController;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case ProfileVisitControllerAC.FOLLOW:
			System.out.println("FollowPressed");

			JSONCalls.follow(visitProfile.getUser().getId(), visitProfile.getVisitUser().getId());

			visitProfile.getVisitUser().addFollower();
			visitProfile.getUser().addFollowedUser(visitProfile.getVisitUser().getId());

			visitProfile.changeButtonStatus();

			visitProfile.updateView();

			break;
		case ProfileVisitControllerAC.UNFOLLOW:
			System.out.println("UnfollwPressed");

			JSONCalls.unfollow(visitProfile.getUser().getId(), visitProfile.getVisitUser().getId());

			visitProfile.getVisitUser().removeFollower();
			visitProfile.getUser().removeFollowedUser(visitProfile.getVisitUser().getId());

			visitProfile.changeButtonStatus();

			visitProfile.updateView();

			break;
		}
	}

	public void openSelectedRecipe() {
		try {
			centerController.setRecipeView(visitProfile.getSelectedRecipe());
		} catch (Exception e) {
		}
	}

	@Override
	public void clicked() {
		openSelectedRecipe();
	}

}
