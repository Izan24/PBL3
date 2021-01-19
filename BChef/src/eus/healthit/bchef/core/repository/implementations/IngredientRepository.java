package eus.healthit.bchef.core.repository.implementations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class IngredientRepository {

	public static List<JSONObject> getByUuid(String uuid) throws SQLException {
		String query = "SELECT * FROM public.rel_ingredients INNER JOIN public.ingredients ON (rel_ingredients.id_ingredient = ingredients.id)"
				+ "WHERE rel_ingredients.uuid_recipe = " + uuid + "";
		ResultSet rSet = QueryCon.executeQuery(query);
		return parseIngredientList(rSet);
	}

	private static JSONObject parseIngredient(ResultSet rset) throws JSONException, SQLException {
		JSONObject ingredient = new JSONObject();
		ingredient.put("id", rset.getInt("id")).put("name", rset.getString("name")).put("type", rset.getString("type"));
		return ingredient;
	}

	private static List<JSONObject> parseIngredientList(ResultSet rSet) throws SQLException {
		List<JSONObject> list = new ArrayList<>();
		while (rSet.next()) {
			list.add(parseIngredient(rSet));
		}
		return list;
	}

	public static JSONObject ingredientLike(String like) throws JSONException, SQLException {
		String query = "SELECT * FROM public.ingredients WHERE ingredients.name LIKE '%" + like + "%'";
		ResultSet rSet = QueryCon.executeQuery(query);
		return new JSONObject().put("ingredients", parseIngredientList(rSet));
	}

	public static void makeRelation(int idIng, String uuidRecipe, String amount) throws SQLException {
		String query = String.format("INSERT INTO public.rel_ingredients VALUES ('%s', %d, %d )", uuidRecipe, idIng,
				amount);
		QueryCon.execute(query);
	}

}
