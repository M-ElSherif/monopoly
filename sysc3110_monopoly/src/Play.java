import Models.Board;
import Models.Dice;
import Models.Player;
import Models.Property;

import java.util.*;

/**
 * Author: Dana El Sherif and Chia-Yu Liu
 *
 * Reviewer: Kareem El Assad
 *
 * Editor: Keefer Belanger
 *
 * Date: 2021-10-18
 *
 */
public class Play {

    HashMap<Integer, Property> properties;
    private boolean gameEnd; // boolean flag
    private List<MonopolyFrame> views;
    private Dice dice = new Dice();
    public static ArrayList<Player>turn;
    private Player currentPlayer;
    private Player player;
    private Player AI;
    int count = 0;
    private int numberOfPlayers = 0;
    private List<Player>inJail;
    int turnNumberinJail;
    private StringBuilder sb;
    private int BrownTile = 2;
    private int lightblueTile = 3;
    private int pinkTile = 3;
    private int orangeTile = 3;
    private int redTile = 3;
    private int yellowTile = 3;
    private int greenTile = 3;
    private int darkblueTile = 2;






    /**
     * Constructor for the Play class. It initializes players as a new arraylist and
     * properties as a board
     */
    public Play() {
        turn = new ArrayList<>();
        properties = Board.createBoard();
        this.views = new ArrayList<>();
        gameEnd = false;
        inJail = new ArrayList<>();
        int turnNumberinJail = 0;
    }

    /**
     * Gets the current player
     * @return Models.Player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    /**
     * Checks if the game has ended
     * @return Boolean
     */
    public boolean checkGameEnd(){
        if (gameEnd == true) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Calls the roll class to roll the dice for a specific player
     * @param p
     */
    public void rollDie(Player p) {
        if (this.checkGameEnd() != true) {
            int rollNumber = dice.roll();
            if (checkJail(p) == true) {
                if (p.getRollCounter() == 0){
                    p.setRollCounter(1+ p.getRollCounter());
                }
                else if (p.getRollCounter() == 1) {
                    System.out.println("Press Roll again if you want to try and roll a double to get out of jail");
                    System.out.println("Otherwise, pay the fine");
                    p.setRollCounter(1+ p.getRollCounter());
                }else if (p.getRollCounter() == 2){
                    if (checkDoubles(p) == true) {
                        System.out.println("You rolled a double, so you get out of jail!");
                        inJail.remove(player);
                        p.setPosition(p.getPosition() + rollNumber);
                        this.playerPosition(p);
                    }else{
                        System.out.println("You didn't roll a double, you remain in jail");
                        System.out.println("Next Models.Player!");

                        if (p.getJailCounter() > 3){       //number of times player attempts doubles
                            p.setJailCounter(0);
                        }
                    }
                    p.setRollCounter(0);
                }
            }else {
                p.setPosition(p.getPosition() + rollNumber);
                this.playerPosition(p);
            }
        }
    }

    public boolean checkDoubles(Player p){
        if (dice.rolledDouble()== true){
            return true;
        }
        return false;
    }

    /**
     * Cycles through the players turns
     * @return Models.Player
     */
    public Player changeTurn(){
        if (count == turn.size()-1) {
            count = 0;
            currentPlayer = turn.get(0);
            System.out.println(currentPlayer.getName() + "'s turn to roll dice!");
        }else if (currentPlayer == turn.get(this.count)){
            currentPlayer = turn.get(this.count + 1);
            count++;
            System.out.println(currentPlayer.getName() + "'s turn to roll dice!");
        }
        return currentPlayer;
    }

    /**
     * Runs as everything a player can do in their turn
     */
    public void playerPosition(Player p) {

//        String path = "Models.Board.json";
//
//        try {
//            String content = new String(Files.readAllBytes(Paths.get(path)));
//            JSONObject jsonObject = new JSONObject(content);
//            JSONArray tiles = jsonObject.getJSONArray("tiles");
//
//            BufferedReader br = new BufferedReader(new FileReader("Game.txt"));
//            String brInfo = br.readLine();
//
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Game.txt")));
//
//            while (brInfo != null) {
//                for (int i = 0; i < tiles.length(); i++) {
//                    JSONObject obj = (JSONObject) tiles.get(i);
//
//                    Integer id = (Integer) obj.get("id");
//                    String type = (String) obj.get("type");
//                    String color = (String) obj.get("color");
//
//                    //Check if space is special
//                    if (type == "special") {
//                        System.out.println("You landed on an empty tile! Next Models.Player's turn!");
//                    }
//
//                    //Check if space is a tax space
//                    else if (type == "tax") {
//                        p.setMoney((p.getMoney() - 200));
//                        System.out.println("You landed on Tax!");
//                        System.out.println("$200 has been deducted from you!");
//                        isBankrupt(p);
//                    }
//
//                    //Check if space is a property space
//                    else if (type == "property") {
//                        if (brInfo.contains("owner")) {
//                            if (color == "brown"){
//
//                            }
//                        }
//                        else{
//                            String propertyName = properties.get(p.getPosition()).getPropertyName();
//                            System.out.println("Would you like to buy " + propertyName + " that's "
//                                    + properties.get(p.getPosition()).getPropertyColor() + " for "
//                                    + properties.get(p.getPosition()).getPropertyCost() + "?");
//                        }
//                    }
//                }
//            }
//            br.close();
//        }
//
//        catch(Exception e){
//            e.printStackTrace();
//        }

        if (p.getPosition() > 39) { // reset position
            p.setPosition(p.getPosition() - 39);
            p.setMoney(p.getMoney() + 200);
            System.out.println("You passed Go! You collected $200!");
        }
        System.out.println(p.getName() + "'s position is: " + p.getPosition());
        Property temp = properties.get((p.getPosition()));

        if (this.checkBuyable(p) == false){

        } else {
            if (properties.get(p.getPosition()).getPropertyColor().equals("None")) {
                if(p.getName().startsWith("AI")){//current player is AI
                    this.buyProperty(p);
                    return;
                }else{
                    System.out.println("Would you like to buy " + properties.get(p.getPosition()).getPropertyName()
                            + " for " + properties.get(p.getPosition()).getPropertyCost() + "?");
                }


            } else {
                String propertyName = properties.get(p.getPosition()).getPropertyName();
                if(p.getName().startsWith("AI")){//current player is AI
                    this.buyProperty(p);
                    return;
                }else {
                    System.out.println("Would you like to buy " + propertyName + " that's "
                            + properties.get(p.getPosition()).getPropertyColor() + " for "
                            + properties.get(p.getPosition()).getPropertyCost() + "?");
                }
            }
        }
    }


    /**
     * Checks if the player is bankrupt to then remove them from the game
     *
     * @param p as a Models.Player object
     * @return true if player has less than or equal to zero money
     */
    private boolean isBankrupt(Player p) {
        if (turn.size() <= 1) {
            gameEnd = true;
            return false;
        } else if (p.getMoney() <= 0) {
            System.out.println(p.getName() + " is Bankrupt!");
            turn.remove(p);
            for (int i =0; i< p.getProperties().size(); i++){
                this.removeProperties(p.getProperties().get(i));
            }
            return true;
        } else {
            return false;
        }
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    /**
     * Prints the players status
     */
    public StringBuilder printStatus() {
        sb = new StringBuilder();
        for(Player p: turn){

            sb.append("Models.Player:" + p.getName());
            sb.append(System.getProperty("line.separator"));

            sb.append("Position on board: " + p.getPosition());
            sb.append(System.getProperty("line.separator"));

            sb.append("Total Money " + p.getMoney());
            sb.append(System.getProperty("line.separator"));

            if (p.getProperties().size() >= 1) {
                sb.append("Owned Properties: ");
                for (Property property : p.getProperties()) {
                    sb.append(property.getPropertyName() + ", \n");
                }
                System.out.println();
                sb.append(System.getProperty("line.separator"));
            }

        }
        return sb;
    }

    public void removeProperties(Property p){
        p.setOwned(false);
    }

    /**
     * Lets the player buy a property
     * @param p
     */
    public void buyProperty(Player p){
        p.buyProperty(properties.get(p.getPosition()));
        System.out.println("You are now the owner of " + properties.get(p.getPosition()).getPropertyName());
        System.out.println("Your turn has ended, Let the next player roll");
        System.out.println();
    }

    public boolean checkBuyable(Player p){
        boolean buyAble = false;
        Property temp = properties.get((p.getPosition()));

        if (p.getPosition() == 2 || p.getPosition() == 7 || p.getPosition() == 10
                || p.getPosition() == 17 || p.getPosition() == 20 || p.getPosition() == 22 ||
                p.getPosition() == 33 || p.getPosition() == 36) {
            buyAble = false;
            System.out.println("You landed on an empty tile! ");
            System.out.println("Your turn is over, Let the next player roll");
            System.out.println();

        }else if (p.getPosition() == 4 || p.getPosition() == 38) {
            p.setMoney((p.getMoney() - 200));
            System.out.println("You landed on Tax!");
            System.out.println("$200 has been deducted from you!");
            isBankrupt(p);
            buyAble = false;
        }else if (p.getPosition() == 30) {
            p.setPosition(10);
            System.out.println("You have been sent to jail, do not pass GO.");
            System.out.println("Do not collect $200.");
            inJail.add(p);
            buyAble = false;
        }else if (p.getPosition() == 0) {
            System.out.println("You landed on GO! Please press roll!");
            buyAble = false;
        } else if (temp.isOwned()) { // if square they landed on is owned
            if (!p.getName().equals(temp.getCurrentPropertyOwner())) { // if they are not the owner
                System.out.println("You landed on " + temp.getCurrentPropertyOwner() + "'s property");
                System.out
                        .println("You must now pay " + temp.getCurrentPropertyOwner() + " $" + temp.getRentCost());
                p.setMoney((int) (p.getMoney() - temp.getRentCost())); // subtract --------------------------rent
                // money
                for (Player owner : turn) { // find owner
                    if (owner.getName().equals(temp.getCurrentPropertyOwner())) {
                        owner.setMoney((owner.getMoney() + temp.getRentCost()));
                    }
                }

                isBankrupt(p);
            }

        }else{
            buyAble = true;
        }
        return buyAble;
    }

    public void addView(MonopolyFrame view){
        this.views.add(view);
    }

    public void removeView(MonopolyFrame view){
        this.views.remove(view);
    }


    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public boolean checkJail(Player p){
        boolean jailed;
        for(int i =0; i< inJail.size(); i++){
            if(p == inJail.get(i)){
                return jailed = true;
            }
        }
        return jailed = false;
    }

    public List<Player> getInJail() {
        return inJail;
    }

    public void playerSetup(){
        //set up real player
        if(numberOfPlayers >0){
            for(int i = 0; i < numberOfPlayers ; i++ ){

                this.player = new Player("Models.Player: " + (i+1));

                this.turn.add(player);
            }
        }
        this.currentPlayer = turn.get(0);
        System.out.println();
        System.out.println("We have "+ (numberOfPlayers) + " players!");

        //set up AI
        for(int i = 0; i < (4-numberOfPlayers) ; i++ ){
            this.player = new Player("AI" + (i+1));
            this.turn.add(player);
        }

        for(int i = 0; i < this.turn.size() ; i++ ){
            System.out.println(this.turn.get(i).getName());
        }


    }





}
