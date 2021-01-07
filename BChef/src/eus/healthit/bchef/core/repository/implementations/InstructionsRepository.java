package eus.healthit.bchef.core.repository.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eus.healthit.bchef.core.controllers.QueryCon;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.RecipeStep;

public class InstructionsRepository {

	public static List<RecipeStep> get(String uuid) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			List<RecipeStep> recipeSteps = new ArrayList<>();
			String query = "SELECT * FROM public.rel_instructions AS r WHERE r.uuid_recipe = '" + uuid + "'";
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			while (rSet.next()) {
				recipeSteps.add(getOne(rSet.getLong("id_instruction")));
			}
			System.out.println(query);
			return recipeSteps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static RecipeStep getOne(long id) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "SELECT * FROM public.instructions AS r WHERE r.id = " + id;
			ResultSet rSet = stmt.executeQuery(q);
			stmt.close();
			System.out.println(q);
			rSet.next();
			return new RecipeStep(id, RecipeStepActions.valueOf(rSet.getString("action")), rSet.getInt("value"),
					rSet.getString("img"), rSet.getString("txt"), rSet.getLong("num"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean insert(RecipeStep instruction) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "INSERT INTO public.instructions (action, value, img, txt, num) VALUES ('"
					+ instruction.getAction() + "', " + instruction.getValue() + ", '" + instruction.getImageURL()
					+ "', '"+instruction.getText()+"', "+instruction.getNum()+")";
			stmt.execute(q);
			stmt.close();
			System.out.println(q);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static long getId(RecipeStep instruction) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String q = "SELECT * FROM public.instructions AS r WHERE r.txt = '" + instruction.getText() + "'";
			ResultSet rSet = stmt.executeQuery(q);
			stmt.close();
			rSet.next();
			System.out.println(q);
			return rSet.getLong("id");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}

}
