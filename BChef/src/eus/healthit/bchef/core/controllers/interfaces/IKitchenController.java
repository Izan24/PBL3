package eus.healthit.bchef.core.controllers.interfaces;

public interface IKitchenController {

	public abstract void setFire(int id, int power);
	
	public abstract void setOven(int id, int temp);
	
}
