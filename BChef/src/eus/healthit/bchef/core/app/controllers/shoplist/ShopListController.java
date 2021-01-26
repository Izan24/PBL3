package eus.healthit.bchef.core.app.controllers.shoplist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.controllers.interfaces.IShopListController;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewShopList;
import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.models.Item;

public class ShopListController
		implements ActionListener, IShopListController, IRoundButtonListener, PropertyChangeListener {

	CenterViewShopList shopListView;

	static ShopListController obj = new ShopListController();

	private ShopListController() {
		BChefController.getInstance().addPropertyChangeListener(this);
	}

	public static ShopListController getShopListController() {
		return obj;
	}

	public void setShopListView(CenterViewShopList shopListView) {
		this.shopListView = shopListView;

	}

	@Override
	public void addElement(String name) {

		String spaceless = name.trim();

		if (!spaceless.equals("") && !name.equals("Nuevo elemento")) {
			shopListView.getListModel().addElement(new Item(name));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ShopListControllerAC.ADD:

			if (checkItem()) {
				Item newItem = new Item(shopListView.getNewElementname());
				newItem.setID(JSONCalls.shoplistAdd(newItem, shopListView.getUserID()));
				System.out.println(newItem.getId());
				shopListView.getListModel().addElement(newItem);
				shopListView.resetText();
			}
			break;
		}
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {

		case ShopListControllerAC.REMOVE:
			try {
				JSONCalls.shoplistRemove(shopListView.getSelectedItem());
				shopListView.getListModel().deleteElement(shopListView.getSelectedItem());
			} catch (IndexOutOfBoundsException ex) {
			}
			break;
		}
	}

	private boolean checkItem() {
		String trimmedString = shopListView.getNewElementname().trim();
		if (trimmedString.equals("") || trimmedString.equals(CenterViewShopList.DEFAULT_ADD_ELEMENT_TEXT)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "LIST_ADD":
			shopListView.getListModel().addElement((Item) evt.getNewValue());
			break;
		case "LIST_REMOVE":
			shopListView.getListModel().deleteElement((Item) evt.getNewValue());
			break;
		default:
			break;
		}

	}
}