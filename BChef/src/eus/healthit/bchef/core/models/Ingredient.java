package eus.healthit.bchef.core.models;

public class Ingredient {

	Integer id;
	String name;

	String type;
	String quantity;

	public Ingredient(int id, String name, String type, String amount) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.quantity = amount;
	}

	public Ingredient(Integer id, String name, String type) {
		this.name = name;
		this.type = type;
		this.id = id;
	}

	public Ingredient(String name, String type, String amount) {
		this.id = null;
		this.name = name;
		this.type = type;
		this.quantity = amount;
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

	public String getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setQuantity(String ingredientQuantity) {
		this.quantity = ingredientQuantity;
		
	}
}
