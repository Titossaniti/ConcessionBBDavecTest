package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Metier.Voiture;

public class ConcessionDAO {
	public BDD database;
	
	public ConcessionDAO() {
		this.database = new BDD();
	}
	
	public Voiture insertionVoiture(String marque, String model) throws SQLException {
		ArrayList<Data> datas = new ArrayList<Data>(); 
		datas.add(new Data(1, "string", marque));
		datas.add(new Data(2, "string", model));
		this.database.executeCudRequete("insert into voiture(marque, model) values (?,?)", datas);
		Voiture voiture = new Voiture();
		return voiture;
	}
	
	public ArrayList<Voiture> FindToutesVoiture() throws SQLException{
		String select = "Select * from voiture";
		ArrayList<Voiture> voitures = new ArrayList<Voiture>();
		ResultSet set = this.database.selectRequete(select);
		while(set.next())
        {
        	voitures.add(new Voiture(set.getInt("id"),set.getString("marque"), set.getString("model")));
        }
		return voitures;
	}
	
	public void supprimerVoiture(int id) throws SQLException {
		ArrayList<Data> datas = new ArrayList<Data>(); 
		datas.add(new Data(1, "int", String.valueOf(id)));
		this.database.executeCudRequete("delete from voiture where id = ?", datas);
		
	}
}
