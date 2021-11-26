import Helpers.ErrorLog;
import Helpers.EventHandler;
import Models.Board;
import Models.Dice;
import Models.Player;
import Models.Property;

import java.util.*;

/**
 * Author: Dana El Sherif and Chia-Yu Liu
 * <p>
 * Reviewer: Kareem El Assad
 * <p>
 * Editor: Keefer Belanger
 * <p>
 * Date: 2021-10-18
 */
public class Play {

    private ArrayList<Player> playerList;
    private Player currentPlayer;
    private Dice dice;
    private int turnCount;
    private Board board;

    /**
     * Constructor for the Play class. It initializes players as a new arraylist and
     * properties as a board
     */
    public Play() {
        playerList = new ArrayList<>();
        currentPlayer = null;
        dice = new Dice();
        turnCount = 0;
        board = new Board();
    }

    // Temp methods to allow for testing backend
    public ArrayList<Player> TempSetPlayers() {
        this.playerList.addAll(Arrays.asList(new Player("p1"), new Player("p2"), new Player("p3")));
        return this.playerList;
    }

    public void StartGame() {
        TempSetPlayers();
        System.out.println(String.format("Players starting the game: %s\n\r%s\n\r%s",
                this.playerList.get(0).getName(),
                this.playerList.get(1),
                this.playerList.get(2)));
    }

    public int TopMenuOptions(Scanner scanner) {
        System.out.println("1. Roll the dice");
        System.out.println("2. Pass your turn");
        System.out.println("3. Quit the game");
        System.out.println("4. Print player status");
        System.out.println("5. Buy property");
        System.out.println("9. End the game (if all players agree)");

        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        return choice;
    }

    public boolean OptionTasks(int choice) {
        switch (choice) {
            case 1:
                this.movePlayerPosition(this.currentPlayer, this.rollDice()); // todo finish
                break;
            case 2:
                this.passTurn(this.currentPlayer);
                break;
            case 3:
                this.quitGame(this.currentPlayer);
                break;
            case 4:
                this.printPlayerStatus(this.currentPlayer);
                break;
            case 5:
                this.printPlayerStatus(this.currentPlayer);
                break;
            case 9:
                System.exit(0);
                break;
        }

        return true;
    }

    private int rollDice() {
        int rollNumber = dice.rollDie();
        return dice.getDiceRoll();
    }

    private int movePlayerPosition(Player player, int diceRoll) {
        int newPosition = player.getPosition() + diceRoll;
        int maxPos = board.getMaxPosition();
        if (newPosition > maxPos)
            player.setPosition(newPosition - maxPos);
        else {
            player.setPosition(newPosition);
        }

        return player.getPosition();
    }

    private String printPlayerStatus(Player player) {
        return player.toString();
    }

    private Player passTurn(Player player) {
        int i = this.playerList.indexOf(player);
        if (i == this.playerList.size() - 1) {
            this.currentPlayer = this.playerList.get(0);
        } else {
            this.currentPlayer = this.playerList.get(i + 1);
        }

        this.incrementTurnCount();
        return this.currentPlayer;
    }

    private boolean buyProperty() {
        Player cp = this.currentPlayer;
        int currentPos = cp.getPosition();
        Property propertyToBuy = this.board.getProperty(currentPos);


        if (propertyToBuy.getOwner() == null) {
            int newCurrentPlayerWealth = (int) (cp.getWealth() - propertyToBuy.getCost());

            if (newCurrentPlayerWealth < 0) {
                EventHandler.failPurchaseEvent(propertyToBuy, cp);
                return false;
            } else {
                this.currentPlayer.setWealth(newCurrentPlayerWealth);
                propertyToBuy.setOwner(cp);
                this.board.setProperty(currentPos, propertyToBuy);
                EventHandler.successPurchaseEvent(propertyToBuy, cp);
                return true;
            }
        }
        return false;
    }

    private boolean payRent(Player player) {
        int position = player.getPosition();
        Property property = board.getProperty(position);
        Player owner = property.getOwner();

        if (owner != null) {
            double rentCost = property.getRentCost();
            player.setWealth(player.getWealth() - rentCost);
            owner.setWealth(owner.getWealth() + rentCost);

            if (player.isBankrupt()) {
                this.board.removeOwner(player);
                this.bankruptRemoval(player);
                EventHandler.bankruptcyAlertEvent(player);
            }
        }

        return true;
    }

    /**
     * Checks if the player is bankrupt to then remove them from the game
     *
     * @param player as a Models.Player object
     * @return true if player has less than or equal to zero money
     */
    private boolean bankruptRemoval(Player player) {
        return this.playerList.remove(player);
    }

    private boolean quitGame(Player player) {
        return this.playerList.remove(player);
    }


    private void incrementTurnCount() {
        this.turnCount++;
    }

    /**
     * Gets the current player
     *
     * @return Models.Player
     */
    private Player getCurrentPlayer() {
        return currentPlayer;
    }


    public boolean checkDoubles(Player p) {
        if (dice.rolledDouble() == true) {
            return true;
        }
        return false;
    }

//    /**
//     * Cycles through the players turns
//     *
//     * @return Models.Player
//     */
//    public Player changeTurn() {
//        if (count == turn.size() - 1) {
//            count = 0;
//            currentPlayer = turn.get(0);
//            System.out.println(currentPlayer.getName() + "'s turn to roll dice!");
//        } else if (currentPlayer == turn.get(this.count)) {
//            currentPlayer = turn.get(this.count + 1);
//            count++;
//            System.out.println(currentPlayer.getName() + "'s turn to roll dice!");
//        }
//        return currentPlayer;
//    }

//    /**
//     * Runs as everything a player can do in their turn
//     */
//    public void playerPosition(Player p) {
//
////        String path = "Models.Board.json";
////
////        try {
////            String content = new String(Files.readAllBytes(Paths.get(path)));
////            JSONObject jsonObject = new JSONObject(content);
////            JSONArray tiles = jsonObject.getJSONArray("tiles");
////
////            BufferedReader br = new BufferedReader(new FileReader("Game.txt"));
////            String brInfo = br.readLine();
////
////            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Game.txt")));
////
////            while (brInfo != null) {
////                for (int i = 0; i < tiles.length(); i++) {
////                    JSONObject obj = (JSONObject) tiles.get(i);
////
////                    Integer id = (Integer) obj.get("id");
////                    String type = (String) obj.get("type");
////                    String color = (String) obj.get("color");
////
////                    //Check if space is special
////                    if (type == "special") {
////                        System.out.println("You landed on an empty tile! Next Models.Player's turn!");
////                    }
////
////                    //Check if space is a tax space
////                    else if (type == "tax") {
////                        p.setMoney((p.getMoney() - 200));
////                        System.out.println("You landed on Tax!");
////                        System.out.println("$200 has been deducted from you!");
////                        isBankrupt(p);
////                    }
////
////                    //Check if space is a property space
////                    else if (type == "property") {
////                        if (brInfo.contains("owner")) {
////                            if (color == "brown"){
////
////                            }
////                        }
////                        else{
////                            String propertyName = properties.get(p.getPosition()).getPropertyName();
////                            System.out.println("Would you like to buy " + propertyName + " that's "
////                                    + properties.get(p.getPosition()).getPropertyColor() + " for "
////                                    + properties.get(p.getPosition()).getPropertyCost() + "?");
////                        }
////                    }
////                }
////            }
////            br.close();
////        }
////
////        catch(Exception e){
////            e.printStackTrace();
////        }
//
//        if (p.getPosition() > 39) { // reset position
//            p.setPosition(p.getPosition() - 39);
//            p.setWealth(p.getWealth() + 200);
//            System.out.println("You passed Go! You collected $200!");
//        }
//        System.out.println(p.getName() + "'s position is: " + p.getPosition());
//        Property temp = properties.get((p.getPosition()));
//
//        if (this.checkBuyable(p) == false) {
//
//        } else {
//            if (properties.get(p.getPosition()).getColor().equals("None")) {
//                if (p.getName().startsWith("AI")) {//current player is AI
//                    this.buyProperty(p);
//                    return;
//                } else {
//                    System.out.println("Would you like to buy " + properties.get(p.getPosition()).getName()
//                            + " for " + properties.get(p.getPosition()).getCost() + "?");
//                }
//
//
//            } else {
//                String propertyName = properties.get(p.getPosition()).getName();
//                if (p.getName().startsWith("AI")) {//current player is AI
//                    this.buyProperty(p);
//                    return;
//                } else {
//                    System.out.println("Would you like to buy " + propertyName + " that's "
//                            + properties.get(p.getPosition()).getColor() + " for "
//                            + properties.get(p.getPosition()).getCost() + "?");
//                }
//            }
//        }
//    }


//    public void setGameEnd(boolean gameEnd) {
//        this.gameEnd = gameEnd;
//    }


//
//    public boolean checkBuyable(Player p) {
//        boolean buyAble = false;
//        Property temp = properties.get((p.getPosition()));
//
//        if (p.getPosition() == 2 || p.getPosition() == 7 || p.getPosition() == 10
//                || p.getPosition() == 17 || p.getPosition() == 20 || p.getPosition() == 22 ||
//                p.getPosition() == 33 || p.getPosition() == 36) {
//            buyAble = false;
//            System.out.println("You landed on an empty tile! ");
//            System.out.println("Your turn is over, Let the next player roll");
//            System.out.println();
//
//        } else if (p.getPosition() == 4 || p.getPosition() == 38) {
//            p.setWealth((p.getWealth() - 200));
//            System.out.println("You landed on Tax!");
//            System.out.println("$200 has been deducted from you!");
//            isBankrupt(p);
//            buyAble = false;
//        } else if (p.getPosition() == 30) {
//            p.setPosition(10);
//            System.out.println("You have been sent to jail, do not pass GO.");
//            System.out.println("Do not collect $200.");
//            inJail.add(p);
//            buyAble = false;
//        } else if (p.getPosition() == 0) {
//            System.out.println("You landed on GO! Please press roll!");
//            buyAble = false;
//        } else if (temp.isOwned()) { // if square they landed on is owned
//            if (!p.getName().equals(temp.getOwner())) { // if they are not the owner
//                System.out.println("You landed on " + temp.getOwner() + "'s property");
//                System.out
//                        .println("You must now pay " + temp.getOwner() + " $" + temp.getRentCost());
//                p.setWealth((int) (p.getWealth() - temp.getRentCost())); // subtract --------------------------rent
//                // money
//                for (Player owner : turn) { // find owner
//                    if (owner.getName().equals(temp.getOwner())) {
//                        owner.setWealth((owner.getWealth() + temp.getRentCost()));
//                    }
//                }
//
//                isBankrupt(p);
//            }
//
//        } else {
//            buyAble = true;
//        }
//        return buyAble;
//    }
//
//    public void addView(MonopolyFrame view) {
//        this.views.add(view);
//    }
//
//    public void removeView(MonopolyFrame view) {
//        this.views.remove(view);
//    }
//
//
//    public void setNumberOfPlayers(int numberOfPlayers) {
//        this.numberOfPlayers = numberOfPlayers;
//    }
//
//    public boolean checkJail(Player p) {
//        boolean jailed;
//        for (int i = 0; i < inJail.size(); i++) {
//            if (p == inJail.get(i)) {
//                return jailed = true;
//            }
//        }
//        return jailed = false;
//    }
//
//    public List<Player> getInJail() {
//        return inJail;
//    }


}
