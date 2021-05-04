package player;

import game.Color;
import game.Coord;
import game.interfaces.IChessboard;

import java.util.ArrayList;
import java.util.Random;

public class SimpleAI extends Player{

    public SimpleAI(Color color) {
        super(color);
    }

    /**
     * Joue un coup aléatoire parmi la liste de coups légaux
     * @param chessboard Échiquier actuel
     * @return Action de l'IA
     */
    @Override
    public String play(IChessboard chessboard) {

        Random r = new Random();
        Coord from = (Coord) chessboard.getColorPieces(getColor()).keySet().toArray()[r.nextInt(chessboard.getColorPieces(getColor()).keySet().size())];
        ArrayList<Coord> legalMoves = chessboard.getPieceAt(from).getLegalMoves(from, chessboard);

        return from.toString() + legalMoves.get(r.nextInt(legalMoves.size())).toString();
    }

    @Override
    public void displayBoard(IChessboard board) {
        System.out.println(board);
    }
}
