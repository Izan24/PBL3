package eus.healthit.bchef.core.api;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.imageio.ImageIO;

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
		String pString = jsonReturn.getString("profilepic");
		Image profilepic = null;
		try {
			profilepic = (pString.equals("default")) ? ImageIO.read(new File("resources/user/default_user.png"))
					: ImageRepository.decodeImage(pString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String email = jsonReturn.getString("email");
		User user = new User(id, name, surname, profilepic, email);

		JSONArray arrayJSON = jsonReturn.getJSONArray("followed");
		user.setFollowed(getFollowed(arrayJSON));
		user.setFollowers(jsonReturn.getInt("followers"));
		user.setUsername(jsonReturn.getString("username"));
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

		arrayJSON = jsonReturn.getJSONArray("shoplist");
		for (Object obj : arrayJSON) {
			JSONObject item = (JSONObject) obj;
			user.addShopElement(getListItem(item));
		}

		return user;
	}

	public static Item getListItem(JSONObject item) {
		String name = item.getString("name");
		Integer id = item.getInt("id");
		Boolean ticked = item.getBoolean("ticked");
		Item itemShop = new Item(name);
		itemShop.setID(id);
		itemShop.setBought(ticked);

		return itemShop;
	}

	public static List<Integer> getFollowed(JSONArray json) {
		List<Integer> followed = new ArrayList<>();
		for (Object obj : json) {
			followed.add((Integer) obj);
		}

		return followed;
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

	public static List<Ingredient> getIntreientList(JSONArray array) {
		List<Ingredient> ingredientes = new ArrayList<>();

		for (Object obj2 : array) {
			JSONObject ingrediente = (JSONObject) obj2;
			int id2 = ingrediente.getInt("id");
			String nameI = ingrediente.getString("name");
			String type = ingrediente.getString("type");
			String amount = ingrediente.getString("amount");
			Ingredient ingre = new Ingredient(id2, nameI, type, amount);
			ingredientes.add(ingre);
		}
		return ingredientes;
	}

	public static Recipe getRecipe(JSONObject published) {
		UUID uuid = UUID.fromString(published.getString("uuid"));
		String name2 = published.getString("name");
		int authorID = published.getInt("author");
		String author = API.getUserName(authorID).getString("name");
		String description = published.getString("description");
		int rating = published.getInt("rating");
		Timestamp publishDate = Timestamp.valueOf(published.getString("publish_date"));
		List<Ingredient> ingredientes = new ArrayList<>();

		JSONArray arrayJson2 = published.getJSONArray("ingredients");
		
		for (int i = 0; i < arrayJson2.length(); i++) {
			JSONObject ingredient = arrayJson2.getJSONObject(i);
			int id2 = ingredient.getInt("id");
			String nameI = ingredient.getString("name");
			String type = ingredient.getString("type");
			String amount = ingredient.getString("amount");
			Ingredient ingre = new Ingredient(id2, nameI, type, amount);
			ingredientes.add(ingre);
		}

		List<RecipeStep> stepList = new ArrayList<>();
		arrayJson2 = published.getJSONArray("instructions");
		
		for (int i = 0; i < arrayJson2.length(); i++) {
			JSONObject step = arrayJson2.getJSONObject(i);
			int idS = step.getInt("id");
			RecipeStepActions recipeS = RecipeStepActions.valueOf(step.getString("action"));
			int value = step.getInt("value");
			Image image = ImageRepository.decodeImage(step.getString("img"));
			String text = step.getString("txt");
			System.out.println("JSONUTILS: "+text);
			int num = step.getInt("num");
			stepList.add(new RecipeStep(idS, recipeS, value, image, text, num));
		}
		
		
		Image image = ImageRepository.decodeImage(published.getString("img"));
		return new Recipe(uuid, name2, author, authorID, description, rating, publishDate, ingredientes, stepList,
				image);
	}

	public static List<JSONObject> instructionsToJSON(List<RecipeStep> instructions) {
		List<JSONObject> JSONlist = new ArrayList<>();

		for (RecipeStep step : instructions) {
			JSONObject ste = new JSONObject();
			ste.put("action", step.getAction()).put("value", step.getValue())
					.put("image", ImageRepository.encodeImage(step.getImagePath())).put("text", step.getText())
					.put("num", step.getNum());
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