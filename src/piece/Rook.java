package piece;

import game.Color;
import game.Coord;

import java.util.ArrayList;
import java.util.List;

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

    public List<Direction> getLegalDirections(){
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(new Direction(1, 0));
        directions.add(new Direction(-1, 0));
        directions.add(new Direction(0,1));
        directions.add(new Direction(0, -1));
        return directions;
    }

}
