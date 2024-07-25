package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Metier.Voiture;

public class BDD {
	public Connection con;
	public String url;
	public String bddMysql;
	public String bddApp;
	public String user;
	public String pwd;
	
	public BDD() {
		this.url = "jdbc:mysql://localhost:3306/";
		this.bddMysql = "mysql";
		this.bddApp = "concession_mii";
		this.user = "root";
		this.pwd = "";
		
	}
	
	public void getConnexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+bddApp, user, pwd);
	        this.con = conn;
	        
	        //System.out.println("Connecter");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void executeStructureQuery() {
		
	}
	
	public void executeCudRequete(String sql, ArrayList<Data> datas) throws SQLException {
		getConnexion();
		PreparedStatement pstmt2 = this.con.prepareStatement(sql);
        for (Data data : datas) {
        	
        	if(data.type.equals("int")) {
        		pstmt2.setInt(data.position, Integer.parseInt(data.valeur));
        	}
        	else {
        		pstmt2.setString(data.position, data.valeur);
        	}
        	
        	  //System.out.println("key: " + i + " value: " + datas.get(i));
        }
         //Parametre de type String
        //pstmt2.setString(2, "Prenomperso"); //Parametre de type String
        pstmt2.execute();
        pstmt2.close();
        this.con.close();
        System.out.println("voiture gérée");
	}
	
	// Faire un select
	// sql => gére ma requete
	// 
	public ResultSet selectRequete(String sql) throws SQLException{
		getConnexion();
        PreparedStatement pstmt = this.con.prepareStatement(sql);
        ResultSet rs2 = pstmt.executeQuery();

        return rs2;
	}
}
