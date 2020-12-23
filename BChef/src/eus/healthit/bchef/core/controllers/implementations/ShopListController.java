package eus.healthit.bchef.core.controllers.implementations;

import eus.healthit.bchef.core.controllers.interfaces.IShopListController;
import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.view.panels.center.CenterShopListView;

public class ShopListController implements IShopListController {

	CenterShopListView shopListView;

	static ShopListController obj = new ShopListController();

	private ShopListController() {
	}

	public static ShopListController getShopListController() {
		return obj;
	}

	public void setShopListView(CenterShopListView shopListView) {
		this.shopListView = shopListView;
	}

	@Override
	public void addElement(String name) {
		shopListView.getListModel().addElement(new Item(name));
		//aqui falta mandar la info a la DB
	}
}