package game.interfaces;

import game.Color;
import game.Coord;

import java.util.ArrayList;

public interface IPiece {

    /**
     * Vérifie si le déplacement est bloqué par une pièce
     * (le déplacement doit respecter le pattern && être sur le plateau)
     * - De même couleur sur tout le déplacement (case destination inclus)
     * - De couleur différente, sur le déplacement, sauf case destination
     * @return True : Une pièce gêne le déplacement, False sinon
     */
    boolean isBlocked(Coord from, Coord to, IChessboard board);

    /**
     * Vérifie si une pièce peut atteindre une case de l'échiquier (sans prendre en compte la possibilité d'échec)
     * C.à.d si le déplacement correspond au pattern et qu'il n'y a pas d'obstacle sur le chemin
     * @param from Coordonnées de départ
     * @param to Coordonnées d'arrivée
     * @param board Échiquier
     * @return True : La pièce peut l'atteindre, False sinon
     */
    boolean canReach(Coord from, Coord to, IChessboard board);

    /**
     * Vérifie si une pièce peut se déplacer à certaines coordonnées
     * - Vérifie si le pattern de déplacement est correct
     * - Vérifie si une pièce alliée ne gêne pas le déplacement
     * - Vérifie que le déplacement ne met pas le joueur en échec
     * @param from Coordonnées de départ
     * @param to Coordonnées de destination
     * @param board Échiquier sur lequel se déplace la pièce
     */
    boolean canMove(Coord from, Coord to, IChessboard board);

    /**
     * Vérifie si la pièce est attaquée
     * @param coord Coordonnées auxquelles se trouve la pièce
     * @param chessboard Échiquier sur lequel se trouve la pièce
     * @return True: La pièce est attaquée par une ou plusieurs pièces, False sinon
     */
    boolean isAttacked(Coord coord, IChessboard chessboard);

    /**
     * Retourne une liste de déplacement légaux à partir d'une certaine position
     * @param from Position de départ
     * @param chessboard Echiquier sur lequel la pièce se déplace
     * @return Liste des déplacement légaux
     */
    ArrayList<Coord> getLegalMoves(Coord from, IChessboard chessboard);

    /**
     * Vérifie si la pièce peut être mise en échec
     * @return True si la pièce peut être mise en échec, False sinon (les pièces normales)
     */
    boolean isCheckable();

    /**
     * Retourne la couleur de la pièce
     * @return Couleur de la pièce
     */
    Color getColor();

    /**
     * Retourne le symbole de la pièce (Blanc : Majuscule, Noir : Minuscule);
     * @return Symbole de la pièce
     */
    char getSymbole();
}
