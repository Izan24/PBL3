package eus.healthit.bchef.core.controllers.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewList;

public class CenterListController implements IClickable, AdjustmentListener {

	static CenterViewList viewList;
	CenterViewController centerController;
	static int actualPage;

	public CenterListController(CenterViewList viewList, CenterViewController centerController) {
		this.viewList = viewList;
		this.centerController = centerController;
		actualPage = 0;
	}

	public static void setShowList(List<Recipe> recipesList) {
		viewList.getListModel().setList(recipesList);
		actualPage = 0;
		viewList.getVerticalScrollBar().setValue(0);
		viewList.repaint();
		viewList.revalidate();
	}

	@Override
	public void clicked() {
		centerController.setRecipeView(viewList.getSelecterRecipe());
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		int extent = viewList.getVerticalScrollBar().getModel().getExtent();
		int actual = (viewList.getVerticalScrollBar().getValue() + extent);
		int max = viewList.getVerticalScrollBar().getMaximum();

		if (actual == max) {
			if (checkPage()) {

				List<Recipe> searchList = JSONCalls.getPage(actualPage + 1);
				int recipeSize = max / viewList.getListModel().getSize();

				viewList.getVerticalScrollBar().setValue(max - (searchList.size() * recipeSize));

				searchList.stream().forEach((recipe) -> viewList.getListModel().addElement(recipe));
				viewList.repaint();
				viewList.revalidate();
				actualPage++;
			}
		}
	}

	private boolean checkPage() {
		int actualListSize = viewList.getListModel().getList().size();

		if (actualListSize % 7 == 0) {
			return true;
		}
		return false;
	}
}
