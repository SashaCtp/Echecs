package game.interfaces;

import game.Color;

public interface IPlayer {

    // Les classes héritant de IPlayer servent uniquement à gérer l'interface entre Homme/Programme, IA ou par réseau

    /**
     * Demande l'action voulu au joueur
     * @return Action
     */
    String play();

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
}