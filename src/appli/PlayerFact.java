package appli;

import game.interfaces.IPlayer;
import game.interfaces.PlayerFactory;

public class PlayerFact implements PlayerFactory {

    @Override
    public IPlayer newPlayer(int type) {
        return null;
    }
}
