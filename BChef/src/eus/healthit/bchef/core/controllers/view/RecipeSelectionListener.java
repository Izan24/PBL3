package eus.healthit.bchef.core.controllers.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import eus.healthit.bchef.core.view.panels.center.CenterViewList;

public class RecipeSelectionListener extends MouseAdapter {
	
	CenterViewList centerViewList;

	public RecipeSelectionListener(CenterViewList centerViewList) {
		this.centerViewList = centerViewList;
	}

	public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            e.consume();
            centerViewList.openRecipeView();
        }
    }
}
