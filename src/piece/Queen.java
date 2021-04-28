package piece;

import game.Color;
import game.Coord;
import game.Direction;

import java.util.ArrayList;

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
    public Direction[] getLegalDirections() {
        ArrayList<Direction> directions = new ArrayList<>();
        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){

                if(!(x == 0 && y== 0))
                    directions.add(new Direction(x, y));

            }
        }
        return directions.toArray(new Direction[0]);
    }

    @Override
    char getLowerSymbole() {
        return 'd';
    }

}
