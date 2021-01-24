package eus.healthit.bchef.core.controllers.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.JScrollBar;

import eus.healthit.bchef.core.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewList;

public class CenterListController implements IClickable, AdjustmentListener {

	static CenterViewList viewList;
	CenterViewController centerController;

	public CenterListController(CenterViewList viewList, CenterViewController centerController) {
		this.viewList = viewList;
		this.centerController = centerController;
	}

	public static void setShowList(List<Recipe> recipesList) {
		viewList.getListModel().setList(recipesList);
		viewList.repaint();
		viewList.revalidate();
	}

	@Override
	public void clicked() {
		centerController.setRecipeView(viewList.getSelecterRecipe());
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		System.out.println("cammmbiooo");
	}

}
