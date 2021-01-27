package eus.healthit.bchef.core.app.controllers.recipe.view;

import java.util.ResourceBundle;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewRecipe;
import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;

public class RecipeViewController implements IRoundButtonListener, IClickable {

	CenterViewController centerController;
	CenterViewRecipe centerViewRecipe;
	User user;

	public RecipeViewController(CenterViewRecipe centerViewRecipe, User user, CenterViewController centerController) {
		this.centerViewRecipe = centerViewRecipe;
		this.user = user;
		this.centerController = centerController;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case RecipeViewControllerAC.SAVE:
			if (user.getSaved().contains(centerViewRecipe.getRecipe())) {
				user.getSaved().remove(centerViewRecipe.getRecipe());
				centerViewRecipe.updateView(centerViewRecipe.getRecipe());
				JSONCalls.unsave(user.getId(), centerViewRecipe.getRecipe().getUUID());
			} else {
				user.getSaved().add(centerViewRecipe.getRecipe());
				centerViewRecipe.updateView(centerViewRecipe.getRecipe());
				JSONCalls.save(user.getId(), centerViewRecipe.getRecipe().getUUID());
			}
			break;
		case RecipeViewControllerAC.START:
			Recipe recipe = centerViewRecipe.getRecipe();
			centerController.setStepView(recipe);
			System.out.println("action performed START");
			BChefController controller = BChefController.getInstance();
			controller.startVoiceRecon();
			System.out.println("entrando en  ----------------------->");
			controller.startRecipe(recipe);
			System.out.println("<---------------------------- saliendo de");

			break;
		}
	}

	@Override
	public void clicked() {
		if (JSONCalls.getUserById(centerViewRecipe.getRecipe().getAuthorID()) != null) {
			if (!JSONCalls.getUserById(centerViewRecipe.getRecipe().getAuthorID()).equals(user)) {
				centerController.setVisitProfileView(JSONCalls.getUserById(centerViewRecipe.getRecipe().getAuthorID()));
			} else {
				centerController.setProfileView();
			}
		} else {
			System.out.println("User not found");
		}
	}
}
