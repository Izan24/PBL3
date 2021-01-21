package eus.healthit.bchef.core.controllers.interfaces;

public interface IKitchenController {

	public abstract void setFire(int index, int power);
	
	public abstract void setOven(int index, int temp);
	
}
