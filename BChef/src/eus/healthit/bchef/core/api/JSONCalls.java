package eus.healthit.bchef.core.api;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.util.func.NewAlarmCall;

public class JSONCalls {
	public static StatusCode registerUser(String name, String surname, String email, String username, String password,
			String path) {
		JSONObject json = new JSONObject();
		json.put("name", name).put("surname", surname).put("email", email).put("username", username)
				.put("password", password)
				.put("profilepic", (path.equals("default")) ? "default" : ImageRepository.encodeImage(path));
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
		System.out.println(recipe.getDescription());
		json.put("uuid", recipe.getUUID()).put("name", recipe.getName()).put("author", recipe.getAuthorID())
				.put("description", recipe.getDescription()).put("rating", recipe.getRating())
				.put("publish_date", Timestamp.valueOf(LocalDate.now().atStartOfDay()))
				.put("img", ImageRepository.encodeImage(recipe.getImagePath()));

		json.put("ingredients", JSONutils.ingredientsToJSON(recipe.getIngredients()));
		json.put("instructions", JSONutils.instructionsToJSON(recipe.getSteps()));

		JSONObject jsonReturn = API.addRecipe(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static List<Recipe> searchByIngredient(Set<String> set) {
		JSONObject json = API.searchByIngredients(set);
		JSONArray array = json.getJSONArray("recipes");
		List<Recipe> list = new ArrayList<>();
		for (Object object : array) {
			JSONObject recipe = (JSONObject) object;
			list.add(JSONutils.getRecipe(recipe));
		}
		return list;
	}

	public static List<Ingredient> ingredientLike(String kw) {
		JSONObject json = API.searchIngredient(kw);
		JSONArray array = json.getJSONArray("ingredients");
		List<Ingredient> ingredientes = new ArrayList<>();
		for (Object object : array) {
			JSONObject ingr = (JSONObject) object;
			int id = ingr.getInt("id");
			String name = ingr.getString("name");
			String type = ingr.getString("type");
			ingredientes.add(new Ingredient(id, name, type));
		}
		return ingredientes;
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

		json.put("id", user).put("id_followed", unfollowed);

		JSONObject jsonReturn = API.unfollow(json);
		String status = jsonReturn.getString("status");

		return StatusCode.valueOf(status);
	}

	public static StatusCode save(Integer userID, UUID recipeID) {
		JSONObject json = new JSONObject();

		json.put("id", userID).put("uuid", recipeID);

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

	public static StatusCode shoplistTicked(Item item) {
		JSONObject json = new JSONObject();

		json.put("ticked", item.isBought()).put("id", item.getId());

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

	public static Integer shoplistAdd(Item item, Integer userid) {
		JSONObject json = new JSONObject();

		json.put("name", item.getName()).put("id_user", userid);

		JSONObject jsonReturn = API.shoplistAdd(json);
		Integer id = jsonReturn.getInt("id");

		return id;
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

	public static List<Recipe> getHistoryBetween(int userId, LocalDate from, LocalDate until) {
		JSONObject json = API.getHistoryBetween(userId, Timestamp.valueOf(from.atStartOfDay()),
				Timestamp.valueOf(until.atStartOfDay()));
		JSONArray array = json.getJSONArray("history");
		List<Recipe> history = new ArrayList<>();
		for (Object obj : array) {
			JSONObject recipe = (JSONObject) obj;
			history.add(JSONutils.getRecipe(recipe));
		}
		return history;
	}

	public static List<User> getAllUsers() {
		JSONObject json = API.getAllUsers();
		JSONArray userArray = json.getJSONArray("users");
		List<User> users = new ArrayList<>();
		for (Object object : userArray) {
			JSONObject user = (JSONObject) object;
			users.add(new User(user.getInt("id"), user.getString("name"), user.getString("surname"),
					user.getString("email")));
		}
		return users;
	}

	public static StatusCode updateUser(User user) {
		JSONObject json = new JSONObject();
		json.put("name", user.getName()).put("surname", user.getSurname()).put("email", user.getEmail())
				.put("username", user.getUsername()).put("id_user", user.getId()).put("password", user.getPassword())
				.put("profilepic",
						(user.getImgString().equals("default")) ? "default"
								: (user.getImgString().equals("nochange")) ? "nochange"
										: ImageRepository.encodeImage(user.getImgString()));
		JSONObject jsonReturn = API.updateUserConfig(json);

		String status = jsonReturn.getString("status");
		return StatusCode.valueOf(status);

	}

	public static boolean checkUser(String username) {
		JSONObject json = API.checkUser(username);
		if (StatusCode.valueOf(json.getString("status")) == StatusCode.SUCCESSFUL) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean reauth(String username, String pwd) {
		JSONObject json = new JSONObject().put("username", username).put("password", pwd);
		JSONObject jsonRet = API.reauth(json);
		if (StatusCode.valueOf(jsonRet.getString("status")) == StatusCode.SUCCESSFUL) {
			return true;
		} else {
			return false;
		}

	}

	public static List<Recipe> search(String text, int page) {
		JSONArray json = API.searchRecipe(text, page).getJSONArray("recipes");
		List<Recipe> list = new ArrayList<>();
		for (Object obj : json) {
			JSONObject jObject = (JSONObject) obj;
			list.add(JSONutils.getRecipe(jObject));
		}
		return list;

	}

	public static boolean rateRecipe(int userId, UUID recipeUUID, int rating) {
		JSONObject json = new JSONObject().put("id_user", userId).put("uuid_recipe", recipeUUID.toString())
				.put("rating", rating);
		JSONObject jsonRet = API.rate(json);
		if (StatusCode.valueOf(jsonRet.getString("status")) == StatusCode.SUCCESSFUL) {
			return true;
		} else {
			return false;
		}
	}

}
