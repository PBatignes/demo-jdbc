package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class TestUpdate {
	
	public static void main(String[] args) {
		
		FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
		
		fdj.update("Fournisseur4", "Fournisseur");
		
	}

}
