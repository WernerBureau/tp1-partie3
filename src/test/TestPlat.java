package test;

import static org.junit.Assert.*;

import main.Plat;

import org.junit.Test;

public class TestPlat {

	
	private Plat plat0 = new Plat("a", 1);
	private Plat plat1 = new Plat("b", 2);
	private Plat plat2 = new Plat("e", 5);
	
	
	@Test
	public void testChercherPlatExistant() {
		
		//Tester si chercherPlat avec plat2 retourne le bon index dans la liste
		assertEquals(1, Plat.chercherPlat(plat1.getNom()), 0);
	}
	
	@Test
	public void testChercherPlatInexistant() {
		//Tester si les plats inexistants sont invalides
		assertNotEquals(-1, Plat.chercherPlat(plat2.getNom()), 0);
	}

	@Test
	public void testGetPrixPlat(){
		assertEquals(1, plat0.getPrix(), 0);
	}
	
	@Test
	public void testGetNomPlat(){
		assertEquals("a", plat0.getNom());
	}
	
	@Test
	public void testGetPlat(){
		assertEquals(plat0.getNom(), Plat.getPlat(0).getNom());
	}
	
	
}
