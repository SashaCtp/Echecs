package piece;

import game.Color;
import game.Coord;
import game.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void matchPattern() {

        Knight k = new Knight(Color.BLACK);

        // Un cavalier ne peut pas rester sur place
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(1,1)));

        // Un cavalier ne peut pas se déplacer :
        // - Horizontalement
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(8,1)));
        // - Verticalement
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(1,8)));
        // - en diagonale
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(8,8)));
        assertFalse(k.matchPattern(new Coord(5,5), new Coord(2,8)));

        // Un cavalier se déplace sous la forme d'un L
        assertTrue(k.matchPattern(new Coord(1,1), new Coord(2,3)));
        assertTrue(k.matchPattern(new Coord(8,8), new Coord(6,7)));
        assertTrue(k.matchPattern(new Coord(4,4), new Coord(6,3)));

    }

    @Test
    void getLegalDirections() {
        Knight k = new Knight(Color.BLACK);
        ArrayList<Direction> directions = (ArrayList<Direction>) k.getLegalDirections();

        Direction[] expected = new Direction[]{
                new Direction(1,2),
                new Direction(-1,2),
                new Direction(1,-2),
                new Direction(-1,-2),
                new Direction(2,1),
                new Direction(-2,1),
                new Direction(2,-1),
                new Direction(-2,-1),
        };

        System.out.println(directions);

        for(int i = 0; i < 8; i++){
            assertTrue(directions.contains(expected[i]));
        }

        assertEquals(directions.size(), 8);
    }

    @Test
    void getLowerSymbole() {
        Knight k = new Knight(Color.WHITE);
        assertEquals('c', k.getLowerSymbole());
    }
}