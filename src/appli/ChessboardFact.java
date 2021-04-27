package appli;

import board.Chessboard;
import game.interfaces.ChessboardFactory;
import game.interfaces.IChessboard;

public class ChessboardFact implements ChessboardFactory {
    @Override
    public IChessboard newChessboard() {
        return new Chessboard();
    }
}
