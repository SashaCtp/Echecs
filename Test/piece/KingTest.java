package piece;

import board.Chessboard;
import game.Color;
import game.Coord;
import game.interfaces.IPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void canMove() {

        Chessboard b = new Chessboard();
        King king = new King(Color.WHITE);
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

    @Test
    void isBlocked(){

        Chessboard b = new Chessboard();
        King k = new King(Color.WHITE);

        // Déplacement normal, sans aucune pièce bloquante
        b.place(k, new Coord(2,2));
        assertFalse(k.isBlocked(new Coord(2,2), new Coord(3,3), b));
        assertFalse(k.isBlocked(new Coord(2,2), new Coord(1,2), b));

        // Bloqué par une pièce Blanche sur la case de destination (à gauche du roi)
        b.place(new Rook(Color.WHITE), new Coord(1,2));
        assertTrue(k.isBlocked(new Coord(2,2), new Coord(1,2), b));
        b.removePieceAt(new Coord(1,2));

        // Pas bloqué par une pièce Noire sur la case de destination
        b.place(new Rook(Color.BLACK), new Coord(5,2));
        assertFalse(k.isBlocked(new Coord(2,2), new Coord(5,2), b));
        b.removePieceAt(new Coord(5,2));

    }
}