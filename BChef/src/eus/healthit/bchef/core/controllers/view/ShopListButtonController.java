package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.api.JSONParser;
import eus.healthit.bchef.core.models.Item;
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

			if (checkItem()) {
				Item newItem = new Item(listView.getNewElementname());
				newItem.setID(JSONParser.shoplistAdd(newItem, listView.getUserID()));
				listView.getListModel().addElement(newItem);
			}

			break;
		case ShopListControllerAC.REMOVE:
			try {
				JSONParser.shoplistRemove(listView.getSelectedItem());
				listView.getListModel().deleteElement(listView.getSelectedItem());
			} catch (IndexOutOfBoundsException ex) {
			}
			break;
		}
	}

	private boolean checkItem() {
		if (listView.getNewElementname().trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}
}
