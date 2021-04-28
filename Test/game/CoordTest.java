package game;

import game.exceptions.WrongCoordinatesFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordTest {

    @Test
    @DisplayName("Init Coord")
    public void testInit(){

        // Initialisation sans paramètres
        Coord coord = new Coord();
        assertEquals(0, coord.getRow());
        assertEquals(0, coord.getColumn());

        // Initialisation avec coordonnées
        coord = new Coord(0, 0);
        assertEquals(0, coord.getRow());
        assertEquals(0, coord.getColumn());

        // Initialisation avec un chaîne de caractère

        // Lorsque le format est incorrect, on ne peut pas instancier coord
        assertThrows(WrongCoordinatesFormatException.class, () -> new Coord(""));
        assertThrows(WrongCoordinatesFormatException.class, () -> new Coord("a"));
        assertThrows(WrongCoordinatesFormatException.class, () -> new Coord("1"));
        assertThrows(WrongCoordinatesFormatException.class, () -> new Coord("1a"));
        assertThrows(WrongCoordinatesFormatException.class, () -> new Coord("a1a"));


        assertDoesNotThrow(() -> new Coord("b8"));
        assertDoesNotThrow(() -> new Coord("e3"));

        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        for(int i = 1; i <= 8; i++){
            for(char c : chars) {
                String str = "" + c + i;
                assertDoesNotThrow(() -> new Coord(str));
            }
        }

    }

    @Test
    public void testParser(){

        // On vérifie que lorsque l'on donne "a1" en entrée, on obtient (1,1) en sortie
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        for(int i = 1; i <= 8; i++){
            for(int j = 0; j <= 7; j++){
                String str = "" + chars[j] + i;
                int finalI = i;
                int finalJ = j;
                assertDoesNotThrow(() -> {
                    Coord c = new Coord(str);
                    assertEquals(finalJ +1, c.getColumn());
                    assertEquals(finalI, c.getRow());
                });

            }
        }

    }

    @Test
    void next(){

        Coord coord = new Coord(1,1);

        Direction direction = new Direction(1,0);

        assertEquals(new Coord(2,1) ,coord.next(direction));

    }

    @Test
    void testEquals(){

        assertDoesNotThrow(() -> {
            Coord b7 = new Coord("b7");
            Coord b8 = new Coord("b8");
            Coord c = new Coord("b7");

            assertEquals(b7, c);
            assertNotEquals(b8, c);
        });

    }

    @Test
    void testToString(){

        Coord c = new Coord(1,1);
        assertEquals("a1", c.toString());

        c = new Coord(2,2);
        assertEquals("b2", c.toString());

        c = new Coord(4, 5);
        assertEquals("d5", c.toString());

    }

}