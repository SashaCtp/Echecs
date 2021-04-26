package game.interfaces;

import game.Color;
import game.Coord;
import game.exceptions.NoPieceException;

import java.util.ArrayList;

public interface Chessboard {

    /**
     * Vérifie si une case est vide ou non
     * @param coo Coordonnées de la case
     * @return True : Il y a une pièce sur la case, False sinon
     */
    boolean isFree(Coord coo);

    /**
     * Permet de récupérer la pièce présente sur une case
     * @param coo Coordonnées de la case
     * @return Pièce présente sur la case / NULL s'il n'y a pas de pièce
     */
    IPiece getPieceAt(Coord coo);

    /**
     * Déplace une pièce d'une case à une autre
     * @param from Case de départ
     * @param to Case d'arrivée
     * @param player Joueur effectuant l'action
     */
    void move(Coord from, Coord to, IPlayer player) throws NoPieceException;

    /**
     * Retourne la liste des pièces d'une couleur
     * @param c Couleur des pièces
     * @return Liste des pièces d'une couleur
     */
    ArrayList<IPiece> getColorPieces(Color c);

    /**
     * Retourne les coordonnées d'une pièce
     * @param piece Pièce dont on veut connaître les coordonnées
     * @return Coordonnées de la pièce si elle existe, null sinon
     */
    Coord getCoordinates(IPiece piece);

    /**
     * Place une pièce sur l'échiquier
     * @param piece Pièce à placer
     * @param coo Coordonnées où placer la pièce
     */
    void place(IPiece piece, Coord coo);

    /**
     * Retire la pièce de l'échiquier
     * @param piece Pièce à retirer
     */
    void removePiece(IPiece piece);

    /**
     * Retire la pièce présente à certaines coordonnées
     * @param coo Coordonnées
     */
    void removePieceAt(Coord coo);

}
