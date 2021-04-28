package appli;

import game.Game;

public class Appli {

    public static void main(String[] args){

        Game game = new Game(new PieceFact(), new ChessboardFact(), new PlayerFact());
        game.init();
        game.launchGame();

    }

}
