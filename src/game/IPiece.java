package game;

public interface IPiece {

    /**
     * Vérifie si une pièce peut se déplacer à certaines coordonnées
     * @param from Coordonnées de départ
     * @param to Coordonnées de destination
     */
    boolean canMove(Coord from, Coord to);

    /**
     * Vérifie si la pièce est attaquée
     * @param chessboard Echiquier
     * @return True: La pièce est attaquée par une ou plusieurs pièces, False sinon
     */
    boolean isAttacked(Chessboard chessboard);

    IPiece[] getAttackers(Chessboard chessboard);

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
