package piece;

import game.Color;
import game.Coord;
import game.interfaces.Chessboard;

public class King extends Piece {

    private Color color;

    public King(Color color){
        this.color = color;
    }
    
    @Override
    public boolean canMove(Coord from, Coord to, Chessboard board) {

        // Le roi ne peut se déplacer que dans les cases environnantes
        if(Math.abs(from.getColumn()-to.getColumn()) > 1 || Math.abs(from.getRow()-to.getRow()) > 1)
            return false;

        return false;
    }

    @Override
    public boolean isCheckable() {
        return true;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public char getSymbole() {
        return 'r';
    }
}
