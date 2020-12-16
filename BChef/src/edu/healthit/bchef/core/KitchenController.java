package edu.healthit.bchef.core;

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
