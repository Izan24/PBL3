package eus.healthit.bchef.core.repository.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eus.healthit.bchef.core.controllers.QueryCon;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public class RecipeRepository {

	public static Recipe get(String uuid) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM public.recipes AS r WHERE r.uuid = '" + uuid + "'";
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			rSet.next();
			Recipe recipe = new Recipe(UUID.fromString(rSet.getString("uuid")), rSet.getString("name"),
					UserRepository.getName(rSet.getLong("author")), 10, rSet.getTimestamp("publish_date"),
					rSet.getTime("duration"), IngredientRepository.get(rSet.getString("uuid")),
					InstructionsRepository.get(rSet.getString("uuid")), rSet.getString("img"));
			System.out.println(query);
			return recipe;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Recipe> getN(int number) {
		try {
			List<Recipe> recipes = new ArrayList<>();
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM public.recipes LIMIT " + number;
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			while (rSet.next()) {
				recipes.add(new Recipe(UUID.fromString(rSet.getString("uuid")), rSet.getString("name"),
						UserRepository.getName(rSet.getLong("author")), 10, rSet.getTimestamp("publish_date"),
						rSet.getTime("duration"), IngredientRepository.get(rSet.getString("uuid")),
						InstructionsRepository.get(rSet.getString("uuid")), rSet.getString("img")));
			}
			System.out.println(query);
			return recipes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Recipe> search(String keyword) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			List<Recipe> recipes = new ArrayList<>();
			String query = "SELECT * FROM public.recipes AS r WHERE r.name LIKE '%" + keyword + "%'";
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			while (rSet.next()) {
				recipes.add(new Recipe(UUID.fromString(rSet.getString("uuid")), rSet.getString("name"),
						UserRepository.getName(rSet.getLong("author")), 10, rSet.getTimestamp("publish_date"),
						rSet.getTime("duration"), IngredientRepository.get(rSet.getString("uuid")),
						InstructionsRepository.get(rSet.getString("uuid")), rSet.getString("img")));
			}
			System.out.println(query);
			return recipes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean insert(Recipe recipe) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			UUID uuid = recipe.getUUID();
			String name = recipe.getName();
			String authorName = recipe.getAuthor();
			int author = UserRepository.getId(authorName);
			System.out.println(authorName + " " + author);
			int rating = recipe.getRating();
			Timestamp publishdate = recipe.getPublishDate();
			Time duration = recipe.getDuration();

			String imgurl = recipe.getImageURL();

			String q = "INSERT INTO public.recipes (uuid, name, author, rating, publish_date, duration, img) "
					+ "VALUES ('" + uuid.toString() + "', '" + name + "', '" + author + "', " + String.valueOf(rating)
					+ " , '" + publishdate.toString() + "' , '" + duration + "' , '" + imgurl + "')";
			System.out.println(q);
			stmt.execute(q);
			stmt.close();

			List<Ingredient> ingredients = recipe.getIngredients();
			for (Ingredient ingredient : ingredients) {
				long ex = IngredientRepository.getId(ingredient);
				if (ex == -1) {
					IngredientRepository.insert(ingredient);
				}
				RelationRepository.recipeIngredientRel(recipe, IngredientRepository.getId(ingredient),
						ingredient.getQuantity());
			}

			List<RecipeStep> instructions = recipe.getSteps();
			for (RecipeStep instruction : instructions) {
				InstructionsRepository.insert(instruction);
				RelationRepository.recipeInstructionRel(recipe, InstructionsRepository.getId(instruction));
			}

			System.out.println("DONE");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete(Recipe recipe) {
		UUID uuid = recipe.getUUID();
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();

			// RelationRepository.deleteRelation(uuid); xDDD HAU ZE TIO OWO

			String q = "DELETE FROM public.recipes WHERE \"uuid\" = '" + uuid.toString() + "'";
			stmt.execute(q);
			stmt.close();
			System.out.println(q);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	public static boolean delete(UUID uuid) {
//		try {
//			Connection conn = QueryCon.getConnection();
//			Statement stmt = conn.createStatement();
//			
//			String q = "DELETE FROM public.recipes WHERE \"uuid\" = '"+uuid.toString()+"'";
//			stmt.execute(q);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

}
