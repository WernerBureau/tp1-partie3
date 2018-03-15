package partie2;

public class Plat {
	private String nomPlat;
	private double prix;

	public Plat(String nomPlat, double prix) {
		this.nomPlat = nomPlat;
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public String getNom() {
		return nomPlat;
	}
}
