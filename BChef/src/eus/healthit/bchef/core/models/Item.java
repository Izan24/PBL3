package eus.healthit.bchef.core.models;

public class Item {

	String name;
	boolean bought;

	public Item(String name) {
		this.name = name;
		bought = false;
	}

	public boolean isBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}

	public String getName() {
		return name;
	}

}
