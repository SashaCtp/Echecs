package piece;

import game.Color;
import game.Coord;
import game.Direction;
import game.interfaces.IChessboard;

import java.util.ArrayList;

public class King extends Piece {

    // TODO : Ajouter le ROQUE

    public King(Color color){
        super(color);
    }

    @Override
    public boolean matchPattern(Coord from, Coord to) {

        int dx = from.getColumn() - to.getColumn();
        int dy = from.getRow() - to.getRow();

        return (Math.abs(dx) <= 1 && Math.abs(dy) <= 1) && !(dx == 0 && dy == 0);

    }

    @Override
    public boolean isCheckable() {
        return true;
    }

    public Direction[] getLegalDirections(){

        ArrayList<Direction> directions = new ArrayList<>();

        for(int x = -1; x <= 1; x++){

            for(int y = -1; y <= 1; y++){

                if(!(x == 0 && y== 0))
                    directions.add(new Direction(x, y));

            }

        }

        return directions.toArray(new Direction[0]);

    }

    public char getLowerSymbole() {
        return 'r';
    }

}
