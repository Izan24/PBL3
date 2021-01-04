package eus.healthit.bchef.core.controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class QueryCon {
	public static final String DBURL = "jdbc:postgresql://servkolay.ddns.net:5432/Data";

	static Connection connection;

	public QueryCon() {
		try {
			System.out.println();
			connection = DriverManager.getConnection(DBURL, "postgres", "mutriku123");
		} catch (Exception e) {
			e.printStackTrace();
			connection = null;
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(DBURL, "postgres", "mutriku123");
			} catch (Exception e) {
				e.printStackTrace();
				connection = null;
			}
		}
		return connection;
	}

	public static void closeConn() {
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println("uwu");
		}
	}
}
