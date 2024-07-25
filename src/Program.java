import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Application.App;

public class Program {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*
		
        
		Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connecter");
        */
        App app = new App();
        //System.out.println(app.getConcession());

        app.execution();
    }

}
