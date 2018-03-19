package main;

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
			ajoutClients(lignes, i1, i2);

			// Plats
			ajoutPlats(lignes, i2, i3);

			// Commandes
			construireCommandes(lignes, i3, i4);

			FileWriter ecriveurFichier = new FileWriter("sortie.txt");
			BufferedWriter ecriveurBuff = new BufferedWriter(ecriveurFichier);

			// Affichage des commandes
			afficherCommandes();

			ecritureFichier(ecriveurBuff);

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
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out
					.println("Le fichier ne respecte pas le format demandé !");
		}
	}

	private static void ecritureFichier(BufferedWriter ecriveurBuff)
			throws IOException {
		ecriveurBuff.write("Bienvenue chez Barette!\r\nFactures:");
		ecrireCommandes(ecriveurBuff);
	}

	private static void construireCommandes(String[] lignes, int i3, int i4) {
		Client client;
		for (int i = 1; i < i4 - i3; i++) {

			String[] commandesSplit = lignes[i3 + i].split(" ");
			client = new Client(commandesSplit[0]);
			int quantite = Integer.parseInt(commandesSplit[2]);

			validerCommande(commandesSplit);
			
			// Attribuer un client à la commande
			for (Commande com : commandes) {
				if (com.getClient().getNom().equals(client.getNom())) {
					// Attribuer des plats à la commande
					for (Plat pl : plats) {
						if (pl.getNom().equals(commandesSplit[1]))
							com.ajouterPlat(pl, quantite);
					}

				}

			}

		}
	}

	public static boolean validerCommande(String[] commandesSplit) {
		boolean valide = true;
		if (!clientExiste(commandesSplit[0])) {
			valide = false;
		}
		if (!platExiste(commandesSplit[1])) {
			valide = false;
		}
		return valide;
	}

	private static void ajoutPlats(String[] lignes, int i2, int i3) {
		Plat plat;
		for (int i = 1; i < i3 - i2; i++) {
			String[] platSplit = lignes[i2 + i].split(" ");
			plat = new Plat(platSplit[0], Double.parseDouble(platSplit[1]));
			plats.add(plat);

		}
	}

	private static void ajoutClients(String[] lignes, int i1, int i2) {
		Client client;
		Commande commande;
		for (int i = 1; i < i2 - i1; i++) {
			client = new Client(lignes[i]);
			clients.add(client);
			commande = new Commande(client);
			commandes.add(commande);
		}
	}

	public static boolean clientExiste(String nom) {

		boolean b = false;
		for (Client cli : clients) {
			if (cli.getNom().equals(nom)) {
				b = true;
			}
		}

		return b;

	}

	public static boolean platExiste(String nom) {

		boolean b = false;
		for (Plat plat : plats) {
			if (plat.getNom().equals(nom)) {
				b = true;
			}
		}

		return b;

	}

	private static String genererSortie() {
		String afficher = "";
		for (Commande commande : commandes) {
			commande.setErreur("");
			if (!commande.getErreur().equals("")) {
				afficher += commande + "\r\n";
			}
		}

		for (Commande commande : commandes) {
			if (commande.getErreur().equals("")
					&& commande.calculerPrixTotal() != 0) {
				afficher += commande + "\r\n";
			}
		}
		return afficher;
	}

	public static void afficherCommandes() {
		System.out.println(genererSortie());
	}

	private static void ecrireCommandes(BufferedWriter ecriveurBuff)
			throws IOException {
		ecriveurBuff.write(genererSortie());
	}

}
