package eus.healthit.bchef.core.models;

public class Item {

	int id;
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

	public void flipBought() {
		bought = !bought;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

}
