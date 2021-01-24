package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.api.JSONCalls;
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
				newItem.setID(JSONCalls.shoplistAdd(newItem, listView.getUserID()));
				System.out.println(newItem.getId());
				listView.getListModel().addElement(newItem);
				listView.resetText();
			}

			break;
		case ShopListControllerAC.REMOVE:
			try {
				JSONCalls.shoplistRemove(listView.getSelectedItem());
				listView.getListModel().deleteElement(listView.getSelectedItem());
			} catch (IndexOutOfBoundsException ex) {
			}
			break;
		}
	}

	private boolean checkItem() {
		String trimmedString = listView.getNewElementname().trim();
		if (trimmedString.equals("") || trimmedString.equals(CenterViewShopList.DEFAULT_ADD_ELEMENT_TEXT)) {
			return false;
		} else {
			return true;
		}
	}
}
