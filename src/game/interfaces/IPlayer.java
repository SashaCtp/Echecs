package game.interfaces;

import game.Color;

public interface IPlayer {

    /**
     * Demande l'action voulu au joueur
     * @return Action
     */
    String play();

    /**
     * Vérifie si un joueur peut ou non jouer
     * @param board Plateau de jeu
     * @return True : Le joueur peut jouer, False sinon
     */
    boolean canPlay(IChessboard board);

    /**
     * Retourne la couleur du joueur
     * @return couleur du joueur
     */
    Color getColor();

    /**
     * Affiche le plateau de jeu au joueur
     * @param board Plateau de jeu
     */
    void displayBoard(IChessboard board);

    /**
     * Vérifie si un joueur est échec et mat
     * @param board Plateau de jeu
     * @return True : Le jouer est échec est mat, False sinon
     */
    boolean isCheckMate(IChessboard board);

    /**
     * Vérifie si un jouer est dans la situation de Pat
     * @param board Plateau de jeu
     * @return True : Il y a PAT, False sinon
     */
    boolean isPat(IChessboard board);

}