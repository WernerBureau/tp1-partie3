package main;

import java.util.ArrayList;

public class Table {
	private int noTable;

	private ArrayList<Client> clients;
	
	public static ArrayList<Table> listeTables = new ArrayList<Table>();
	
	public Table(int noTable){
		this.noTable = noTable;
		clients = new ArrayList<Client>();
		listeTables.add(this);
	}
	
	//Ajoute le client à la table avec le no recu en paramètre. Si elle n'existe pas, elle est crée et le client est ajouté
	public static void ajouterClient(Client cli, int noTable){
		int index = chercherTable(noTable);
		if (index != -1) {
			listeTables.get(index).clients.add(cli);
		}else {
			new Table(noTable);
			listeTables.get(listeTables.size()-1).clients.add(cli);
		}
	}
	
	public static int chercherTable(int noTable){
		int index = -1;
		for (int i = 0; i < listeTables.size(); i++) {
			if (listeTables.get(i).getNoTable()==noTable) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public int getNoTable() {
		return noTable;
	}

	public void setNoTable(int noTable) {
		this.noTable = noTable;
	}
	
	public static String compilerFactures(){
		String sortie = "";
		for (Table table : listeTables) {
			double totalTable = 0;
			int noTable = table.getNoTable();
			
			for (Client client : table.clients) {
				if (client.calculerTotal() > 0)
					totalTable += client.calculerTotal();
			}
			if (table.clients.size() >= 3 || totalTable >= 100)
				totalTable *= 1.30;
			else
				totalTable *= 1.15;
			
			sortie += "Table " + noTable + ": " + String.format("%.2f",totalTable)+ " $\r\n";
		}
		
		return sortie;
	}
}