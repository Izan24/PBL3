package eus.healthit.bchef.core.models;

import java.awt.Image;
import java.time.Duration;

import eus.healthit.bchef.core.enums.RecipeStepActions;

public class RecipeStep {

	Integer id;
	RecipeStepActions action;
	int value;
	Image image;
	String text;
	int num;
	Duration time;
	String imagePath;

	public RecipeStep(Integer id, RecipeStepActions action, int value, Image image, String text, int num) {
		this.id = id;
		this.action = action;
		this.value = value;
		this.image = image;
		this.text = text;
		this.num = num;
	}

	public RecipeStep(RecipeStepActions action, int value, Image image, String text, int num) {
		this.id = null;
		this.action = action;
		this.value = value;
		this.image = image;
		this.text = text;
		this.num = num;
	}

	public RecipeStep(RecipeStepActions action, int value, Image image, String text) {
		this.id = null;
		this.action = action;
		this.value = value;
		this.image = image;
		this.text = text;
	}

	public RecipeStep(RecipeStepActions action, int value, String imagePath, String text, int num) {
		this.id = null;
		this.action = action;
		this.value = value;
		this.imagePath = imagePath;
		this.text = text;
		this.num = num;
	}

	public Duration getDuration() {
		return time;
	}

	public void setDuration(Duration duration) {
		this.time = duration;
	}

	public String getImagePath() {
		return imagePath;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public int getNum() {
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

	public Duration getTime() {
		return time;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return text;
	}

}
