package partie2;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main {

	
	static ArrayList<Client> clients = new ArrayList<Client>();
	static ArrayList<Plat> plats = new ArrayList<Plat>();
	static ArrayList<Commande> commandes = new ArrayList<Commande>();
	
	public static void main(String[] args) throws IOException {

		// Lecture du fichier entree.txt

		String nomFichier = "entree.txt";
		String ligne = null;

		Client client;
		Plat plat;
		Commande commande;

		String[] lignes = new String[30];
		String[] texte;

		try {
			FileReader liseurFichier = new FileReader(nomFichier);
			BufferedReader liseurBuff = new BufferedReader(liseurFichier);

			// Ajout de toutes les lignes du fichier texte dans un tableau.
			int indice = 0;
			while ((ligne = liseurBuff.readLine()) != null) {
				lignes[indice] = ligne;
				indice++;
			}
			liseurBuff.close();

			// Création d'un nouveau tableau ayant la longueur du nombre de
			// lignes.
			texte = new String[indice];

			// Transfer du vieux tableau au nouveau tableau.
			for (int j = 0; j < texte.length; j++) {
				texte[j] = lignes[j];
			}

			int i1 = -1, i2 = -1, i3 = -1, i4 = -1;
			for (int i = 0; i < texte.length; i++) {
				if (texte[i].equals("Clients :")) {
					i1 = i;
				} else if (texte[i].equals("Plats :")) {
					i2 = i;
				} else if (texte[i].equals("Commandes :")) {
					i3 = i;
				}

				else if (texte[i].equals("Fin")) {
					i4 = i;
				}
			}

			// Clients
			for (int i = 1; i < i2 - i1; i++) {
				client = new Client(lignes[i]);
				clients.add(client);
				commande = new Commande(client);
				commandes.add(commande);
			}

			// Plats
			for (int i = 1; i < i3 - i2; i++) {
				String[] platSplit = lignes[i2 + i].split(" ");
				plat = new Plat(platSplit[0], Double.parseDouble(platSplit[1]));
				plats.add(plat);

			}

			// Commandes
			for (int i = 1; i < i4 - i3; i++) {
				
				String[] commandesSplit = lignes[i3 + i].split(" ");
				client = new Client(commandesSplit[0]);
				int quantite = Integer.parseInt(commandesSplit[2]);
				if (!clientExiste(commandesSplit[0])) {
					System.out.println("Le fichier ne respecte pas le format demandé !");
				}
				if (!platExiste(commandesSplit[1])) {
					System.out.println("Le fichier ne respecte pas le format demandé !");
				}
				
				
				for (Commande com : commandes) {
					if (com.getClient().getNom().equals(client.getNom())) {

						for (Plat pl : plats) {
							if (pl.getNom().equals(commandesSplit[1]))
								com.ajouterPlat(pl,
										quantite);
						}

					}
					
				}

			}

			FileWriter ecriveurFichier = new FileWriter("sortie.txt");
			BufferedWriter ecriveurBuff = new BufferedWriter(ecriveurFichier);

			ecriveurBuff.write("Bienvenue chez Barette!\r\nFactures:");
			for (Commande com : commandes) {

				ecriveurBuff.write("\r\n" + com);
			}
			ecriveurBuff.flush();
			ecriveurBuff.close();
			liseurFichier.close();
			liseurBuff.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Impossible d'ouvrir le fichier '" + nomFichier
					+ "'");
		} catch (IOException ex) {
			System.out.println("Erreur lors de la lecture du fichier '"
					+ nomFichier + "'");
		} catch (ArrayIndexOutOfBoundsException ex){
			System.out.println("Le fichier ne respecte pas le format demandé !");
		}
	}
	
	public static boolean clientExiste(String nom){
		
		boolean b =false;
		for (Client cli : clients) {
			if (cli.getNom().equals(nom)) {
				b=true;
			}
		}
		
		return b;
		
	}
	
	public static boolean platExiste(String nom){
		
		boolean b =false;
		for (Plat plat : plats) {
			if (plat.getNom().equals(nom)) {
				b=true;
			}
		}
		
		return b;
		
	}
}
