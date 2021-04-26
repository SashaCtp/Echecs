package game.interfaces;

import game.Color;
import game.Coord;

public interface IPiece {

    /**
     * Vérifie si une pièce peut se déplacer à certaines coordonnées
     * - Vérifie si le pattern de déplacement est correct
     * - Vérifie si une pièce alliée ne gêne pas le déplacement
     * @param from Coordonnées de départ
     * @param to Coordonnées de destination
     * @param board Échiquier sur lequel se déplace la pièce
     */
    boolean canMove(Coord from, Coord to, Chessboard board);

    /**
     * Vérifie si la pièce est attaquée
     * @param chessboard Échiquier sur lequel se trouve la pièce
     * @return True: La pièce est attaquée par une ou plusieurs pièces, False sinon
     */
    boolean isAttacked(Chessboard chessboard);

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
     * Retourne le symbole de la pièce
     * @return Symbole de la pièce
     */
    char getSymbole();

}
