package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.Plat;

import org.junit.Test;

public class TestPlat {

	ArrayList<Plat> listePlats = new ArrayList<Plat>();
	
	private Plat plat0 = new Plat("a", 1);
	private Plat plat1 = new Plat("b", 2);
	private Plat plat2 = new Plat("c", 3);
	private Plat plat3 = new Plat("d", 4);
	private Plat plat4 = new Plat("e", 5);
	
	
	@Test
	public void testChercherPlatExistant() {
		listePlats.add(plat0);
		listePlats.add(plat1);
		listePlats.add(plat2);
		listePlats.add(plat3);
		listePlats.add(plat4);
		
		//Tester si chercherPlat avec plat2 retourne le bon index dans la liste
		assertEquals(1, Plat.chercherPlat(plat1.getNom()), 0);
	}
	
	@Test
	public void testChercherPlatInexistant() {
		listePlats.add(plat0);
		listePlats.add(plat1);
		listePlats.add(plat2);
		listePlats.add(plat3);
		//Tester si les plats inexistants sont invalides
		assertNotEquals(-1, Plat.chercherPlat(plat4.getNom()), 0);
	}

	@Test
	public void testGetPrixPlat(){
		assertEquals(1, plat0.getPrix(), 0);
	}
	
	@Test
	public void testGetNomPlat(){
		assertEquals("a", plat0.getNom());
	}
	
	
}
