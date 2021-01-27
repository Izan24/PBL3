package eus.healthit.bchef.core.app.controllers.profile.visit;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewVisitProfile;

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

			JSONCalls.follow(visitProfile.getUser().getId(), visitProfile.getVisitUser().getId());

			visitProfile.getVisitUser().addFollower();
			visitProfile.getUser().addFollowedUser(visitProfile.getVisitUser().getId());

			visitProfile.changeButtonStatus();

			visitProfile.updateView();

			break;
		case ProfileVisitControllerAC.UNFOLLOW:

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
