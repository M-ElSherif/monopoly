import Models.Player;
import Models.Property;

import java.util.Scanner;

public class GameGUI {

    private GamePlay gamePlay;

    private final int ROLLDICE = 1;
    private final int PASSTURN = 2;
    private final int QUITGAME = 3;
    private final int PRINTSTATUS = 4;
    private final int BUYPROPERTY = 5;
    private final int ENDGAME = 6;

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

    public int FirstMenuOptions(Scanner scanner) {
        System.out.println("\n" + ROLLDICE + ": Roll the dice");
        System.out.println(PASSTURN + ": Pass your turn");
        System.out.println(QUITGAME + ": Quit the game");
        System.out.println(PRINTSTATUS + ": Print player status");
        System.out.println(ENDGAME + ": End the game (if all players agree)");

        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        return choice;
    }

    public int SecondMenuOptions(Scanner scanner) {
        System.out.println(PASSTURN + ": Pass your turn");
        System.out.println(QUITGAME + ": Quit the game");
        System.out.println(PRINTSTATUS + ": Print player status");
        System.out.println(BUYPROPERTY + ": Buy property");
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
                this.gamePlay.movePlayerPosition(cp, diceRoll);
                this.gamePlay.checkPositionEvent(cp);
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
            case 9:
                System.exit(0);
                break;
        }

        return bool;
    }


    public void RunGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!this.AddPlayers(scanner)) {
                break;
            }
        }

        // TODO fix this
        this.gamePlay.getGame().setCurrentPlayer(gamePlay.getGame().getPlayerList().get(0));

        while (true) {
            int choice = FirstMenuOptions(scanner);
            if (!OptionTasks(choice)) {
                break;
            }

            int choice2 = SecondMenuOptions(scanner);
            if (!OptionTasks(choice2)) {
                break;
            }
        }


    }


}
