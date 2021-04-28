package appli;

import game.Color;
import game.interfaces.IPiece;
import game.interfaces.PieceFactory;
import piece.King;
import piece.Queen;
import piece.Rook;

public class PieceFact implements PieceFactory {

    @Override
    public IPiece newPiece(int type, Color color) {
        if(color == null)
            return null;

        switch (type){
            case 0:
                return new King(color);
            case 1:
                return new Queen(color);
            case 2:
                return new Rook(color);
            default:
                return null;
        }
    }
}
