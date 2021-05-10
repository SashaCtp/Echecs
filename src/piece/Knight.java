package piece;

import game.Color;
import game.Coord;
import game.Direction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    @Override
    boolean matchPattern(Coord from, Coord to) {

        int dx = from.getColumn() - to.getColumn();
        int dy = from.getRow() - to.getRow();

        return (Math.abs(dx) == 2 && Math.abs(dy) == 1) || (Math.abs(dx) == 1 && Math.abs(dy) == 2);

    }

    @Override
    public List<Direction> getLegalDirections() {

        Direction d1 = new Direction(1,2);
        Direction d2 = new Direction(2,1);

        ArrayList<Direction> directions = new ArrayList<>();

        for(int i = -1; i <= 1; i+=2){

            for(int j = -1; j <= 1; j+=2){

                directions.add(new Direction(d1.getDx()*i, d1.getDy()*j));
                directions.add(new Direction(d2.getDx()*i, d2.getDy()*j));

            }

        }

        return directions;
    }

    @Override
    char getLowerSymbole() {
        return 'c';
    }
}
