package eus.healthit.bchef.core.models;

import java.awt.Image;

import eus.healthit.bchef.core.enums.RecipeStepActions;

public class RecipeStep {


	Integer id;
	RecipeStepActions action;
	int value;
	String imagePath;
	Image image;
    String text;
    Integer num;

    public RecipeStep(int id, RecipeStepActions action, int value, Image image, String text, int num) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RecipeStepActions getAction() {
		return action;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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

  @Override
	public String toString() {
		return text;
	}
}
