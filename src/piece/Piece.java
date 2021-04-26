package piece;

import game.Color;
import game.Coord;
import game.Game;
import game.interfaces.Chessboard;
import game.interfaces.IPiece;

public abstract class Piece implements IPiece {

    /**
     * Vérifie si la pièce est attaquée
     * @param chessboard Échiquier sur lequel se trouve la pièce
     * @return True: La pièce est attaquée par une ou plusieurs pièces, False sinon
     */
    public boolean isAttacked(Chessboard chessboard){

        for(IPiece piece : chessboard.getColorPieces(Game.getOpponentColor(this.getColor()))){

            if(piece.canMove(chessboard.getCoordinates(piece), chessboard.getCoordinates(this), chessboard))
                return true;

        }

        return false;
    }

}
