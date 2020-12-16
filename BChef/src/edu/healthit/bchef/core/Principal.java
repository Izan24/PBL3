package edu.healthit.bchef.core;

import edu.healthit.bchef.core.controllers.kitchen.IKitchenController;
import edu.healthit.bchef.core.controllers.kitchen.KitchenController;

public class Principal {

	public static void main(String[] args) {
		IKitchenController kitchenController = new KitchenController();
	}

}
