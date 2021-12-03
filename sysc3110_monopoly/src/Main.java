import Models.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//        // TESTING house hotel purchase
//        final String RED = "Red";
//        final String BLUE = "Blue";
//        final String INDIGO = "Indigo";
//        final String GREEN = "Green";
//        final String YELLOW = "Yellow";
//        final String PINK = "Pink";
//        final String ORANGE = "Orange";
//        final String BROWN = "Brown";
//
//        Player p1 = new Player("p1");
//        Player p2 = new Player("p2");
//
//        Game game = new Game();
//        game.setPlayerList(new ArrayList<Player>(Arrays.asList(p1, p2)));
//
//        Property pr1 = game.getProperty(39);
//        Property pr2 = game.getProperty(37);
//        Property pr3 = game.getProperty(34);
//        Property pr4 = game.getProperty(32);
//        Property pr5 = game.getProperty(31);
//
//        pr1.setOwner(p1);
//        pr2.setOwner(p1);
//        pr3.setOwner(p2);
//        pr4.setOwner(p2);
//        pr5.setOwner(p2);
//
//        System.out.println(game.printMonopolizedProperties(p1));
//        System.out.println(game.printMonopolizedProperties(p2));
//
//        System.out.println(game.checkIfPropertyInMonopoly(p1, pr1));
//        System.out.println(game.checkIfPropertyInMonopoly(p2, pr3));
//
//        GamePlay gamePlay = new GamePlay(game);
//        gamePlay.buyHouse(p1, pr1);
//        gamePlay.buyHotel(p1, pr1);
//
//        System.out.println(pr1);

        // TESTING overall gameplay commands
        Game game = new Game();
        game.setPlayerList(new ArrayList<Player>(Arrays.asList(new Player("p1"),
                new Player("p2"),
                new Player("p3"))));

        System.out.println(String.format("Players starting the game:\n %s\n\r%s\n\r%s",
                game.getPlayerList().get(0),
                game.getPlayerList().get(1),
                game.getPlayerList().get(2)));

        GamePlay gamePlay = new GamePlay(game);

        GameGUI gameGUI = new GameGUI(gamePlay);
        gameGUI.RunGame();

    }
}
