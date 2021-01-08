package eus.healthit.bchef.core.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class Recipe {

	String name, author;
	int rating; // 0 to 10 in 5 stars
	Timestamp publishDate;
	Time duration;
	List<Ingredient> ingredients;
	List<RecipeStep> steps;

	String imageURL;
	UUID uuid;

	public Recipe(UUID uuid, String name, String author, int rating, Timestamp publishDate, Time duration,
			List<Ingredient> ingredients, List<RecipeStep> steps, String imageUrl) {
		this.uuid = uuid;
		this.name = name;
		this.author = author;
		this.rating = rating;
		this.publishDate = publishDate;
		this.duration = duration;
		this.ingredients = ingredients;
		this.steps = steps;
		this.imageURL = imageUrl;
	}

	public UUID getUUID() {
		return uuid;
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

	public Timestamp getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Timestamp publishDate) {
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImage(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getIngredientNumber() {
		try {
			return ingredients.size();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	public int getStepCount() {
		try {
			return this.steps.size();						
		} catch (NullPointerException e) {
			return 0;
		}
	}

}
