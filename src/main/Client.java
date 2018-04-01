package main;

import java.util.ArrayList;

public class Client {
	private String nom;
	private ArrayList<Commande> commandes;
	
	static ArrayList<Client> listeClients = new ArrayList<Client>();
	
	public Client(String nom, int numeroTable) {
		this.nom = nom;
		this.commandes = new ArrayList<Commande>();
		Table.ajouterClient(this, numeroTable);
		listeClients.add(this);
		
	}

	public String getNom() {
		return nom;
	}
	

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void ajouterCommande(Commande commande){
		commandes.add(commande);
	}
	
	public double calculerTotal(){
		double total = 0;
		for (Commande commande : commandes) {
			total += commande.calculerPrix();
		}
		return total *=1.15;
		
	}
	
	public static int chercherClient(String nom){
		int index = -1;
		for (int i = 0; i < listeClients.size(); i++) {
			if (listeClients.get(i).getNom().equals(nom)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static Client getClient(int index){
		return listeClients.get(index);
	}
	

}
