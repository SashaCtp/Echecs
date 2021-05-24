package game;

import game.exceptions.CoordinatesOutOfBoundsException;
import game.exceptions.InvalidPlayerInput;
import game.exceptions.WrongCoordinatesFormatException;
import game.interfaces.*;

import java.util.Map;

public class Game {

    private int currentPlayerIndex;
    private IPlayer[] players;

    // Lorsque les joueurs s'accordent sur une partie nulle, cette variable passe à true
    private boolean nullGame = false;
    private boolean abandon = false;

    private IChessboard chessboard;

    // Factories
    private PieceFactory pieceFactory;
    /**
     * Constructor
     * @param type 1 : PvP, PvE, EvE
     * @param pieceFact Fabrique de pièces d'échec
     * @param chessboardFact Fabrique d'échiquier
     */
    public Game(int type, PieceFactory pieceFact, ChessboardFactory chessboardFact, PlayerFactory playerFactory){

        this.pieceFactory = pieceFact;
        this.chessboard = chessboardFact.newChessboard();

        IPlayer white;
        IPlayer black;
        switch (type){
            case 1:
                white = playerFactory.newPlayer(0, Color.WHITE);
                black = playerFactory.newPlayer(0, Color.BLACK);
                break;
            case 2:
                white = playerFactory.newPlayer(0, Color.WHITE);
                black = playerFactory.newPlayer(1, Color.BLACK);
                break;
            case 3:
                white = playerFactory.newPlayer(1, Color.WHITE);
                black = playerFactory.newPlayer(1, Color.BLACK);
                break;
            default:
                white = null;
                black = null;
                break;
        }

        this.players = new IPlayer[]{ white, black };
        this.currentPlayerIndex = 0;

    }

    /**
     * Par défaut, le type de partie = 1 (Player vs Player)
     * @param pieceFact Fabrique de pièces d'échec
     * @param chessboardFact Fabrique d'échiquier
     */
    public Game(PieceFactory pieceFact, ChessboardFactory chessboardFact, PlayerFactory playerFactory){
        this(1, pieceFact, chessboardFact, playerFactory);
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

        // Knights
        getChessboard().place(this.pieceFactory.newPiece(4, Color.WHITE), new Coord(2,1));
        getChessboard().place(this.pieceFactory.newPiece(4, Color.WHITE), new Coord(7,1));
        getChessboard().place(this.pieceFactory.newPiece(4, Color.BLACK), new Coord(2,8));
        getChessboard().place(this.pieceFactory.newPiece(4, Color.BLACK), new Coord(7,8));

    }

    /**
     * Lance la partie
     */
    public void launchGame(){

        // Boucle de jeu principale
        while (canPlay(getCurrentPlayer().getColor()) && !nullGame && !abandon){

            System.out.println("\n== Au tour du joueur " + getCurrentPlayer().getColor() + " ==");
            getCurrentPlayer().displayBoard(getChessboard());

            boolean validAction = false;

            while (!validAction){
                try {

                    String action = getCurrentPlayer().play(getChessboard());

                    if(action.equals("/nulle")){ // Le joueur propose la nulle
                        if(getOpponent().acceptNull()){
                            validAction = true;
                            nullGame = true;
                            System.out.println("✅ Votre adversaire a accepté la nulle ! ✅");
                        }else{
                            System.out.println("❌ Votre adversaire a refusé la nulle ! ❌");
                            System.out.println("La partie reprend !");
                        }
                    }else if(action.equals("/abandon")) {
                        abandon = true;
                        validAction = true;
                    }else{
                        Coord[] parsedAction = parseAction(action);
                        getChessboard().move(parsedAction[0], parsedAction[1], getCurrentPlayer().getColor());
                        validAction = true;
                    }

                }catch (Exception e){
                    validAction = false;
                }
            }

            nextPlayer();

        }

        displayEndGame();

    }

    private void displayEndGame(){
        if(nullGame)
            System.out.println("Partie nulle !");
        else if(abandon) // Lorsque l'abandon est enregistré, on passe au joueur suivant, le joueur courant est donc le gagnat
            System.out.println("Les " + getCurrentPlayer().getColor() + " gagnent par abandon !");
        else if(isCheckMate(getCurrentPlayer().getColor()))
            System.out.println("Les " + getCurrentPlayer().getColor() + " sont MAT !");
        else if(isPat(getCurrentPlayer().getColor()))
            System.out.println("Les " + getCurrentPlayer().getColor() + " sont PAT !");

        System.out.println("\nEtat final de l'échiquier : ");
        getCurrentPlayer().displayBoard(getChessboard());
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
     * Renvoie le joueur opposé au joueur courant
     */
    public IPlayer getOpponent(){
        return players[(currentPlayerIndex+1)%2];
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
