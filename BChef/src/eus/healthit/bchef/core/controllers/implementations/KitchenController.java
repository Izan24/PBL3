package eus.healthit.bchef.core.controllers.implementations;

import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.models.Kitchen;

public class KitchenController implements IKitchenController {

	Kitchen kitchen;

    static KitchenController obj = new KitchenController();

    private KitchenController() {
    	kitchen = new Kitchen();
    }

    public static KitchenController getKitchenController() {
    	
        return obj;
    }
	
	@Override
	public void setFire(int id, int value) {
		kitchen.setFire(id, value);
	}

	@Override
	public void setOven(int id, int value) {
		kitchen.setOven(id, value);
		
	}

}
