package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entities.Article;
import fr.diginamic.jdbc.entities.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao {

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<Article> extraire() {
		ResourceBundle dbResource = ResourceBundle.getBundle("db");

		List<Article> list = null;

		try {
			Class.forName(dbResource.getString("db.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(dbResource.getString("db.url"),
					dbResource.getString("db.user"), dbResource.getString("db.mdp"));

			Statement stat = connection.createStatement();

			ResultSet set = stat.executeQuery("SELECT * FROM article");

			String prepStat = "SELECT * FROM fournisseur WHERE id=?";

			FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
			List<Fournisseur> listF = fdj.extraire();

			list = new ArrayList<>();

			while (set.next()) {

				try (PreparedStatement pst = connection.prepareStatement(prepStat)) {

					pst.setInt(1, set.getInt("fk_fournisseur"));
					ResultSet setF = pst.executeQuery();
					setF.next();

					list.add(new Article(set.getInt("id"), set.getString("ref"), set.getString("designation"),
							set.getDouble("prix"), new Fournisseur(setF.getInt("id"), setF.getString("nom"))));

				}

			}

			set.close();
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(Article article) {

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

			String prepStat = "INSERT INTO `article` (`id`, `ref`, `designation`, `prix`, `fk_fournisseur`) VALUES (?, ?, ?, ?, ?)";

			try (PreparedStatement pst = connection.prepareStatement(prepStat)) {

				pst.setInt(1, article.getId());
				pst.setString(2, article.getRef());
				pst.setString(3, article.getDesignation());
				pst.setDouble(4, article.getPrix());
				pst.setInt(5, article.getFournisseur().getId());
				pst.executeUpdate();

			}

			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int update(Article ancienArticle, Article nouvelArticle) {

		ResourceBundle dbResource = ResourceBundle.getBundle("db");
		int nb = 0;

		try {
			Class.forName(dbResource.getString("db.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(dbResource.getString("db.url"),
					dbResource.getString("db.user"), dbResource.getString("db.mdp"));

			Statement stat = connection.createStatement();

			String prepStat = "UPDATE article SET ref=?, designation=?, prix=? WHERE designation=?";

			try (PreparedStatement pst = connection.prepareStatement(prepStat)) {

				pst.setString(1, nouvelArticle.getRef());
				pst.setString(2, nouvelArticle.getDesignation());
				pst.setDouble(3, nouvelArticle.getPrix());
				pst.setString(4, ancienArticle.getDesignation());
				pst.executeUpdate();

			}

			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nb;
	}


	@Override
	public boolean delete(Article article) {
		
		ResourceBundle dbResource = ResourceBundle.getBundle("db");
		int nb = 0;

		try {
			Class.forName(dbResource.getString("db.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(dbResource.getString("db.url"),
					dbResource.getString("db.user"), dbResource.getString("db.mdp"));

			Statement stat = connection.createStatement();

			String prepStat = "DELETE FROM article WHERE id =?";

			try (PreparedStatement pst = connection.prepareStatement(prepStat)) {

				pst.setInt(1, article.getId());
				pst.executeUpdate();

			}
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (nb == 0) {
			return false;
		} else {
			return true;
		}
		
	}

}
