package appli;

import game.Color;
import game.interfaces.IPlayer;
import game.interfaces.PlayerFactory;
import player.HumanPlayer;

public class PlayerFact implements PlayerFactory {

    @Override
    public IPlayer newPlayer(int type, Color color) {

        return new HumanPlayer(color);

    }
}
