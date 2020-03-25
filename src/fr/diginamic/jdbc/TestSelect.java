package fr.diginamic.jdbc;

import java.util.List;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestSelect {
	
	public static void main(String[] args) {
		
		FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
		
		List<Fournisseur> list = fdj.extraire();
		
		for (Fournisseur f : list) {
			
			System.out.println("Id : " + f.getId() + " Nom : " + f.getNom());
			
		}
		
	}

}
