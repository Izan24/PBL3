package eus.healthit.bchef.core.controllers.view;

import java.util.List;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewList;

public class CenterListController {

	static CenterViewList viewList;

	public CenterListController(CenterViewList viewList) {
		this.viewList = viewList;
	}

	public static void setShowList(List<Recipe> recipesList) {
		viewList.getListModel().setList(recipesList);
		viewList.repaint();
	}

}
