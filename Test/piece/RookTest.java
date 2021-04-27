package piece;

import board.Chessboard;
import game.Color;
import game.Coord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void canMove() {

        Chessboard b = new Chessboard();
        Rook r = new Rook(Color.WHITE);

        b.place(r, new Coord(1,1));

        assertTrue(r.canMove(new Coord(1,1), new Coord(1,8), b));
        assertTrue(r.canMove(new Coord(1,1), new Coord(8,1), b));

        // Mise en danger du roi:
        // - Roi blanc : b2
        // - Tour blanche : b4
        // - Tour noire : b8
        Chessboard b1 = new Chessboard();
        b1.place(new King(Color.WHITE), new Coord(2,2));
        b1.place(r, new Coord(2, 4));
        b1.place(new Rook(Color.BLACK), new Coord(2, 8));

        assertFalse(r.canMove(new Coord(2,4), new Coord(4,4), b1));

        assertDoesNotThrow(() -> b1.move(new Coord(2, 8), new Coord(8, 8), Color.BLACK));

        // En enlevant la tour qui menace le roi : la tour peut se déplacer
        assertTrue(r.canMove(new Coord(2,4), new Coord(4,4), b1));

    }

    @Test
    void matchPattern(){

        Rook r = new Rook(Color.WHITE);

        // La tour ne peut pas rester sur place
        assertFalse(r.matchPattern(new Coord(1,1), new Coord(1,1)));

        // La tour ne peut pas se déplacer en diagonale
        assertFalse(r.matchPattern(new Coord(1,1), new Coord(8,8)));

        // La tour se déplace soit horizontalement, soit verticalement
        assertTrue(r.matchPattern(new Coord(2,2), new Coord(8,2)));

    }

    @Test
    void isBlocked(){

        Chessboard b = new Chessboard();
        Rook r1 = new Rook(Color.WHITE);

        // Déplacement normal, sans aucune pièce bloquante
        b.place(r1, new Coord(2,2));
        assertFalse(r1.isBlocked(new Coord(2,2), new Coord(5,2), b));
        assertFalse(r1.isBlocked(new Coord(2,2), new Coord(2,5), b));

        // Bloqué par une pièce sur le chemin (pas la case de destination)
        b.place(new Rook(Color.WHITE), new Coord(3,2));
        assertTrue(r1.isBlocked(new Coord(2,2), new Coord(5,2), b));
        b.removePieceAt(new Coord(3,2));

        // Bloqué par une pièce Blanche sur la case de destination
        b.place(new Rook(Color.WHITE), new Coord(5,2));
        assertTrue(r1.isBlocked(new Coord(2,2), new Coord(5,2), b));
        b.removePieceAt(new Coord(5,2));

        // Pas bloqué par une pièce Noire sur la case de destination
        b.place(new Rook(Color.BLACK), new Coord(5,2));
        assertFalse(r1.isBlocked(new Coord(2,2), new Coord(5,2), b));
        b.removePieceAt(new Coord(5,2));

    }

}