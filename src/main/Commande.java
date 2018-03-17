package main;

import java.util.ArrayList;

public class Commande {

	private Client client;
	private ArrayList<Plat> plats = new ArrayList<Plat>();

	public Commande(Client client) {
		this.client = client;
		plats = new ArrayList<Plat>();
	}

	public void ajouterPlat(Plat plat, int quantite) {
		for (int i = 0; i < quantite; i++) {
			plats.add(plat);
		}
	}

	public String toString() {
		double prixTotal = 0;
		for (Plat plat : plats) {
			prixTotal += plat.getPrix();
		}
		return client.getNom() + " " + String.format("%.2f", prixTotal) + "$";
	}

	public Client getClient() {

		return this.client;
	}

}
