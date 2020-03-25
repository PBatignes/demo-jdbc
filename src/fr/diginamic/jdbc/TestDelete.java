package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestDelete {
	
	public static void main(String[] args) {
		
		ResourceBundle dbResource = ResourceBundle.getBundle("db");

		try {
			Class.forName(dbResource.getString("db.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(dbResource.getString("db.url"),
					dbResource.getString("db.user"), dbResource.getString("db.mdp"));

			Statement stat = connection.createStatement();
			
			int nb = stat.executeUpdate("DELETE FROM fournisseur WHERE id = 4");
			System.out.println(nb);
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
