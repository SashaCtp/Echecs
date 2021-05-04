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

        while (!correctInput(inp) || inp.equalsIgnoreCase("QUIT") || inp.equalsIgnoreCase("END")){

            Scanner sc = new Scanner(System.in);

            System.out.print("> ");
            inp = sc.nextLine();

        }

        return inp;
    }

    @Override
    public void displayBoard(IChessboard board) {
        System.out.println(board);
    }
}
