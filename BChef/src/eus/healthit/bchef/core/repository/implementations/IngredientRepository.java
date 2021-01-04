package eus.healthit.bchef.core.repository.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eus.healthit.bchef.core.controllers.QueryCon;
import eus.healthit.bchef.core.models.Ingredient;

public class IngredientRepository {

	public static List<Ingredient> get(String uuid) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			List<Ingredient> ingredients = new ArrayList<>();
			String query = "SELECT * FROM public.rel_ingredients AS r WHERE r.uuid_recipe = '" + uuid + "'";
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			while (rSet.next()) {
				ingredients.add(getOne(rSet.getLong("id_ingredient"), uuid));
			}
			System.out.println(query);
			return ingredients;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Ingredient getOne(long id, String uuid) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "SELECT * FROM public.ingredients AS r WHERE r.id = " + id;
			ResultSet rSet = stmt.executeQuery(q);
			stmt.close();
			rSet.next();
			System.out.println(q);
			return new Ingredient(id, rSet.getString("name"), rSet.getString("type"), RelationRepository.getIngredientAmount(uuid, id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean insert(Ingredient ingredient) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "INSERT INTO public.ingredients (name, type) VALUES ('" + ingredient.getName() + "', '"
					+ ingredient.getType() + "')";
			stmt.execute(q);
			stmt.close();
			System.out.println(q);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static long getId(Ingredient ingredient) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "SELECT * FROM public.ingredients AS r WHERE r.name = '" + ingredient.getName() + "'";
			ResultSet rSet = stmt.executeQuery(q);
			stmt.close();
			rSet.next();
			System.out.println(q);
			return rSet.getLong("id");
		} catch (Exception e) {
			return -1;
		}

	}

}
