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
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(new Direction(1,1));
        directions.add(new Direction(1,-1));
        directions.add(new Direction(-1,1));
        directions.add(new Direction(-1,-1));
        return directions;
    }

    @Override
    char getLowerSymbole() {
        return 'b';
    }
}
