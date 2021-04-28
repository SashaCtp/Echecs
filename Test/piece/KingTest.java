package piece;

import board.Chessboard;
import game.Color;
import game.Coord;
import game.interfaces.IPiece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void getLegalMoves(){

        Chessboard b = new Chessboard();
        King k = new King(Color.WHITE);

        b.place(k, new Coord(4,4));

        ArrayList<Coord> legalMoves = k.getLegalMoves(new Coord(4,4), b);

        // On vérifie d'abord qu'il peut se déplacer vers les cases voisines
        assertTrue(legalMoves.contains(new Coord(3,3)));
        assertTrue(legalMoves.contains(new Coord(3,4)));
        assertTrue(legalMoves.contains(new Coord(4,3)));

        // Il ne peut pas se déplacer en dehors des cases voisines
        assertFalse(legalMoves.contains(new Coord(6,6)));
        assertFalse(legalMoves.contains(new Coord(4,6)));
        assertFalse(legalMoves.contains(new Coord(4,2)));

        // Lorsque l'on place une pièce alliée sur une case, il ne peut plus y aller (Pièce à gauche du roi)
        b.place(new King(Color.WHITE), new Coord(3,4));

        // On actualise la liste des mouvements légaux
        legalMoves = k.getLegalMoves(new Coord(4,4), b);

        assertTrue(legalMoves.contains(new Coord(5,4)));
        assertTrue(legalMoves.contains(new Coord(3,5)));
        assertTrue(legalMoves.contains(new Coord(3,3)));
        assertFalse(legalMoves.contains(new Coord(3,4)));

    }

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
    void matchPattern(){

        King k = new King(Color.WHITE);

        // Le roi ne peut pas rester sur place
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(1,1)));

        // Le roi ne peut se déplacer plus loin que les cases adjacentes
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(2,3)));
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(3,2)));
        assertFalse(k.matchPattern(new Coord(1,1), new Coord(4,1)));

        // La tour peut uniquement se déplacer sur les cases adjacentes
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){

                if(!(i == 0 && j == 0))
                    assertTrue(k.matchPattern(new Coord(4,4), new Coord(4+i,4+j)));

            }
        }

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

        // Pas bloqué par une pièce Noire sur la case de destination (a1)
        b.place(new Rook(Color.BLACK), new Coord(1,2));
        assertFalse(k.isBlocked(new Coord(2,2), new Coord(1,2), b));
        b.removePieceAt(new Coord(5,2));

    }
}