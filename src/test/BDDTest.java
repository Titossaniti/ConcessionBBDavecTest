package test;

import Database.BDD;
import Database.Data;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BDDTest {

    @Test
    // On vérifie que le constructeur initialise les variables avec les bonnes valeurs
    public void testBDDConstructor() {
        BDD bdd = new BDD();
        assertEquals("jdbc:mysql://localhost:3306/", bdd.url);
        assertEquals("mysql", bdd.bddMysql);
        assertEquals("concession_mii", bdd.bddApp);
        assertEquals("root", bdd.user);
        assertEquals("", bdd.pwd);
    }

    @Test
    public void testGetConnexion() {
        try {
            BDD bdd = new BDD();
            bdd.getConnexion();
            assertNotNull("Echec",bdd.con); // Vérifie que "con" ne soit pas null, si null alors la connexion a échoué
            assertFalse("Echec 2", bdd.con.isClosed()); // Si retourne false, alors la connexion n'est pas fermée, donc c'est reussis, si c'est true, c'est que la connexion est fermée
        } catch (Exception e) {
            fail("Echec de la connexion");
        }
    }

    @Test
    public void executeCudRequete() {
        // On déclare la requete
        String sql = "INSERT INTO voiture (id, marque, model) VALUES (?, ?, ?)";
        // Crée la liste pour avoir des datas (position, type, valeur)
        ArrayList<Data> datas = new ArrayList<>();
        datas.add(new Data(1, "int", "1"));
        datas.add(new Data(2, "String", "testMarque"));
        datas.add(new Data(3, "String", "testModel"));

        try {
            BDD bdd = new BDD();
            // On effectue l'INSERT (execution de la requete)
            bdd.executeCudRequete(sql, datas);

            // On vérifie que l'INSERT a bien été fait, on prépare donc une requete SELECT
            String selectSQL = "SELECT * FROM voiture WHERE id = 1";
            //On execute la requete
            ResultSet rs = bdd.selectRequete(selectSQL);
            // On vérifie que les données ont bien été rentrée
            assertTrue("Le résultat ne devrait pas être vide", rs.next());
            // On vérifie la valeur de la colonne marque pour etre sur que l'INSERT s'est bien fait sans encombre
            assertEquals("testMarque", rs.getString("marque"));
            // Ferme le ResultSet après utilisation.
            rs.close();
        } catch (Exception e) {
            fail("Echec du test");
        }
    }

    @Test
    public void testSelectRequete() {
        // on prépare la requete
        String sql = "SELECT * FROM voiture WHERE id = 1";

        try {
            BDD bdd = new BDD();
            // execution de la requete
            ResultSet rs = bdd.selectRequete(sql);

            // On vérifie que le ResultSet retourné n'est pas null
            assertNotNull(rs);

            // On vérifie que le ResultSet contient au moins une ligne de données (on attend true)
            assertTrue(rs.next());

            // On vérifie que la colonne "id" de la première ligne du ResultSet est égale à 1
            assertEquals(1, rs.getInt("id"));

            // On vérifie que "marque" est égale à ce qu'on a rentré (testmarque)
            assertEquals("testMarque", rs.getString("marque"));

            // On vérifie que "model" est égale à "TestModel"
            assertEquals("testModel", rs.getString("model"));

            rs.close();
        } catch (Exception e) {
            fail("Erreur lors du test");
        }
    }


}