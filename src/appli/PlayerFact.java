package appli;

import game.Color;
import game.interfaces.IPlayer;
import game.interfaces.PlayerFactory;
import player.HumanPlayer;
import player.SimpleAI;

public class PlayerFact implements PlayerFactory {

    @Override
    public IPlayer newPlayer(int type, Color color) {

        switch (type){
            case 0:
                return new HumanPlayer(color);
            case 1:
                return new SimpleAI(color);
            default:
                return null;
        }


    }
}
