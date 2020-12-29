package eus.healthit.bchef.core.controllers.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import eus.healthit.bchef.core.view.panels.center.CenterViewShopList;

public class ShopListSelectionListener extends MouseAdapter{
	
	CenterViewShopList view;
	
	public ShopListSelectionListener(CenterViewShopList view) {
		this.view = view;
	}

	public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            e.consume();
            view.checkItem();
        }
    }
}
