package board;

import game.Color;
import game.exceptions.CoordinatesOutOfBoundsException;
import game.exceptions.EmptySquareException;
import game.exceptions.WrongColorException;
import game.interfaces.IChessboard;
import game.Coord;
import game.interfaces.IPiece;
import org.junit.jupiter.api.Test;
import piece.King;
import piece.Rook;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ChessboardTest {

    @Test
    void isFree() {

        Chessboard b = new Chessboard();
        for(int c = 1; c <= 8; c++){
            for(int r = 1; r <= 8; r++){
                assertTrue(b.isFree(new Coord(c,r)));
            }
        }

        int c = (new Random()).nextInt(8) + 1;
        int r = (new Random()).nextInt(8) + 1;

        // TODO : Ajouter le test lorsque l'on place une pièce au hasard sur l'échiquier la case n'est plus free

    }

    @Test
    void getPieceAt() {

        Chessboard b = new Chessboard();
        for(int c = 1; c <= 8; c++){
            for(int r = 1; r <= 8; r++){
                assertNull(b.getPieceAt(new Coord(c,r)));
            }
        }

        int c = (new Random()).nextInt(8) + 1;
        int r = (new Random()).nextInt(8) + 1;

        IPiece piece = new King(Color.WHITE);

        b.place(piece, new Coord(c, r));

        assertNotNull(b.getPieceAt(new Coord(c, r)));

        assertEquals(piece, b.getPieceAt(new Coord(c, r)));

        // TODO : Ajouter le test lorsque l'on place une pièce au hasard sur l'échiquier, on peut la récupérer aux coordonnées

    }

    @Test
    void move() {

        Chessboard board = new Chessboard();

        // Lorsque des coordonnées invalides sont utilisés : CoordinatesOutOfBoundsException
        assertThrows(CoordinatesOutOfBoundsException.class, ()->board.move(new Coord(20, 6), new Coord(7, 7), Color.WHITE));
        assertThrows(CoordinatesOutOfBoundsException.class, ()->board.move(new Coord(0, 6), new Coord(7, 7), Color.WHITE));
        assertThrows(CoordinatesOutOfBoundsException.class, ()->board.move(new Coord(4, 6), new Coord(1, 90), Color.WHITE));

        // Lorsque la case de départ ne contient pas de pièce : EmptySquareException
        for(int c = 1; c <= 8; c++){
            for(int r = 1; r <= 8; r++){
                int finalC = c;
                int finalR = r;

                assertThrows(EmptySquareException.class, () -> board.move(new Coord(finalC, finalR), new Coord(9-finalR,finalC), Color.WHITE));
            }
        }

        // Lorsque la couleur de la pièce n'est pas celle du joueur : WrongColorException
        board.place(new Rook(Color.BLACK), new Coord(1,1));

        assertThrows(WrongColorException.class, () -> board.move(new Coord(1,1), new Coord(1,2), Color.WHITE));
        assertDoesNotThrow(() -> board.move(new Coord(1,1), new Coord(1,2), Color.BLACK));

        // On vient maintenant vérifier si un déplacement est légal ou non

    }

    @Test
    void getColorPieces() {

    }

    @Test
    void isCheck(){

        // Le roi noir est en : d4
        // La tour noire est en : a3
        // La tour blanche est en : d1
        Chessboard b = new Chessboard();

        assertDoesNotThrow(() -> b.place(new King(Color.BLACK), new Coord("d4")));
        assertDoesNotThrow(() -> b.place(new Rook(Color.BLACK), new Coord("a3")));

        // Sans pièce menaçant le roi : les noirs ne sont pas en échec
        assertFalse(b.isCheck(Color.BLACK));

        // On ajoute la tour blanche
        assertDoesNotThrow(() -> b.place(new Rook(Color.WHITE), new Coord("d1")));

        // Le roi passe en échec
        assertTrue(b.isCheck(Color.BLACK));
        assertFalse(b.isCheck(Color.WHITE));

        // On déplace maintenant la tour noire devant le roi pour le protéger
        assertDoesNotThrow(() -> b.move(new Coord("a3"), new Coord("d3"), Color.BLACK));

        // Le roi noir n'est plus en échec
        assertFalse(b.isCheck(Color.BLACK));
        assertFalse(b.isCheck(Color.WHITE));

    }

    @Test
    void getCoordinates() {

        IPiece p1 = new King(Color.WHITE);
        IPiece p2 = new King(Color.WHITE);

        Coord c1 = new Coord(1,1);
        Coord c2 = new Coord(7, 8);

        IChessboard cb = new Chessboard();
        cb.place(p1, c1);
        cb.place(p2, c2);

        assertEquals(c1, cb.getCoordinates(p1));
        assertEquals(c2, cb.getCoordinates(p2));

        // Bien qu'il s'agisse de deux rois blancs, on doit pouvoir récupérer leurs coordonnées respectifs
        assertNotEquals(c1, cb.getCoordinates(p2));
        assertNotEquals(c2, cb.getCoordinates(p1));

    }

    @Test
    void place() {
    }

    @Test
    void removePiece() {
    }

    @Test
    void removePieceAt() {
    }

}