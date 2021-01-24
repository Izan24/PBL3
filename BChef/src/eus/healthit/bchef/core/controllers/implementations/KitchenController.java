package eus.healthit.bchef.core.controllers.implementations;

import java.util.List;

import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.models.Kitchen;
import eus.healthit.bchef.core.models.Oven;
import eus.healthit.bchef.core.models.Stove;

public class KitchenController implements IKitchenController {

	Kitchen kitchen;

	public KitchenController() {
		kitchen = new Kitchen();
	}

	@Override
	public void setStove(int index, int power) {
		kitchen.setStove(index, power);
	}

	@Override
	public void setOven(int index, int temp) {
		kitchen.setOven(index, temp);
	}

	@Override
	public Kitchen getKitchen() {
		return kitchen;
	}

	@Override
	public void setOvens(List<Oven> ovens) {
		kitchen.setOvens(ovens);
	}

	@Override
	public void setStoves(List<Stove> stoves) {
		kitchen.setStoves(stoves);
	}

}
