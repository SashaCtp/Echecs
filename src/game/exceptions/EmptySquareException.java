package game.exceptions;

public class EmptySquareException extends Exception{

    public EmptySquareException(){
        super("Aucune pièce présente aux coordonnées donnés");
    }

}
