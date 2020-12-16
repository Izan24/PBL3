package edu.healthit.bchef.core.models;

public class Kitchen {

	Stove [] stoves;
	Oven oven;
	
	public boolean setFire(int index, int power) {
		if(power < 0) return false;
		stoves[index].setState((power) > 0 ? true : false);
		stoves[index].setPower(power);
		return true;
	}
	
	public boolean setOven(double temp) {
		if(temp < 0) return false;
		oven.setState((temp) > 0 ? true : false);
		oven.setTemp(temp);
		return true;
	}
	
}
