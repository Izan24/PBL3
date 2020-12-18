package eus.healthit.bchef.core.models;

import java.util.List;

public class Ingredients {

	String name;
	List<Ingredients> replacements;

	public Ingredients(String name, List<Ingredients> replacements) {
		this.name = name;
		this.replacements = replacements;
	}
}
