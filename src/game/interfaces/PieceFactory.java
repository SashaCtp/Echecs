package game.interfaces;

import game.Color;

public interface PieceFactory {

    /**
     * Instancie une nouvelle pièce en fonction du type et de la couleur
     * @param type Type de la pièce : 0=Roi, 1=Reine, 2=Tour
     * @param color Couleur de la pièce
     * @return Instance de la nouvelle pièce, NULL si mauvais type/couleur renseigné
     */
    IPiece newPiece(int type, Color color);

}
