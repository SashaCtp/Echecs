package piece;

import game.Color;
import game.Coord;
import game.Direction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void matchPattern() {

        Bishop b = new Bishop(Color.BLACK);

        // Le fou ne peut pas rester sur place
        assertFalse(b.matchPattern(new Coord(1,1), new Coord(1,1)));

        // Le fou ne peut pas se déplacer horizontalement ou verticalement
        assertFalse(b.matchPattern(new Coord(4,4), new Coord(8,4)));
        assertFalse(b.matchPattern(new Coord(4,4), new Coord(1,4)));
        assertFalse(b.matchPattern(new Coord(4,4), new Coord(4,1)));
        assertFalse(b.matchPattern(new Coord(4,4), new Coord(4,8)));
        assertFalse(b.matchPattern(new Coord(4,4), new Coord(3,4)));
        assertFalse(b.matchPattern(new Coord(4,4), new Coord(4,3)));

        // Le fou peut uniquement se déplacer en diagonale
        assertTrue(b.matchPattern(new Coord(1,1), new Coord(8,8)));
        assertTrue(b.matchPattern(new Coord(1,8), new Coord(8,1)));
        assertTrue(b.matchPattern(new Coord(8,1), new Coord(1,8)));
        assertTrue(b.matchPattern(new Coord(8,8), new Coord(1,1)));

    }

    @Test
    void getLegalDirections() {
        Bishop b = new Bishop(Color.BLACK);
        List<Direction> legalDirections = b.getLegalDirections();
        assertTrue(legalDirections.contains(new Direction(1,1)));
        assertTrue(legalDirections.contains(new Direction(1,-1)));
        assertTrue(legalDirections.contains(new Direction(-1,1)));
        assertTrue(legalDirections.contains(new Direction(-1,-1)));
    }

    @Test
    void getLowerSymbole() {
        Bishop b = new Bishop(Color.WHITE);
        assertEquals('b', b.getLowerSymbole());
    }
}