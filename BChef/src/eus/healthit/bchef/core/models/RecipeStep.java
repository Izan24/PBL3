package eus.healthit.bchef.core.models;

import eus.healthit.bchef.core.enums.RecipeStepActions;

public class RecipeStep {

	Long id;
	RecipeStepActions action;
	int value;
	String imageURL;
	String text;
	long num;

	public RecipeStep(long id, RecipeStepActions action, int value, String imageURL, String text, long num) {
		this.id = id;
		this.action = action;
		this.value = value;
		this.imageURL = imageURL;
		this.text = text;
		this.num = num;
	}

	public RecipeStep(RecipeStepActions action, int value, String imageURL, String text, long num) {
		this.id = null;
		this.action = action;
		this.value = value;
		this.imageURL = imageURL;
		this.text = text;
		this.num = num;
	}

	public RecipeStep(RecipeStepActions action, int value, String imageURL, String text) {
		this.id = null;
		this.action = action;
		this.value = value;
		this.imageURL = imageURL;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RecipeStepActions getAction() {
		return action;
	}

	public void setAction(RecipeStepActions action) {
		this.action = action;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public long getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}

}
