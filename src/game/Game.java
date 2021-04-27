package game;

import game.interfaces.*;

public class Game {

    private int currentPlayerIndex;
    private IPlayer[] players;

    private IChessboard chessboard;

    // Factories
    private PieceFactory pieceFactory;
    private PlayerFactory playerFactory;
    /**
     * Constructor
     * @param pieceFact Fabrique de pièces d'échec
     * @param playerFact Fabrique de joueur
     * @param chessboardFact Fabrique d'échiquier
     */
    public Game(PieceFactory pieceFact, PlayerFactory playerFact, ChessboardFactory chessboardFact){

        this.pieceFactory = pieceFact;
        this.playerFactory = playerFact;
        this.chessboard = chessboardFact.newChessboard();

    }

    /**
     * Initialise la partie
     */
    public void init(){

        // Kings
        this.chessboard.place(this.pieceFactory.newPiece(0, Color.WHITE), new Coord(5,1));
        this.chessboard.place(this.pieceFactory.newPiece(0, Color.BLACK), new Coord(5, 8));

        // Rooks
        this.chessboard.place(this.pieceFactory.newPiece(1, Color.WHITE), new Coord(1,1));
        this.chessboard.place(this.pieceFactory.newPiece(1, Color.WHITE), new Coord(8,1));
        this.chessboard.place(this.pieceFactory.newPiece(1, Color.BLACK), new Coord(1,8));
        this.chessboard.place(this.pieceFactory.newPiece(1, Color.BLACK), new Coord(8,8));

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
