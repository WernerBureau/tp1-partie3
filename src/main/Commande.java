package main;

import java.util.ArrayList;

public class Commande {

	private Client client;
	private ArrayList<Plat> plats = new ArrayList<Plat>();
	private String erreur;

	public Commande(Client client) {
		this.client = client;
		plats = new ArrayList<Plat>();
	}

	public void ajouterPlat(Plat plat, int quantite) {
		for (int i = 0; i < quantite; i++) {
			plats.add(plat);
		}
	}
	
	public double calculerPrixTotal(){
		double prixTotal = 0;
		for (Plat plat : plats) {
			prixTotal += plat.getPrix();
		}
		prixTotal += prixTotal*15/100;
		return prixTotal;
	}

	public String toString() {
		String s;
		if (getErreur().equals("")) {
			s = client.getNom() + " : " + String.format("%.2f", calculerPrixTotal()) + "$";
		}else {
			s = "Commande incorrecte : " + client.getNom() + ", " + getErreur();
		}
		return s;
	}

	public Client getClient() {

		return this.client;
	}
	

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

}
