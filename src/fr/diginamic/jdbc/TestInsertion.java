package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestInsertion {

	public static void main(String[] args) {

		FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
		
		fdj.insert(new Fournisseur(4, "Fournisseur4"));

	}

}
