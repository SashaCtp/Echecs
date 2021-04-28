package piece;

import board.Chessboard;
import game.Color;
import game.Coord;
import game.Game;
import game.interfaces.IChessboard;
import game.interfaces.IPiece;

import java.util.HashMap;

public abstract class Piece implements IPiece {

    private Color color;

    public Piece(Color color){
        this.color = color;
    }

    @Override
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

    @Override
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

    @Override
    public boolean isAttacked(Coord coord, IChessboard chessboard){

        for(HashMap.Entry<Coord, IPiece> entry : chessboard.getColorPieces(Game.getOpponentColor(this.getColor())).entrySet()){

            if(entry.getValue().canMove(entry.getKey(), coord, chessboard))
                return true;

        }

        return false;
    }

    @Override
    public char getSymbole(){

        if(this.getColor().equals(Color.WHITE))
            return Character.toUpperCase(this.getLowerSymbole());

        return this.getLowerSymbole();
    }

    @Override
    public boolean isCheckable(){
        return false;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    // Méthodes abstraites ==========----------

    /**
     * Vérifie si le déplacement correspond au pattern de déplacement de la pièce
     * @param from Coordonnées de la case d'origine
     * @param to Coordonnées de la case de destination
     * @return True : Le déplacement correspond, False sinon
     */
    abstract boolean matchPattern(Coord from, Coord to);

    /**
     * Retourne le symbole en minuscule
     * @return Symbole en minuscule
     */
    abstract char getLowerSymbole();

}
