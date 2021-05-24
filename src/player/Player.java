package player;

import game.Color;
import game.interfaces.IPlayer;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public abstract class Player implements IPlayer {

    private Color color;

    public Player(Color color){
        this.color = color;
    }

    public boolean correctInput(String input){

        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("/nulle");
        cmds.add("/oui");
        cmds.add("/non");
        cmds.add("/abandon");

        return (cmds.contains(input.toLowerCase()) ||
                (input.length() == 4 &&
                Character.isDigit(input.charAt(1)) &&
                Character.isDigit(input.charAt(3)) &&
                Character.isAlphabetic(input.charAt(0)) &&
                Character.isAlphabetic(input.charAt(2)) )
        );

    }

    public Color getColor(){
        return this.color;
    }

}
