package piece;

import game.Color;
import game.Coord;
import game.interfaces.IChessboard;

public class King extends Piece {

    public King(Color color){
        super(color);
    }

    @Override
    public boolean matchPattern(Coord from, Coord to) {

        int dx = from.getColumn() - to.getColumn();
        int dy = from.getRow() - to.getRow();

        return (Math.abs(dx) <= 1 || Math.abs(dy) <= 1) && !(dx == 0 && dy == 0);

    }

    @Override
    public boolean isBlocked(Coord from, Coord to, IChessboard board){

        return (!board.isFree(to) && board.getPieceAt(to).getColor().equals(this.getColor()));

    }

    @Override
    public boolean isCheckable() {
        return true;
    }

    public char getLowerSymbole() {
        return 'r';
    }

}
