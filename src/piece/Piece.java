package piece;

import board.Chessboard;
import game.Color;
import game.Coord;
import game.Game;
import game.interfaces.IChessboard;
import game.interfaces.IPiece;

public abstract class Piece implements IPiece {

    private Color color;

    public Piece(Color color){
        this.color = color;
    }

    public boolean canMove(Coord from, Coord to, IChessboard board) {

        // Si le déplacement correspond au pattern de la piece
        if(!matchPattern(from, to))
            return false;

        // S'il n'y a pas d'obstacle sur le chemin
        if(isBlocked(from, to, board))
            return false;

        // Si le déplacement ne met pas le roi en danger (en échec)
        // => Pour cela, on effectue le déplacement sur un faux échiquier
        // => Si le roi est en échec : illégal
        Chessboard tmpBoard = new Chessboard(board);
        tmpBoard.removePiece(this);
        if(!tmpBoard.isFree(to))
            tmpBoard.removePieceAt(to);
        tmpBoard.place(this, to);

        if(tmpBoard.isCheck(this.getColor()))
            return false;

        return true;
    }

    public boolean isBlocked(Coord from, Coord to, IChessboard board){
        assert matchPattern(from, to);

        Coord current = new Coord(from);

        // Direction du déplacement
        int dx = (int) Math.signum(to.getColumn() - from.getColumn());
        int dy = (int) Math.signum(to.getRow() - from.getRow());

        while(!current.equals(to)){

            current = new Coord(current.getColumn() + dx, current.getRow() + dy);

            if(!board.isFree(current)){

                // Le déplacement est bloqué si :
                // - une pièce est sur le passage (peut importe la couleur), et pas à la case d'arrivée
                // - une pièce de la même couleur à la case d'arrivée
                if(!current.equals(to)
                        || (current.equals(to) && board.getPieceAt(current).getColor().equals(this.getColor()))){
                    return true;
                }

            }

        }
        // Si rien ne bloque, on retourne true
        return false;
    }

    /**
     * Vérifie si la pièce est attaquée
     * @param chessboard Échiquier sur lequel se trouve la pièce
     * @return True: La pièce est attaquée par une ou plusieurs pièces, False sinon
     */
    public boolean isAttacked(IChessboard chessboard){

        for(IPiece piece : chessboard.getColorPieces(Game.getOpponentColor(this.getColor()))){

            if(piece.canMove(chessboard.getCoordinates(piece), chessboard.getCoordinates(this), chessboard))
                return true;

        }

        return false;
    }

    public char getSymbole(){

        if(this.getColor().equals(Color.WHITE))
            return Character.toUpperCase(this.getLowerSymbole());

        return this.getLowerSymbole();
    }

    public boolean isCheckable(){
        return false;
    }

    public Color getColor() {
        return this.color;
    }

    abstract char getLowerSymbole();

}
