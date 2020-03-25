package main.java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexionJdbc {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ResourceBundle dbResource = ResourceBundle.getBundle("db");
		
		Class.forName(dbResource.getString("db.driver"));
		
		Connection connection = DriverManager.getConnection(dbResource.getString("db.url"), 
				dbResource.getString("db.user"), 
				dbResource.getString("db.mdp"));
		
		System.out.println(connection);
		
		connection.close();
		
	}

}
