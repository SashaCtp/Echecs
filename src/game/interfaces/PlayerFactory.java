package game.interfaces;

import game.Color;

public interface PlayerFactory {

    /**
     * Créé un nouveau joueur
     * @param type Type du joueur (0 : Joueur normal, 1 : IA)
     * @return Joueur / null si type invalide
     */
    IPlayer newPlayer(int type, Color color);

}
