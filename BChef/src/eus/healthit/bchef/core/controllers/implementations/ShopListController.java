package eus.healthit.bchef.core.controllers.implementations;

import eus.healthit.bchef.core.controllers.interfaces.IShopListController;
import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.view.panels.center.CenterViewShopList;

public class ShopListController implements IShopListController {

	CenterViewShopList shopListView;

	static ShopListController obj = new ShopListController();

	private ShopListController() {
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
}