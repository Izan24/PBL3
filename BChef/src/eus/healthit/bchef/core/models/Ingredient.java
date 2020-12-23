package eus.healthit.bchef.core.models;

import java.util.List;

public class Ingredient {

	String name;
	List<Ingredient> replacements;

	public Ingredient(String name, List<Ingredient> replacements) {
		this.name = name;
		this.replacements = replacements;
	}
}
