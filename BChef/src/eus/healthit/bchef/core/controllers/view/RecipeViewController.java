package eus.healthit.bchef.core.controllers.view;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;

public class RecipeViewController implements IRoundButtonListener {

	CenterViewRecipe centerViewRecipe;
	User user;

	public RecipeViewController(CenterViewRecipe centerViewRecipe, User user) {
		this.centerViewRecipe = centerViewRecipe;
		this.user = user;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case RecipeViewControllerAC.SAVE:
			if (user.getSaved().contains(centerViewRecipe.getRecipe())) {
				System.out.println("borrando");
				user.getSaved().remove(centerViewRecipe.getRecipe());
				centerViewRecipe.updateView(centerViewRecipe.getRecipe());
			} else {
				System.out.println("añadiendo");
				user.getSaved().add(centerViewRecipe.getRecipe());
				centerViewRecipe.updateView(centerViewRecipe.getRecipe());
			}
			break;
		}
	}

}
