package eus.healthit.bchef.core.repository.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import eus.healthit.bchef.core.controllers.QueryCon;
import eus.healthit.bchef.core.models.Recipe;

public class RelationRepository {

	public static boolean recipeIngredientRel(Recipe recipe, long id, String amount) {
		UUID uuid = recipe.getUUID();
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "INSERT INTO public.rel_ingredients VALUES ('" + uuid + "', '" + id + "', '" + amount + "')";
			stmt.execute(q);
			System.out.println(q);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean recipeInstructionRel(Recipe recipe, long id) {
		UUID uuid = recipe.getUUID();
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "INSERT INTO public.rel_instructions VALUES ('" + uuid + "', '" + id + "')";
			stmt.execute(q);
			System.out.println(q);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getIngredientAmount(String uuid, long id) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM public.rel_ingredients AS r WHERE r.id_ingredient = " + id
					+ " AND r.uuid_recipe = '" + uuid + "'";
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			System.out.println(query);
			rSet.next();
			return rSet.getString("amount");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
