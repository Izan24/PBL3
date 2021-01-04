package eus.healthit.bchef.core.repository.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import eus.healthit.bchef.core.controllers.QueryCon;
import eus.healthit.bchef.core.models.User;


public class UserRepository {


	public static User getUserById(Long id) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static String getName(Long id) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM public.users AS r WHERE r.id = " + id + "";
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			rSet.next();
			System.out.println(query);
			return rSet.getString("name");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public static int getId(String authorName) {
		try {
			Connection conn = QueryCon.getConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM public.users AS r WHERE UPPER(r.name) = UPPER('" + authorName + "')";
			ResultSet rSet = stmt.executeQuery(query);
			stmt.close();
			System.out.println(query);
			rSet.next();
			return rSet.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public static void name() {
		
	}
	

}
