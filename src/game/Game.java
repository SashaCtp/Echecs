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

    public static Color getOpponentColor(Color c){
        return c.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

}
