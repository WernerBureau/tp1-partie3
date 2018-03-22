package main;


public class Commande {

	private Plat plat;
	private int quantite;

	public Commande(Plat plat, int quantite) {
		this.plat = plat;
		this.quantite = quantite;
	}

	
	public double calculerPrix(){
		return plat.getPrix()*quantite;
	}
	
	public int getQuantite() {
		return quantite;
	}
}
