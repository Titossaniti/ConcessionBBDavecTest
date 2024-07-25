package test;

import Database.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataTest {

    @Test
    public void testDataConstructor() {
        // nouvelle instance de data avec des valeurs pour tester
        Data data = new Data(1, "int", "123");

        // Vérifie que la position est correctement initialisée
        assertEquals(1, data.position);

        // Vérifie que le type est correctement initialisé
        assertEquals("int", data.type);

        // Vérifie que la valeur est correctement initialisée
        assertEquals("123", data.valeur);
    }
}
