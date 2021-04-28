package game;

import appli.ChessboardFact;
import appli.PlayerFact;
import board.Chessboard;
import game.interfaces.IChessboard;
import org.junit.jupiter.api.Test;
import piece.King;
import piece.Rook;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void parseAction(){

        Game game = new Game(null, new ChessboardFact(), new PlayerFact());

        

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