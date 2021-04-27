package game.exceptions;

public class CoordinatesOutOfBoundsException extends Exception{

    public CoordinatesOutOfBoundsException(){
        super("Les coordonnées ne sont pas valides.");
    }

}
