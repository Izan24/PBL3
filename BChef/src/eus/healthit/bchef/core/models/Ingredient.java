package eus.healthit.bchef.core.models;

import java.util.List;

public class Ingredient {

	String name;
	String quantity;
	List<Ingredient> replacements;
	
	public Ingredient(String name, String quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public String getQuantity() {
		return quantity;
	}

	public List<Ingredient> getReplacements() {
		return replacements;
	}

	public Ingredient(String name, List<Ingredient> replacements) {
		this.name = name;
		this.replacements = replacements;
	}

	@Override
	public String toString() {
		return name;
	}
}
