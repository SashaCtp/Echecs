package game.interfaces;

import game.Color;

public interface IPlayer {

    // Les classes héritant de IPlayer servent uniquement à gérer l'interface entre Homme/Programme, IA ou par réseau

    /**
     * Demande l'action voulu au joueur
     * @param chessboard Échiquier actuel
     * @return Action
     */
    String play(IChessboard chessboard);

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