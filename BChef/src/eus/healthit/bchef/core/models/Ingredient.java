package eus.healthit.bchef.core.models;

public class Ingredient {

	Long id;
	String name;
	String type;
	String amount;

	public Ingredient(long id, String name, String type, String amount) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.amount = amount;
	}
	
	public Ingredient(String name, String type, String amount) {
		this.id = null;
		this.name = name;
		this.type = type;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getAmount() {
		return amount;
	}
}
