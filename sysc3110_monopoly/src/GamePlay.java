import Models.Game;
import Models.Player;
import Models.Property;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePlay {

    private Game game;
    private GameGUI view;


    public GamePlay(Game game) {
        this.game = game;

    }

    // Temp methods to allow for testing backend
    public ArrayList<Player> TempSetPlayers() {
        this.game.setPlayerList(new ArrayList<Player>(Arrays.asList(new Player("p1"),
                new Player("p2"),
                new Player("p3"))));
        this.game.setCurrentPlayer(this.game.getPlayerList().get(0));
        return this.game.getPlayerList();
    }


    public int rollDice() {
        int rollNumber = this.game.getDice().rollDice();
        return this.game.getDice().getDiceRoll();
    }


    public int movePlayerPosition(Player player, int diceRoll) {
        int newPosition = player.getPosition() + diceRoll;
        int maxPos = this.game.getBoard().getMaxPosition();

        if (newPosition > maxPos) {
            this.game.getPlayer(player).setPosition(newPosition - maxPos);
        } else {
            this.game.getPlayer(player).setPosition(maxPos - newPosition);
        }

        // TODO fix this
        return this.game.getPlayer(player).getPosition();
    }

    public boolean checkPositionEvent(Player player) {
        int position = player.getPosition();
        Property currentProperty = this.game.getProperty(position);
        System.out.println(EventHandler.landedOnPropertyEvent(player, currentProperty));

        // TODO payrent event

        if (position == this.game.getJailPosition()) {
            System.out.println(EventHandler.jailEvent(player));
            // TODO Put in jail event
        }

        return true;
    }

    // TODO check if go has been passed method


    public String printPlayerStatus(Player player) {

        return player.toString();

    }

    public Player passTurn(Player player) {

        return this.game.passTurn(player);

    }

    public boolean buyProperty(Property property) {
        Player cp = this.game.getCurrentPlayer();
        int currentPos = cp.getPosition();

        if (property.getOwner() == null) {
            int newCurrentPlayerWealth = (int) (cp.getWealth() - property.getCost());

            if (newCurrentPlayerWealth < 0) {
                System.out.println(EventHandler.failPurchaseEvent(property, cp));

                return false;
            } else {
                this.game.getCurrentPlayer().setWealth(newCurrentPlayerWealth);
                property.setOwner(cp);
                this.game.getBoard().setProperty(currentPos, property);
                System.out.println(EventHandler.successPurchaseEvent(property, cp));
                cp.addAsset(property, 0,0);

                return true;
            }
        }
        return false;
    }

    public boolean payRent(Player player) {
        int position = player.getPosition();

        Property property = this.game.getBoard().getProperty(position);
        Player owner = property.getOwner();
        Player playerToPay = this.game.getPlayer(player);

        if (owner != null) {
            double rentCost = property.getRentCost();
            playerToPay.setWealth(player.getWealth() - rentCost);
            owner.setWealth(owner.getWealth() + rentCost);

            if (player.isBankrupt()) {
                this.game.removePropertyOwner(player);
                this.game.removePlayer(player);
                System.out.println(EventHandler.bankruptcyAlertEvent(player));
            }
        }

        return true;
    }

    public boolean quitGame(Player player) {
        System.out.println(EventHandler.quitGameEvent(player));
        return this.game.removePlayer(player);
    }

    public Game getGame() {
        return this.game;
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
