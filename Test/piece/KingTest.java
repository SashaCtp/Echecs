package piece;

import board.Board;
import game.Color;
import game.Coord;
import game.interfaces.IPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void canMove() {

        Board b = new Board();
        IPiece king = new King(Color.WHITE);
        b.place(king, new Coord(1,1));

        // On vérifie d'abord qu'il peut se déplacer vers les cases voisines
        assertTrue(king.canMove(new Coord(1,1), new Coord(2,1), b));
        assertTrue(king.canMove(new Coord(1,1), new Coord(2,2), b));
        assertTrue(king.canMove(new Coord(1,1), new Coord(1,2), b));

        // Il ne peut pas se déplacer en dehors des cases voisines
        assertFalse(king.canMove(new Coord(1,1), new Coord(3,1), b));
        assertFalse(king.canMove(new Coord(1,1), new Coord(1,3), b));
        assertFalse(king.canMove(new Coord(1,1), new Coord(3,3), b));

        // Lorsque l'on place une pièce alliée sur une case, il ne peut plus y aller
        b.place(new King(Color.WHITE), new Coord(2,2));
        assertTrue(king.canMove(new Coord(1,1), new Coord(2,1), b));
        assertFalse(king.canMove(new Coord(1,1), new Coord(2,2), b));
        assertTrue(king.canMove(new Coord(1,1), new Coord(1,2), b));

    }
}