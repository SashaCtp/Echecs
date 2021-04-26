package appli;

import game.Color;
import game.interfaces.IPiece;
import game.interfaces.PieceFactory;
import piece.King;

public class PieceFact implements PieceFactory {

    @Override
    public IPiece newPiece(int type, Color color) {
        if(color == null)
            return null;

        if(type == 0)
            return new King(color);

        return null;
    }
}
