package eus.healthit.bchef.core.api;

import java.awt.Image;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;

public class JSONutils {
	public static User getUser(JSONObject jsonReturn) {
		int id = jsonReturn.getInt("id");
		String name = jsonReturn.getString("name");
		String surname = jsonReturn.getString("surname");
		Image profilepic = ImageRepository.decodeImage(jsonReturn.getString("profilepic"));
		String email = jsonReturn.getString("email");
		User user = new User(id, name, surname, profilepic, email);

		JSONArray arrayJSON = jsonReturn.getJSONArray("followed");
		user.setFollowed(getFollowers(arrayJSON));
		user.setFollowers(jsonReturn.getInt("followers"));
		arrayJSON = jsonReturn.getJSONArray("published");

		for (Object obj : arrayJSON) {
			JSONObject published = (JSONObject) obj;
			user.addPublication(getRecipe(published));
		}

		arrayJSON = jsonReturn.getJSONArray("saved");
		for (Object obj : arrayJSON) {
			JSONObject saved = (JSONObject) obj;
			user.addSaved(getRecipe(saved));
		}

		arrayJSON = jsonReturn.getJSONArray("history");
		for (Object obj : arrayJSON) {
			JSONObject history = (JSONObject) obj;
			user.addHistory(getRecipe(history));
		}

		arrayJSON = jsonReturn.getJSONArray("shopList");
		for (Object obj : arrayJSON) {
			JSONObject item = (JSONObject) obj;
			user.addShopElement(getListItem(item));
		}

		return user;
	}

	public static Item getListItem(JSONObject item) {
		String name = item.getString("name");
		Item itemShop = new Item(name);
		return itemShop;
	}

	public static List<User> getFollowers(JSONArray arrayJSON) {
		List<User> followers = new ArrayList<>();
		for (Object obj : arrayJSON) {
			followers.add(getSimpleUser((JSONObject) obj));
		}

		return followers;
	}

	public static User getSimpleUser(JSONObject followedUser) {
		int id = Integer.parseInt(followedUser.getString("id"));
		String name = followedUser.getString("name");
		String surname = followedUser.getString("surname");
		Image profilepic = ImageRepository.decodeImage(followedUser.getString("profilepic"));
		String email = followedUser.getString("email");
		User user = new User(id, name, surname, profilepic, email);

		return user;
	}

	public static Recipe getRecipe(JSONObject published) {
		UUID uuid = UUID.fromString(published.getString("uuid"));
		String name2 = published.getString("name");
		int authorID = published.getInt("author");
		String author = API.getUserName(authorID).getString("username");
		String description = published.getString("description");
		int rating = published.getInt("rating");
		Timestamp publishDate = Timestamp.valueOf(published.getString("publish_date"));
		Time duration = Time.valueOf(published.getString("duration"));
		List<Ingredient> ingredientes = new ArrayList<>();

		JSONArray arrayJson2 = published.getJSONArray("ingredients");
		for (Object obj2 : arrayJson2) {
			JSONObject ingrediente = (JSONObject) obj2;
			int id2 = ingrediente.getInt("id");
			String nameI = ingrediente.getString("name");
			String type = ingrediente.getString("type");
			String amount = ingrediente.getString("amount");
			Ingredient ingre = new Ingredient(id2, nameI, type, amount);
			ingredientes.add(ingre);
		}

		List<RecipeStep> listaPasos = new ArrayList<>();
		arrayJson2 = published.getJSONArray("instructions");
		for (Object obj2 : arrayJson2) {
			JSONObject step = (JSONObject) obj2;
			int idS = step.getInt("id");
			RecipeStepActions recipeS = RecipeStepActions.valueOf(step.getString("action"));
			int value = step.getInt("value");
			Image image = ImageRepository.decodeImage(step.getString("image"));
			String text = step.getString("text");
			int num = step.getInt("num");
			listaPasos.add(new RecipeStep(idS, recipeS, value, image, text, num));
		}

		Image image = ImageCoder.decodeImage(published.getString("image"));
		return new Recipe(uuid, name2, author, authorID, description, rating, publishDate, duration, ingredientes,
				listaPasos, image);
	}

	public static List<JSONObject> instructionsToJSON(List<RecipeStep> instructions) {
		List<JSONObject> JSONlist = new ArrayList<>();

		for (RecipeStep step : instructions) {
			JSONObject ste = new JSONObject();
			ste.put("id", step.getId()).put("action", step.getAction()).put("value", step.getValue())
					.put("image", ImageRepository.encodeImage(step.getImagePath())).put("text", step.getText()).put("num", step.getNum());
			JSONlist.add(ste);
		}

		return JSONlist;
	}
	
	public static List<JSONObject> ingredientsToJSON(List<Ingredient> ingredients) {
		List<JSONObject> JSONlist = new ArrayList<>();

		for (Ingredient ing : ingredients) {
			JSONObject ste = new JSONObject();
			ste.put("id", ing.getId()).put("amount", ing.getQuantity());
			JSONlist.add(ste);
		}

		return JSONlist;
	}

}