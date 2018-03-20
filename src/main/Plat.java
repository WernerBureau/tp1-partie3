package main;

import java.util.ArrayList;

public class Plat {
	private String nom;
	private double prix;
	
	static ArrayList<Plat> listePlats = new ArrayList<Plat>();

	public Plat(String nom, double prix) {
		this.nom = nom;
		this.prix = prix;
		listePlats.add(this);
	}

	public double getPrix() {
		return prix;
	}

	public String getNom() {
		return nom;
	}
	
	static int chercherPlat(String nom){
		int index = -1;
		for (int i = 0; i < listePlats.size(); i++) {
			if (listePlats.get(i).getNom().equals(nom)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	static Plat getPlat(int index){
		return listePlats.get(index);
	}
}
