package game;

import game.exceptions.WrongCoordinatesFormatException;

public class Coord{

    private int column,row;

    private final int ASCII_SUB = 96;

    public Coord(){
        this.column = 0;
        this.row = 0;
    }

    public Coord(int column, int row){
        this.column = column;
        this.row = row;
    }

    public Coord(String str) throws WrongCoordinatesFormatException{

        if(!correctFormat(str))
            throw new WrongCoordinatesFormatException();

        this.column = str.charAt(0) - ASCII_SUB;
        this.row = Integer.parseInt("" + str.charAt(1));

    }

    private boolean correctFormat(String str) {
        return str.length() == 2 && Character.isAlphabetic(str.charAt(0)) && Character.isDigit(str.charAt(1));
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean equals(Object o){

        if(o == null)
            return false;
        if(this == o)
            return true;
        if(!(o instanceof Coord))
            return false;

        Coord c = (Coord) o;

        return c.getColumn() == this.getColumn() && c.getRow() == this.getRow();


    }

    @Override
    public int hashCode(){

        return this.getColumn() * 100 + this.getRow();

    }

    @Override
    public String toString(){

        return Character.toString((char) (this.getColumn() + ASCII_SUB)) + this.getRow();

    }
}

