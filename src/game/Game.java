package game;

import game.exceptions.CoordinatesOutOfBoundsException;
import game.exceptions.InvalidPlayerInput;
import game.exceptions.WrongCoordinatesFormatException;
import game.interfaces.*;

import java.util.Map;

public class Game {

    private int currentPlayerIndex;
    private IPlayer[] players;

    private IChessboard chessboard;

    // Factories
    private PieceFactory pieceFactory;
    /**
     * Constructor
     * @param pieceFact Fabrique de pièces d'échec
     * @param chessboardFact Fabrique d'échiquier
     */
    public Game(PieceFactory pieceFact, ChessboardFactory chessboardFact, PlayerFactory playerFactory){

        this.pieceFactory = pieceFact;
        this.chessboard = chessboardFact.newChessboard();

        this.players = new IPlayer[]{ playerFactory.newPlayer(0, Color.WHITE), playerFactory.newPlayer(1, Color.BLACK) };
        this.currentPlayerIndex = 0;

    }

    /**
     * Initialise la partie
     */
    public void init(){

        // Kings
        getChessboard().place(this.pieceFactory.newPiece(0, Color.WHITE), new Coord(5,1));
        getChessboard().place(this.pieceFactory.newPiece(0, Color.BLACK), new Coord(5, 8));

        // Queens
        getChessboard().place(this.pieceFactory.newPiece(1, Color.WHITE), new Coord(4,1));
        getChessboard().place(this.pieceFactory.newPiece(1, Color.BLACK), new Coord(4,8));

        // Rooks
        getChessboard().place(this.pieceFactory.newPiece(2, Color.WHITE), new Coord(1,1));
        getChessboard().place(this.pieceFactory.newPiece(2, Color.WHITE), new Coord(8,1));
        getChessboard().place(this.pieceFactory.newPiece(2, Color.BLACK), new Coord(1,8));
        getChessboard().place(this.pieceFactory.newPiece(2, Color.BLACK), new Coord(8,8));

        // Bishops
        getChessboard().place(this.pieceFactory.newPiece(3, Color.WHITE), new Coord(3,1));
        getChessboard().place(this.pieceFactory.newPiece(3, Color.WHITE), new Coord(6,1));
        getChessboard().place(this.pieceFactory.newPiece(3, Color.BLACK), new Coord(3,8));
        getChessboard().place(this.pieceFactory.newPiece(3, Color.BLACK), new Coord(6,8));

    }

    /**
     * Lance la partie
     */
    public void launchGame(){

        // Boucle de jeu principale
        while (canPlay(getCurrentPlayer().getColor())){


            System.out.println("Au tour du joueur " + getCurrentPlayer().getColor());
            getCurrentPlayer().displayBoard(getChessboard());

            boolean validAction = false;

            while (!validAction){
                try {

                    Coord[] parsedAction = parseAction(getCurrentPlayer().play(getChessboard()));
                    getChessboard().move(parsedAction[0], parsedAction[1], getCurrentPlayer().getColor());
                    validAction = true;

                }catch (Exception e){
                    validAction = false;
                }
            }

            nextPlayer();

        }

        if(isCheckMate(getCurrentPlayer().getColor()))
            System.out.println("Les " + getCurrentPlayer().getColor() + " sont MAT");

        if(isPat(getCurrentPlayer().getColor()))
            System.out.println("Les " + getCurrentPlayer().getColor() + " sont PAT");

    }

    private Coord[] parseAction(String playerAction) throws InvalidPlayerInput, WrongCoordinatesFormatException, CoordinatesOutOfBoundsException {

        if (!(playerAction.length() == 4 &&
                Character.isDigit(playerAction.charAt(1)) &&
                Character.isDigit(playerAction.charAt(3)) &&
                Character.isAlphabetic(playerAction.charAt(0)) &&
                Character.isAlphabetic(playerAction.charAt(2))))
            throw new InvalidPlayerInput();

        Coord[] parsedAction = new Coord[2];

        parsedAction[0] = new Coord(playerAction.substring(0, 2));
        parsedAction[1] = new Coord(playerAction.substring(2, 4));

        if(getChessboard().coordinatesOutOfBounds(parsedAction[0]) || getChessboard().coordinatesOutOfBounds(parsedAction[1]))
            throw new CoordinatesOutOfBoundsException();

        return parsedAction;

    }

    /**
     * Retourne le joueur courrant
     */
    public IPlayer getCurrentPlayer(){
        if(currentPlayerIndex >= 2)
            return null;

        return this.players[this.currentPlayerIndex];
    }

    /**
     * Passe au joueur suivant
     */
    private void nextPlayer(){
        this.currentPlayerIndex = (this.currentPlayerIndex + 1)%2;
    }

    /**
     * Vérifie si un joueur d'une couleur peut jouer ou non
     * @param color Couleur du joueur
     * @return True : Le joueur peut jouer, False sinon
     */
    public boolean canPlay(Color color){
        return !(isCheckMate(color) || isPat(color));
    }

    /**
     * Vérifie si un joueur d'une couleur est échec est mat
     * @param color Couleur du joueur
     * @return True : Mat, False sinon
     */
    public boolean isCheckMate(Color color){

        if(canMovePieces(color))
            return false;

        for(Map.Entry<Coord, IPiece> entry : this.getChessboard().getColorPieces(color).entrySet()){
            if(entry.getValue().isCheckable() && entry.getValue().isAttacked(entry.getKey(), this.getChessboard()))
                return true;
        }

        return false;

    }

    /**
     * Vérifie si un joueur d'une couleur est pat
     * @param color Couleur du joueur
     * @return True : Pat, False sinon
     */
    public boolean isPat(Color color){

        if(canMovePieces(color))
            return false;

        for(Map.Entry<Coord, IPiece> entry : this.getChessboard().getColorPieces(color).entrySet()){
            if(entry.getValue().isCheckable() && entry.getValue().isAttacked(entry.getKey(), this.getChessboard()))
                return false;
        }

        return true;

    }

    /**
     * Vérifie si une couleur peut bouger des pièces
     */
    public boolean canMovePieces(Color color){

        for(Map.Entry<Coord, IPiece> entry : this.getChessboard().getColorPieces(color).entrySet()){

            if(!entry.getValue().getLegalMoves(entry.getKey(), this.getChessboard()).isEmpty())
                return true;

        }

        return false;

    }

    /**
     * Retourne l'échiquier
     * @return Échiquier
     */
    public IChessboard getChessboard(){
        return this.chessboard;
    }

    /**
     * Renvoie la couleur de l'opposant
     * @param c Couleur du joueur
     * @return Couleur de l'opposant
     */
    public static Color getOpponentColor(Color c){
        return c.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

}
