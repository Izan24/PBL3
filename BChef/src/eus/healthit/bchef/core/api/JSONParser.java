package eus.healthit.bchef.core.api;

import java.awt.Image;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
import eus.healthit.bchef.core.repository.implementations.ImageRepository;

public class JSONParser 
{
	public static StatusCode toJSON(String name, String surname, String email, String username, String password, String profilepic)
	{
		JSONObject json = new JSONObject();
		json.put("name", name).put("surname", surname).put("email", email).put("username", username).put("password", password)
				.put("profilepic", profilepic);
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static User toJSON(String username, String password)
	{
		JSONObject json = new JSONObject();
		json.put("username", username).put("password", password);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		if (status.equals("SUCCESSFUL"))
		{
			return JSONFunctions.obtainUser(jsonReturn);
		}
		else
		{
			return null;
		}
	}
	
	public static StatusCode toJSON(Recipe recipe)
	{
		JSONObject json = new JSONObject();
		json.put("uuid", recipe.getUUID()).put("name", recipe.getName()).put("author", recipe.getAuthor()).put("authorID", recipe.getAuthorID()).put("description", recipe.getDescription())
			.put("rating", recipe.getRating()).put("publishDate", recipe.getPublishDate()).put("duration", recipe.getDuration()).put("img", ImageCoder.decodeImage(recipe.getImagePath()));
		
		json.put("ingredients", JSONParser.ingredientToJSON(recipe.getIngredients()));
		json.put("steps", JSONParser.stepToJSON(recipe.getSteps()));
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONFollow(Integer id1, Integer id2)
	{
		JSONObject json = new JSONObject();
		
		json.put("idUser", id1).put("idToFollow", id2);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONUnfollow(Integer id1, Integer id2)
	{
		JSONObject json = new JSONObject();
		
		json.put("idUser", id1).put("idToUnfollow", id2);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONSave(Integer userID, UUID recipeID)
	{
		JSONObject json = new JSONObject();
		
		json.put("idUser", userID).put("UUIDToSave", recipeID);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONUnsave(Integer userID, UUID recipeID)
	{
		JSONObject json = new JSONObject();
		
		json.put("idUser", userID).put("UUIDToUnsave", recipeID);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONHistory(Integer userID, UUID recipeID)
	{
		JSONObject json = new JSONObject();
		
		json.put("idUser", userID).put("UUIDToHistory", recipeID);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONUpdateShopListItem(Item item, Integer id)
	{
		JSONObject json = new JSONObject();
		
		json.put("name", item.getName()).put("ticked", item.isBought()).put("id", id);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONRemoveShopListItem(Item item, Integer id)
	{
		JSONObject json = new JSONObject();
		
		json.put("name", item.getName()).put("ticked", item.isBought()).put("id", id);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	public static StatusCode toJSONAddShopListItem(Item item, Integer id)
	{
		JSONObject json = new JSONObject();
		
		json.put("name", item.getName()).put("ticked", item.isBought()).put("id", id);
		
		JSONObject jsonReturn = new JSONObject(); //AQUÍ FUNCIÓN URKO
		String status = jsonReturn.getString("status");
		
		return StatusCode.valueOf(status);
	}
	
	private static List<JSONObject> stepToJSON(List<RecipeStep> steps) 
	{
		List<JSONObject> JSONlist = new ArrayList<>();
		
		for(RecipeStep step : steps)
		{
			JSONObject ste = new JSONObject();
			ste.put("id", step.getId()).put("action", step.getAction()).put("value", step.getValue()).put("image", step.getImagePath()).put("text", step.getText()).put("num", step.getNum());
			JSONlist.add(ste);
		}
		
		return JSONlist;
	}

	public static List<JSONObject> ingredientToJSON(List<Ingredient> lista)
	{
		List<JSONObject> JSONlist = new ArrayList<>();
		
		for(Ingredient ingrediente : lista)
		{
			JSONObject ingre = new JSONObject();
			ingre.put("id", ingrediente.getId()).put("name", ingrediente.getName()).put("type", ingrediente.getType()).put("amount", ingrediente.getQuantity());
			JSONlist.add(ingre);
		}
		
		return JSONlist;
	}
}
