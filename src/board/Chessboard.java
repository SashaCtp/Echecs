package board;

import game.Color;
import game.exceptions.CoordinatesOutOfBoundsException;
import game.exceptions.IllegalMoveException;
import game.exceptions.WrongColorException;
import game.interfaces.IChessboard;
import game.Coord;
import game.interfaces.IPiece;
import game.exceptions.EmptySquareException;

import java.util.HashMap;

public class Chessboard implements IChessboard {

    private int cols, rows;
    private HashMap<Coord, IPiece> pieces;

    /**
     * Créé un échiquier classique
     */
    public Chessboard(){
        this(8,8);
    }

    /**
     * Créé un échiquier avec un nbr de colonnes et de lignes paramétrable
     * @param cols Nombre de colonnes
     * @param rows Nombre de lignes
     */
    public Chessboard(int cols, int rows) {
        assert cols > 0 && rows > 0;

        this.cols = cols;
        this.rows = rows;

        this.pieces = new HashMap<>();
    }

    /**
     * Créé un échiquier par copie
     * @param chessboard Échiquier à copier
     */
    public Chessboard(IChessboard chessboard){
        this.cols = chessboard.getCols();
        this.rows = chessboard.getRows();

        this.pieces = new HashMap<>(chessboard.getPieces());
    }

    @Override
    public boolean isFree(Coord coo) {
        return !this.pieces.containsKey(coo);
    }

    @Override
    public IPiece getPieceAt(Coord coo) {
        if(isFree(coo))
            return null;

        return pieces.get(coo);
    }

    @Override
    public void move(Coord from, Coord to, Color playingColor) throws EmptySquareException, CoordinatesOutOfBoundsException, WrongColorException, IllegalMoveException {

        // Si les coordonnées ne sont pas correctes
        if(coordinatesOutOfBounds(from) || coordinatesOutOfBounds(to))
            throw new CoordinatesOutOfBoundsException();

        // S'il n'y a pas de pièce sélectionnée
        if(isFree(from))
            throw new EmptySquareException();

        IPiece movingPiece = getPieceAt(from);

        // Si la pièce n'est pas de la bonne couleur
        if(!movingPiece.getColor().equals(playingColor))
            throw new WrongColorException();

        if(movingPiece.canMove(from, to, this)){
            this.removePiece(movingPiece);

            if(!this.isFree(to))
                this.removePieceAt(to);

            this.place(movingPiece, to);
        }else{
            throw new IllegalMoveException();
        }

    }

    @Override
    public boolean coordinatesOutOfBounds(Coord coord){

        return coord.getRow() <= 0 || coord.getColumn() <= 0 || coord.getRow() > this.rows || coord.getColumn() > this.cols;

    }

    @Override
    public HashMap<Coord, IPiece> getColorPieces(Color c) {

        HashMap<Coord, IPiece> colorPieces = new HashMap<>();

        for (HashMap.Entry<Coord, IPiece> entry : pieces.entrySet()){
            if (entry.getValue().getColor() == c)
                colorPieces.put(new Coord(entry.getKey()), entry.getValue());
        }

        return colorPieces;
    }

    @Override
    public boolean isCheck(Color c){

        for(HashMap.Entry<Coord, IPiece> entry : pieces.entrySet()){

            // Le joueur de la couleur est en échec si une pièce qui peut être mise en échec est attaquée
            if(entry.getValue().getColor() == c
                    && entry.getValue().isCheckable()
                    && entry.getValue().isAttacked(entry.getKey(), this))
                return true;

        }

        return false;
    }

    @Override
    public Coord getCoordinates(IPiece piece) {

        for(Coord coo : this.pieces.keySet()){
            if(this.pieces.get(coo).equals(piece))
                return coo;
        }

        return null;
    }

    @Override
    public void place(IPiece piece, Coord coo) {

        if(isFree(coo))
            this.pieces.put(coo, piece);

    }

    @Override
    public void removePiece(IPiece piece) {

        if(getCoordinates(piece) != null)
            removePieceAt(getCoordinates(piece));

    }

    @Override
    public void removePieceAt(Coord coo) {

        if(!isFree(coo))
            this.pieces.remove(coo);

    }

    @Override
    public String toString() {

        char[][] display = new char[19][37];

        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 37; j++)
                display[i][j] = ' ';
        }

        // Columns index
        char firstChar = 'a';
        for(int i = 0; i < 8; i++){
            display[0][4 * (i+1) ] = (char) (firstChar + (char) i);
            display[18][4 * (i+1) ] = (char) (firstChar + (char) i);
        }

        // Rows index
        char firstNumber = '8';
        for(int i = 0; i < 8; i++){
            display[2 * (i+1)][0] = (char) (firstNumber - (char) i);
            display[2 * (i+1)][36] = (char) (firstNumber - (char) i);
        }

        // Grid : Rows ---
        for(int y = 1; y < 19; y+=2){

            for(int i = 0; i < 8; i++){
                display[y][3+4*i] = '-';
                display[y][4+4*i] = '-';
                display[y][5+4*i] = '-';
            }

        }

        // Grid : Columns |||
        for(int i = 0; i <= 8; i++){
            for(int j = 0; j < 8; j++){
                display[(j+1)*2][2+i*4] = '|';
            }
        }

        // Pieces
        for(HashMap.Entry<Coord, IPiece> entry : this.pieces.entrySet())
            display[2 * (this.getRows() + 1 - entry.getKey().getRow())][4 * entry.getKey().getColumn()] = entry.getValue().getSymbole();

        StringBuilder str = new StringBuilder();
        for(char[] l : display) {
            str.append(l);
            str.append("\n");
        }

        return str.toString();

    }

    @Override
    public int getRows(){
        return this.rows;
    }

    @Override
    public int getCols(){
        return this.cols;
    }

    @Override
    public HashMap<Coord, IPiece> getPieces() {
        return this.pieces;
    }
}
