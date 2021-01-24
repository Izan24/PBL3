package eus.healthit.bchef.core.models;

public class Oven {

	int temp;
	boolean state;
	String id;

	public Oven(String id) {
		this.id = id;
		this.state = false;
		this.temp = 0;
	}
	
	public Oven(String id, boolean state, int temp) {
		this.id = id;
		this.state = state;
		this.temp = temp;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
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
		Oven oven = (Oven) obj;
		return oven.getId().equals(this.id);
	}

	public boolean getState() {
		return this.state;
	}

}
