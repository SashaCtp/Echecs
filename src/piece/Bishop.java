package piece;

import game.Color;
import game.Coord;
import game.Direction;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    boolean matchPattern(Coord from, Coord to) {

        int dx = from.getColumn() - to.getColumn();
        int dy = from.getRow() - to.getRow();

        return Math.abs(dx) == Math.abs(dy) && dx != 0;
    }

    @Override
    public List<Direction> getLegalDirections() {
        return new ArrayList<>();
    }

    @Override
    char getLowerSymbole() {
        return 'b';
    }
}
