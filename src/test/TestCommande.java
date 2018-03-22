package test;

import static org.junit.Assert.*;
import main.Plat;
import main.Commande;

import org.junit.Test;

public class TestCommande {

	Plat plat = new Plat("nomPlat", 10); // Création d'un objet Plat ayant comme nom nomPlat et coutant 10$
	Commande commande1 = new Commande(plat, 1); // Création d'un objet Commande avec ce plat.

	
	@Test
	public void testGetQuantite() {
		assertEquals(1, commande1.getQuantite(), 0);
	}
	
	@Test
	public void testGetMauvaiseQuantite() {
		assertNotEquals(2, commande1.getQuantite(), 0);
	}
	
	@Test
	public void testCalculerPrix() {
		
		assertEquals(plat.getPrix()*commande1.getQuantite(), commande1.calculerPrix(), 0);
	}
	

	
}
