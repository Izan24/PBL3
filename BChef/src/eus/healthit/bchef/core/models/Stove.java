package eus.healthit.bchef.core.models;

public class Stove {

	int power;
	boolean state;
	String id;
	
	public Stove(String id) {
		this.id = id;
		this.state = false;
		this.power = 0;
	}

	public Stove(String id, boolean state, int power) {
		this.id = id;
		this.state = state;
		this.power = power;
	}
	
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
	
	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Oven))
			return false;
		Stove stove = (Stove) obj;
		return stove.getId().equals(this.id);
	}
	
}
