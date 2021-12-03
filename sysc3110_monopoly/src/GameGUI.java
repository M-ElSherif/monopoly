import Models.Player;
import Models.Property;

import java.util.Scanner;

/**
 * This class is designed to test functionality, based on user selections
 */
public class GameGUI {

    private GamePlay gamePlay;

    private final int ROLLDICE = 1;
    private final int PASSTURN = 2;
    private final int QUITGAME = 3;
    private final int PRINTSTATUS = 4;
    private final int BUYPROPERTY = 5;
    private final int ENDGAME = 6;
    private final int BUYHOUSE = 7;
    private final int BUYHOTEL = 8;
    private final int PAYJAILFINE = 9;
    private final int EXITAPP = 111;

    public GameGUI(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public boolean AddPlayers(Scanner scanner) {
        System.out.println("1: Add a player");
        System.out.println("2: FINISH");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.println("Provide the player's name: ");
            String name = scanner.nextLine();
            Player player = new Player(name);
            this.gamePlay.getGame().addPlayer(player);

            return true;
        }

        return false;
    }

//    public int FirstMenuOptions(Scanner scanner) {
//        System.out.println("\n" + ROLLDICE + ": Roll the dice");
//        System.out.println(PASSTURN + ": Pass your turn");
//        System.out.println(QUITGAME + ": Quit the game");
//        System.out.println(PRINTSTATUS + ": Print player status");
//        System.out.println(ENDGAME + ": End the game (if all players agree)");
//
//        System.out.print("Your choice: ");
//        int choice = Integer.parseInt(scanner.nextLine());
//
//        return choice;
//    }

    public int SecondMenuOptions(Scanner scanner) {
        System.out.println("\n" + ROLLDICE + ": Roll the dice");
        System.out.println(PASSTURN + ": Pass your turn");
        System.out.println(QUITGAME + ": Quit the game");
        System.out.println(PRINTSTATUS + ": Print player status");
        System.out.println(BUYPROPERTY + ": Buy property");
        System.out.println(BUYHOUSE + ": Buy house");
        System.out.println(BUYHOTEL + ": Buy hotel");
        System.out.println(ENDGAME + ": End the game (if all players agree)");

        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        return choice;
    }

    public int JailMenuOptions(Scanner scanner) {
        System.out.println("\n" + ROLLDICE + ": Roll the dice");
        System.out.println(PAYJAILFINE + ": Pay the jail fine ($50)");
        System.out.println(PASSTURN + ": Pass your turn");
        System.out.println(QUITGAME + ": Quit the game");
        System.out.println(PRINTSTATUS + ": Print player status");
        System.out.println(ENDGAME + ": End the game (if all players agree)");

        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        return choice;
    }

    public boolean OptionTasks(int choice) {

        boolean bool = true;
        Player cp = this.gamePlay.getGame().getCurrentPlayer();

        switch (choice) {
            case ROLLDICE:
                int diceRoll = this.gamePlay.rollDice();

                if (cp.isInJail()) {
                    if (this.gamePlay.isDoubleRollForJailExit(cp)) {
                        this.gamePlay.movePlayerPosition(cp, diceRoll);
                        this.gamePlay.checkPositionEvents(cp);
                    } else {
                        break;
                    }
                }
                this.gamePlay.movePlayerPosition(cp, diceRoll);
                this.gamePlay.checkPositionEvents(cp);
                break;

            case PASSTURN:
                this.gamePlay.passTurn(cp);
                break;

            case QUITGAME:
                this.gamePlay.quitGame(cp);
                bool = false;
                break;

            case PRINTSTATUS:
                System.out.println(this.gamePlay.printPlayerStatus(cp) + "\n");
                break;

            case ENDGAME:
                bool = false;
                break;

            case BUYPROPERTY:
                Property property = this.gamePlay.getGame().getProperty(cp.getPosition());
                this.gamePlay.buyProperty(property);
                break;

            case BUYHOUSE:
//                Property property = this.gamePlay.getGame().getProperty(cp.getPosition());
//                this.gamePlay.buyProperty(property);
                break;

            case BUYHOTEL:
//                Property property = this.gamePlay.getGame().getProperty(cp.getPosition());
//                this.gamePlay.buyProperty(property);
                break;

            case PAYJAILFINE:
                this.gamePlay.payJailFine(cp);
                break;

            case EXITAPP:
                System.exit(0);
                break;
        }

        return bool;
    }


    /**
     * This is ust to test functionalilty
     */
    public void RunGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!this.AddPlayers(scanner)) {
                break;
            }
        }

        // Set the first player as the current player at game start
        this.gamePlay.getGame().setCurrentPlayer(gamePlay.getGame().getPlayerList().get(0));

        while (true) {

            Player cp = this.gamePlay.getGame().getCurrentPlayer();

            if (cp.isInJail()) {
                while (cp.isInJail()) {
                    int choice = JailMenuOptions(scanner);
                    if (!OptionTasks(choice)) {
                        break;
                    }
                }
            }


            int choice = SecondMenuOptions(scanner);
            if (!OptionTasks(choice)) {
                break;
            }

        }


    }


}
