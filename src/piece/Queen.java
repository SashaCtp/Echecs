package piece;

import game.Color;
import game.Coord;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }

    @Override
    boolean matchPattern(Coord from, Coord to) {

        int dx = from.getColumn() - to.getColumn();
        int dy = from.getRow() - to.getRow();

        return (dx != 0 && dy == 0) || (dx == 0 && dy!= 0) || (Math.abs(dx) == Math.abs(dy) && dx != 0);

    }

    @Override
    public List<Direction> getLegalDirections() {
        ArrayList<Direction> directions = new ArrayList<>();
        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){

                if(!(x == 0 && y== 0))
                    directions.add(new Direction(x, y));

            }
        }
        return directions;
    }

    @Override
    char getLowerSymbole() {
        return 'd';
    }

}
