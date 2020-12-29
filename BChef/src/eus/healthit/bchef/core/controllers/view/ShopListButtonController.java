package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.view.panels.center.CenterViewShopList;

public class ShopListButtonController implements ActionListener {

	CenterViewShopList listView;

	public ShopListButtonController(CenterViewShopList listView) {
		this.listView = listView;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case ShopListControllerAC.ADD:
			listView.addElement();
			break;
		case ShopListControllerAC.REMOVE:
			listView.removeElement();
			break;
		}
	}
}
