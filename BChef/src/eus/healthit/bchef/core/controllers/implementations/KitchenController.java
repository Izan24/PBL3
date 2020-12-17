package eus.healthit.bchef.core.controllers.implementations;

import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.models.Kitchen;

public class KitchenController implements IKitchenController {

	Kitchen kitchen;
	
	@Override
	public void setFire(int index, int power) {
		kitchen.setFire(index, power);
	}

	@Override
	public void setOven(double temp) {
		kitchen.setOven(temp);
		
	}

}
