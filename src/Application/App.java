package Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import Database.ConcessionDAO;
import Metier.Concession;
import Metier.Voiture;

public class App {

	private Concession concession;
	//private ConcessionDAO concessionDao;
	
	public App() {
		System.out.println("Nom de la concession");
		//this.concessionDao = new ConcessionDAO();
		creerConcession();
		System.out.println("Concession configurée");
		//Menu();
	}
	
	public Concession getConcession() {
		return concession;
	}

	public void setConcession(Concession concession) {
		this.concession = concession;
	}
	
	public void execution() throws SQLException {
		boolean continuer = true;
		do {
			continuer = action(continuer);
		}
		while(continuer);
	}
	
	public int choix() {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		int choix = 0;
        try {
			choix = Integer.parseInt(bufferRead.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return choix;
	}
	
	public boolean action(boolean sortir) throws SQLException {
		Menu();
		int choix = choix();
		switch(choix) {
			case 1:
				System.out.println("Ajouter une voiture à la concession");
				Voiture voiture = this.creerVoiture();
				this.getConcession().AjoutVoiture(voiture);
				break;
			case 2:
				System.out.println("Supprimer une voiture à la concession");
				SupprimerVoitureConcession();
				break;
			case 3:
				System.out.println("Sortir de la boucle");
				sortir = SortirProgram(sortir);
				break;
			case 4:
				System.out.println("Lister les voitures");
				afficherListeVoiture();
				break;
		}
		return sortir;
	}
	
	public static boolean SortirProgram(boolean sortir) {
		sortir = false;
		return sortir;
	}
	
	public void Menu() {
		System.out.println();
		System.out.println("1 - Ajouter une voiture à la concession");
		System.out.println("2 - Supprimer une voiture de la concession");
		System.out.println("3 - Sortir de l'application");
	}
	
	public String demanderInfo() {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String info = "";
		try {
			info = bufferRead.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public Voiture creerVoiture() throws SQLException {
		Voiture voiture = new Voiture(demanderInfo(), demanderInfo());
		this.concession.concessionDAO.insertionVoiture(voiture.getMarque(), voiture.getModel());
		return voiture;
	}
	
	public void creerConcession() {
		String info = this.demanderInfo();
		this.setConcession(new Concession(info));
	}

	public void afficherListeVoiture() throws SQLException {
		System.out.println("liste de mes voitures");
		this.concession.setVoitures(this.getConcession().concessionDAO.FindToutesVoiture());
		System.out.println(this.getConcession().concessionDAO.FindToutesVoiture());
		System.out.println(this.getConcession().concessionDAO.FindToutesVoiture().size());
		
		for(int i=0; i<this.getConcession().concessionDAO.FindToutesVoiture().size(); i++) {
			System.out.println(i + " " + this.concession.getVoitures().get(i));
		}
		
	}
	
	public void SupprimerVoitureConcession() throws SQLException {
		try {
			this.afficherListeVoiture();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int choix = choix();
		this.getConcession().concessionDAO.supprimerVoiture(choix);
	}
}
