package player;

import game.Color;
import game.interfaces.IChessboard;

import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(Color color){
        super(color);
    }

    @Override
    public String play(IChessboard chessboard) {

        String inp = "";
        Scanner sc = new Scanner(System.in);

        while (!correctInput(inp)){

            System.out.print("> ");
            inp = sc.nextLine();

        }

        return inp;
    }

    @Override
    public void displayBoard(IChessboard board) {
        System.out.println(board);
    }

    @Override
    public boolean acceptNull() {

        System.out.println("== Votre adversaire propose la nulle, acceptez-vous ? ==");
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println(" \"/oui\" pour accepter");
            System.out.println(" \"/non\" pour refuser");

            String str = sc.nextLine();
            if(str.equals("/oui")) {
                return true;
            }else if(str.equals("/non")) {
                return false;
            }
        }

    }
}
