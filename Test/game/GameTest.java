package game;

import appli.ChessboardFact;
import appli.PlayerFact;
import board.Chessboard;
import game.interfaces.IPiece;
import org.junit.jupiter.api.Test;
import piece.*;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testParties(){

        /*
         * Bug rencontré lors d'une partie d'essai
         */
        Chessboard board = new Chessboard();
        // Whites
        board.place(new King(Color.WHITE), new Coord(5,1));
        board.place(new Bishop(Color.WHITE), new Coord(1,3));
        board.place(new Knight(Color.WHITE), new Coord(4,3));
        board.place(new Queen(Color.WHITE), new Coord(1,4));
        board.place(new Rook(Color.WHITE), new Coord(2,7));

        // Blacks
        board.place(new King(Color.BLACK),new Coord(6,6));
        board.place(new Bishop(Color.BLACK),new Coord(8,6));

        // Sans le cavalier le roi blanc n'est pas en échec
        assertFalse(board.isCheck(Color.WHITE));
        board.place(new Knight(Color.BLACK),new Coord(7,2));

        // Le roi blanc doit être en échec car le cavalier Noir peut l'attaquer
        assertTrue(board.isCheck(Color.WHITE));
        assertTrue(board.getPieceAt(new Coord(7,2)).canMove(new Coord(7,2), new Coord(5,1), board));
        assertTrue(board.getPieceAt(new Coord(5,1)).isAttacked(new Coord(5,1), board));

        // Il est impossible de déplacer une autre pièce que le roi
        for(Map.Entry<Coord, IPiece> entry : board.getColorPieces(Color.WHITE).entrySet()){

            if(!entry.getKey().equals(new Coord(5,1)))
                assertEquals(new ArrayList<Coord>(), board.getPieceAt(entry.getKey()).getLegalMoves(entry.getKey(), board));
        }

    }

    @Test
    void parseAction(){

    }

    @Test
    void isCheckMate() {

        /*
         * Dans cette situation, le roi noir est mat en a8 par les tours blanches en a1 et b3
         */
        Game game = new Game(null, new ChessboardFact(), new PlayerFact());

        // Les noirs n'ont plus de pièce à déplacer : MAT
        game.getChessboard().place(new King(Color.BLACK), new Coord(1,8));
        game.getChessboard().place(new Rook(Color.WHITE), new Coord(1,1));
        game.getChessboard().place(new Rook(Color.WHITE), new Coord(2,3));

        assertTrue(game.isCheckMate(Color.BLACK));

        // En ajoutant une tour capable de prendre la tour en a1, ce n'est pas mat
        game.getChessboard().place(new Rook(Color.BLACK), new Coord(3,1));

        assertFalse(game.isCheckMate(Color.BLACK));

    }

    @Test
    void isPat() {

        /*
         * Dans cette situation, le roi noir est bloqué en a8 par les tours blanches en b1 et h7
         */
        Game game = new Game(null, new ChessboardFact(), new PlayerFact());

        // Les noirs n'ont plus de pièce à déplacer : PAT
        game.getChessboard().place(new King(Color.BLACK), new Coord(1,8));
        game.getChessboard().place(new Rook(Color.WHITE), new Coord(2,1));
        game.getChessboard().place(new Rook(Color.WHITE), new Coord(8,7));

        assertTrue(game.isPat(Color.BLACK));

        // En rajoutant une pièce noire qui peut se déplacer : plus de PAT
        game.getChessboard().place(new Rook(Color.BLACK), new Coord(4,4));
        assertFalse(game.isPat(Color.BLACK));

    }
}