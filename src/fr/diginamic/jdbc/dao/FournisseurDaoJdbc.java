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

import fr.diginamic.jdbc.entities.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	@Override
	public List<Fournisseur> extraire() {
		ResourceBundle dbResource = ResourceBundle.getBundle("db");

		List<Fournisseur> list = null;

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

			list = new ArrayList<>();

			while (set.next()) {

				list.add(new Fournisseur(set.getInt("id"), set.getString("nom")));

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
	public void insert(Fournisseur fournisseur) {

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

			String prepStat = "INSERT INTO `fournisseur`(`id`, `nom`) VALUES (?, ?)";

			try (PreparedStatement pst = connection.prepareStatement(prepStat)) {

				pst.setInt(1, fournisseur.getId());
				pst.setString(2, fournisseur.getNom());
				pst.executeUpdate();

			}

			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {

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

			String prepStat = "UPDATE fournisseur SET nom=? WHERE nom=?";

			try (PreparedStatement pst = connection.prepareStatement(prepStat)) {

				pst.setString(1, nouveauNom);
				pst.setString(2, ancienNom);
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
	public boolean delete(Fournisseur fournisseur) {

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

			String prepStat = "DELETE FROM fournisseur WHERE id =?";

			try (PreparedStatement pst = connection.prepareStatement(prepStat)) {

				pst.setInt(1, fournisseur.getId());
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
