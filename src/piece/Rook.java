package piece;

import game.Color;
import game.Coord;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean matchPattern(Coord from, Coord to){

        int dx = from.getColumn() - to.getColumn();
        int dy = from.getRow() - to.getRow();

        return (dx != 0 && dy == 0) || (dx == 0 && dy!= 0);

    }

    @Override
    public char getLowerSymbole() {
        return 't';
    }
}
