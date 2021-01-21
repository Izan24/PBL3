package eus.healthit.bchef.core.models;

import java.awt.Image;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

public class Recipe {

	String name, author;
	int authorID;
	String description;
	int rating; // 0 to 10 in 5 stars
	Timestamp publishDate;
	Time duration;
	String imagePath;
	List<Ingredient> ingredients;
	List<RecipeStep> steps;

	Image image;
	UUID uuid;

	@SuppressWarnings("deprecation")
	public Recipe(UUID uuid, String name, String author, int rating, Timestamp publishDate, Time duration,
			List<Ingredient> ingredients, List<RecipeStep> steps, Image image) {
		this.uuid = uuid;
		this.name = name;
//		this.description = description;
		this.author = author;
		// this.fullAuthor = fullAuthor;
		this.rating = rating;
		this.publishDate = publishDate;
		this.duration = duration;
		this.ingredients = ingredients;
		this.steps = steps;
		this.image = image;
	}

	public Recipe(UUID uuid, String name, String author, int authorID, String description, int rating,
			Timestamp publishDate, Time duration, List<Ingredient> ingredients, List<RecipeStep> steps, Image image) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.author = author;
		this.authorID = authorID;
		this.rating = rating;
		this.publishDate = publishDate;
		this.duration = duration;
		this.ingredients = ingredients;
		this.steps = steps;
		this.image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	}

	public Recipe(String name, String author, int authorID, String description, int rating,
			List<Ingredient> ingredients, List<RecipeStep> steps, Image image) {
		this.name = name;
		this.author = author;
		this.authorID = authorID;
		this.rating = rating;
		this.ingredients = ingredients;
		this.steps = steps;
		this.image = image;

	}

	public Recipe(String title, String author, int id, String description2, List<Ingredient> list,
			List<RecipeStep> list2, String imagePath2) {
		this.name = title;
		this.author = author;
		this.authorID = id;
		this.rating = 0;
		this.ingredients = list;
		this.steps = list2;
		this.imagePath = imagePath2;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getIngredientNumber() {
		try {
			return ingredients.size();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	public int getStepNumber() {
		try {
			return this.steps.size();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		Recipe o = (Recipe) obj;
		return uuid.equals(o.getUUID());
	}

	@Override
	public String toString() {
		return name + author;
	}
}
