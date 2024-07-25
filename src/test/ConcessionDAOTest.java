package test;

import Database.BDD;
import Database.ConcessionDAO;
import Metier.Voiture;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConcessionDAOTest {

    @Test
    public void testConcessionDAOConstructor() {
        // nouvelle instance de la classe ConcessionDAO
        ConcessionDAO concessionDAO = new ConcessionDAO();
        // check si 'database' n'est pas null (echec si null)
        assertNotNull(concessionDAO.database);
    }

    @Test
    public void testInsertionVoiture() throws SQLException {

        // nouvel objet ConcessionDAO pour le test
        ConcessionDAO concessionDAO = new ConcessionDAO();

        // insertion via la methode testée
        Voiture voiture = concessionDAO.insertionVoiture("testMarque", "testModel");

        // check si une voiture a bien été crée
        assertNotNull(voiture);

        // On prépare la requête SELECT
        String selectSQL = "SELECT * FROM voiture WHERE marque = 'testMarque' AND model = 'testModel'";

        // nouvel objet BDD pour interragir avec la bdd pour ce test
        BDD bdd = new BDD();

        // execution de la requete préparée
        ResultSet rs = bdd.selectRequete(selectSQL);

        // check si une voiture est trouvé (echec du test si c'est false)
        assertTrue(rs.next());

        // check si les données rentrées sont bien egale a celle attendues
        assertEquals("testMarque", rs.getString("marque"));
        assertEquals("testModel", rs.getString("model"));

        rs.close();
    }


    @Test
    public void findToutesVoiture() {
    }

    @Test
    public void supprimerVoiture() {
    }
}