package eus.healthit.bchef.core.models;

public class Stove {

	int power;
	boolean state;
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public int getPower() {
		return power;
	}
	
	public boolean getState() {
		return this.state;
	}
	
}
