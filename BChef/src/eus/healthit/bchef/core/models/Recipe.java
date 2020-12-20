package eus.healthit.bchef.core.models;

import java.awt.Image;
import java.sql.Time;
import java.util.List;

import javax.swing.ImageIcon;

public class Recipe {

	String name, author;
	int rating; // 0 to 10 in 5 stars
	// dayTime releasedate;
	Time publishDate, duration;
	List<Ingredient> ingredients;
	List<RecipeStep> steps;
	ImageIcon image;

	public Recipe(String name, String author, int rating, Time publishDate, Time duration,
			List<Ingredient> ingredients, List<RecipeStep> steps, ImageIcon image) {

		this.name = name;
		this.author = author;
		this.rating = rating;
		this.publishDate = publishDate;
		this.duration = duration;
		this.ingredients = ingredients;
		this.steps = steps;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Time getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Time publishDate) {
		this.publishDate = publishDate;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<RecipeStep> getSteps() {
		return steps;
	}

	public void setSteps(List<RecipeStep> steps) {
		this.steps = steps;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

}
