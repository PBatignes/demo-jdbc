package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entities.Fournisseur;

public class TestSelect {
	
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
			
			ResultSet set = stat.executeQuery("SELECT * FROM fournisseur");
			
			List<Fournisseur> list = new ArrayList<>();
			
			while (set.next()) {
				
				list.add(new Fournisseur(set.getInt("id"), set.getString("nom")));
				
			}
			
			for (Fournisseur f : list) {
				
				System.out.println("Id : " + f.getId() + " Nom : " + f.getNom());
				
			}
			
			set.close();
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
