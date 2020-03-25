/**
 * 
 */
package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Article;
import fr.diginamic.jdbc.entities.Fournisseur;

/**
 *
 * @author Pierre
 *
 */
public class TestJdbcArticles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		
		FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
		
		Fournisseur f = new Fournisseur(4, "La Maison de la Peinture");
		fdj.insert(f);
		
		ArticleDaoJdbc adj = new ArticleDaoJdbc();
		
		Article p01 = new Article(11, "P01", "Peinture blanche 1L", 12.5, f);
		adj.insert(p01);
		
		Article p02 = new Article(12, "P02", "Peinture rouge mate 1L", 15.5, f);
		adj.insert(p02);
		
		Article p03 = new Article(13, "P03", "Peinture noire laquée 1L", 17.8, f);
		adj.insert(p03);
		
		Article p04 = new Article(14, "P04", "Peinture bleue mate 1L", 15.5, f);
		adj.insert(p04);
		
		Double p01P = p01.getPrix() - p01.getPrix() * 25 / 100;
		adj.update(p01, new Article(p01.getId(), 
				p01.getRef(), 
				p01.getDesignation(), 
				p01P, 
				p01.getFournisseur()));
		
		Double p02P = p02.getPrix() - p02.getPrix() * 25 / 100;
		adj.update(p02, new Article(p02.getId(), 
				p02.getRef(), 
				p02.getDesignation(), 
				p02P, 
				p02.getFournisseur()));
		
		Double p03P = p03.getPrix() - p03.getPrix() * 25 / 100;
		adj.update(p03, new Article(p03.getId(), 
				p03.getRef(), 
				p03.getDesignation(), 
				p03P, 
				p03.getFournisseur()));
		
		Double p04P = p04.getPrix() - p04.getPrix() * 25 / 100;
		adj.update(p04, new Article(p04.getId(), 
				p04.getRef(), 
				p04.getDesignation(), 
				p04P, 
				p04.getFournisseur()));
		
		List<Article> listA = adj.extraire();
		for (Article a : listA) {
			
			System.out.println(a);
			
		}
		
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

			ResultSet moy = stat.executeQuery("SELECT AVG(article.prix) AS 'Moyenne' FROM `article`");

			moy.next();
			System.out.println(moy.getDouble(1));
			
			stat.executeUpdate("DELETE FROM article WHERE designation LIKE '%Peinture%'");

			moy.close();
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		fdj.delete(f);

	}

}
