package appli;

import game.Color;
import game.interfaces.IPiece;
import game.interfaces.PieceFactory;
import piece.King;
import piece.Rook;

public class PieceFact implements PieceFactory {

    @Override
    public IPiece newPiece(int type, Color color) {
        if(color == null)
            return null;

        if(type == 0)
            return new King(color);

        if(type == 1)
            return new Rook(color);

        return null;
    }
}
