package test;

import static org.junit.Assert.*;
import main.Client;
import main.Main;

import org.junit.Test;


public class TestMain {
	@Test
	public void testLireLigneClientIncorrect() {
		
		assertEquals("Erreur avec la ligne Mathieu Allard\r\n\tLa ligne contient plus qu'un nom\r\n", Main.lireLigneClient("Mathieu Allard"));
	}
	
	@Test
	public void testLireLigneClientCorrect() {
		
		assertEquals("", Main.lireLigneClient("Mathieu"));
	}
	@Test
	public void testLireLigneClientExisteDeja() {
		assertEquals("Erreur avec la ligne Mathieu\r\n\tLe client existe déjà.\r\n", Main.lireLigneClient("Mathieu"));
	}
	
	
	@Test
	public void testLireLignePlatPrixIncorrect() {
		
		assertEquals("Erreur avec la ligne Frites 10,5\r\n\tLe prix n'est pas valide\r\n", Main.lireLignePlat("Frites 10,5"));
	}
	
	@Test
	public void testLireLignePlatFormatPasValide() {
		assertEquals("Erreur avec la ligne Frite\r\n\tLe format de la ligne n'est pas respecté\r\nErreur avec la ligne Frite\r\n\tLe prix n'est pas valide\r\n", Main.lireLignePlat("Frite"));
	}
	
	@Test
	public void testLireLignePlatCorrect() {
		
		assertEquals("", Main.lireLignePlat("Hotdog 7.5"));
	}
	
	@Test
	public void testLireLignePlatExisteDeja() {
		assertEquals("Erreur avec la ligne Hotdog 7.5\r\n\tLe plat existe déjà\r\n", Main.lireLignePlat("Hotdog 7.5"));
	}
	

	
	@Test
	public void testLireLigneCommandePlatEtClientExistePas() {
		
		assertEquals("Erreur avec la ligne Werner Frites 1\r\n\tLe client n'existe pas\r\n\tLe plat n'existe pas\r\n", Main.lireLigneCommande("Werner Frites 1"));
	}

}
