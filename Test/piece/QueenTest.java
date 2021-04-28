package piece;

import board.Chessboard;
import game.Color;
import game.Coord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void matchPattern() {

        Chessboard b = new Chessboard();
        Queen q = new Queen(Color.WHITE);

        b.place(q, new Coord(4,4));

        // Pas de déplacement sur place
        assertFalse(q.matchPattern(new Coord(4,4), new Coord(4,4)));

        // Déplacement en diagonale
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(1,1)));
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(8,8)));
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(1,7)));
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(7,1)));

        // Déplacement horizontal et vertical
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(1,4)));
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(8,4)));
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(4,1)));
        assertTrue(q.matchPattern(new Coord(4,4), new Coord(4,8)));

        // Les déplacements en dehors des ceux au dessus sont interdits
        assertFalse(q.matchPattern(new Coord(4,4), new Coord(5,6)));
        assertFalse(q.matchPattern(new Coord(4,4), new Coord(6,5)));
        assertFalse(q.matchPattern(new Coord(4,4), new Coord(1,8)));
        assertFalse(q.matchPattern(new Coord(4,4), new Coord(8,1)));


    }

}