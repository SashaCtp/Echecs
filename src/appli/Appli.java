package appli;

import game.Game;
import java.util.Scanner;

public class Appli {

    public static void main(String[] args){

        // Affichage du menu
        System.out.println("== Jeu d'échecs | Menu ==");
        System.out.println("> 1 - Joueur VS Joueur");
        System.out.println("> 2 - Joueur VS IA");
        System.out.println("> 3 - IA VS IA");
        System.out.println("\n Commandes utiles (sauf cas 3) :");
        System.out.println(" - Abandon : /abandon");
        System.out.println(" - Nulle : /nulle, l'adversaire répond alors par /oui s'il veut confirmer, /non sinon");
        System.out.println("");

        Game game = new Game(getChoice(), new PieceFact(), new ChessboardFact(), new PlayerFact());
        game.init();
        game.launchGame();

    }

    /**
     * Retourne le choix de l'utilisateur
     * @return Choix
     */
    private static int getChoice(){
        int choice = 0;

        Scanner sc = new Scanner(System.in);
        while(choice <= 0 || choice > 3){

            System.out.print("Type de partie : ");
            String inp = sc.nextLine();

            try{
                choice = Integer.parseInt(inp);
            }catch (Exception e){
                System.out.println("/!\\ Veuillez indiquer un numéro valide /!\\");
            }

        }
        System.out.println("Choix = " + choice);
        return choice;
    }

}
