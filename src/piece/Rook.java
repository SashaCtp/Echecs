package piece;

import game.Color;
import game.Coord;
import game.Direction;

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

    public Direction[] getLegalDirections(){
        return new Direction[]{
                new Direction(1, 0),
                new Direction(-1, 0),
                new Direction(0, 1),
                new Direction(0, -1)
        };
    }

}
