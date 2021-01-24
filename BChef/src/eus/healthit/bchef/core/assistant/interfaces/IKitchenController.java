package eus.healthit.bchef.core.assistant.interfaces;

import java.util.List;

import eus.healthit.bchef.core.models.Kitchen;
import eus.healthit.bchef.core.models.Oven;
import eus.healthit.bchef.core.models.Stove;

public interface IKitchenController {

	public abstract void setStove(int index, int power);

	public abstract void setOven(int index, int temp);

	public abstract Kitchen getKitchen();

	public abstract void setOvens(List<Oven> ovens);

	public abstract void setStoves(List<Stove> stoves);
}
