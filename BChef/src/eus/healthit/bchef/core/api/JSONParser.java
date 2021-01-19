package eus.healthit.bchef.core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;

public class JSONParser {
	public static StatusCode registerUser(String name, String surname, String email, String username, String password,
			String path) {
		JSONObject json = new JSONObject();
		json.put("name", name).put("surname", surname).put("email", email).put("username", username)
				.put("password", password).put("profilepic", (path.equals("default"))?"default":ImageRepository.encodeImage(path));
		JSONObject jsonReturn = API.addUser(json);

		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static User authenticate(String username, String password) {
		JSONObject json = new JSONObject();
		json.put("username", username).put("password", password);

		JSONObject jsonReturn = API.auth(json);
		String status = jsonReturn.getString("status");

		if (status.equals("SUCCESSFUL")) {
			return JSONutils.getUser(jsonReturn);
		} else {
			return null;
		}
	}

	public static StatusCode addRecipe(Recipe recipe) {
		JSONObject json = new JSONObject();
		json.put("uuid", recipe.getUUID()).put("name", recipe.getName()).put("author", recipe.getAuthorID())
				.put("description", recipe.getDescription()).put("rating", recipe.getRating())
				.put("publish_date", recipe.getPublishDate()).put("duration", recipe.getDuration())
				.put("img", ImageCoder.decodeImage(recipe.getImagePath()));

		json.put("ingredients", JSONutils.ingredientsToJSON(recipe.getIngredients()));
		json.put("instructions", JSONutils.instructionsToJSON(recipe.getSteps()));

		JSONObject jsonReturn = API.addRecipe(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode follow(Integer user, Integer followed) {
		JSONObject json = new JSONObject();

		json.put("id", user).put("id_followed", followed);

		JSONObject jsonReturn = API.follow(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static User getUserById(int id) {
		JSONObject json = API.getUserById(id);
		return JSONutils.getUser(json);
	}

	public static StatusCode unfollow(Integer user, Integer unfollowed) {
		JSONObject json = new JSONObject();

		json.put("id", user).put("idToUnfollow", unfollowed);

		JSONObject jsonReturn = API.unfollow(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode save(Integer userID, UUID recipeID) {
		JSONObject json = new JSONObject();

		json.put("id_user", userID).put("uuid_recipe", recipeID);

		JSONObject jsonReturn = API.save(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode unsave(Integer userID, UUID recipeID) {
		JSONObject json = new JSONObject();

		json.put("id", userID).put("uuid", recipeID);

		JSONObject jsonReturn = API.unsave(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode addToHistory(Integer userID, UUID recipeID) {
		JSONObject json = new JSONObject();

		json.put("id", userID).put("uuid", recipeID);

		JSONObject jsonReturn = API.addToHistory(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode shoplistTicked(Item item, Integer userid) {
		JSONObject json = new JSONObject();

		json.put("name", item.getName()).put("ticked", item.isBought()).put("id", userid);

		JSONObject jsonReturn = API.shoplistTick(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode shoplistRemove(Item item) {
		JSONObject json = new JSONObject();

		json.put("id", item.getId());

		JSONObject jsonReturn = API.shoplistRemove(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode shoplistAdd(Item item, Integer userid) {
		JSONObject json = new JSONObject();

		json.put("name", item.getName()).put("id_user", userid);

		JSONObject jsonReturn = API.shoplistAdd(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static List<Recipe> getPage(int i) {

		JSONObject jsonReturn = API.getPage(i);
		JSONArray recipes = jsonReturn.getJSONArray("recipes");

		List<Recipe> recipeList = new ArrayList<>();

		for (Object object : recipes) {
			JSONObject recipe = (JSONObject) object;
			recipeList.add(JSONutils.getRecipe(recipe));
		}
		return recipeList;
	}

	public static StatusCode updateUser(User user) {
		JSONObject json = new JSONObject();
		json.put("name", user.getName()).put("surname", user.getSurname())
		.put("email", user.getEmail()).put("username", user.getUsername())
				.put("password", user.getPassword()).put("profilepic", (user.getImgString().equals("default"))?"default":ImageRepository.encodeImage(user.getImgString()));
		JSONObject jsonReturn = API.addUser(json);

		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
		
	}

}
