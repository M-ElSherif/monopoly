package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves to store all data relevant to a game of monopoly (persistence)
 */

public class Game {

    private ArrayList<Player> playerList;
    private Board board;
    private Player currentPlayer;
    private Dice dice;
    private int turnCount;


    public Game() {
        this.playerList = new ArrayList<>();
        this.board = new Board();
        this.dice = new Dice();
        this.currentPlayer = null;
        this.turnCount = 0;
    }

    public int rollDice() {
        this.dice.rollDice();
        return this.dice.getDiceRoll();
    }

    public boolean isDiceRollDouble() {
        return this.dice.rolledDouble();
    }

    public Player passTurn(Player player) {
        int i = this.playerList.indexOf(player);

        if (i == this.playerList.size() - 1) {
            this.currentPlayer = this.playerList.get(0);
        } else {
            this.currentPlayer = this.playerList.get(i + 1);
        }

        this.incrementTurnCount();
        return this.currentPlayer;
    }

    public boolean addPlayer(Player player) {
        return this.playerList.add(player);
    }

    public boolean removePlayer(Player player) {
        return this.playerList.remove(player);
    }

    public Player getPlayer(Player player) {
        return this.playerList.get(this.playerList.indexOf(player));
    }

    public int getPlayerListSize() {
        return this.playerList.size();
    }

    public Property getProperty(int position) {
        return this.board.getProperty(position);
    }

    public boolean setPropertyOwner(Player player, Property property) {
        return this.board.setPropertyOwner(player, property);
    }

    public Player getPropertyOwner(int position) {
        return this.board.getPropertyOwner(position);
    }

    public boolean removePropertyOwner(Player player) {
        return this.board.removePropertyOwner(player);
    }

    public boolean addHouse(Property property) {
        return this.board.addHouse(property);
    }

    public boolean addHotel(Property property) {
        return this.board.addHotel(property);
    }

    public boolean putInJail(Player player) {
        if (this.getPlayer(player) != null) {
            this.getPlayer(player).setInJail(true);
            return true;
        }

        return false;
    }

    public boolean removeFromJail(Player player) {
        if (this.getPlayer(player) != null) {
            this.getPlayer(player).setInJail(false);
            return true;
        }

        return false;
    }

    public List<Property> getPlayerProperties(Player player) {
        return this.board.getPlayerProperties(player);
    }

    public String printPlayerProperties(Player player) {
        return this.board.printPlayerProperties(player);
    }

    public int getJailPosition() {
        return this.board.getGoJailPosition();
    }

    public int getGoPosition() {
        return this.board.getGoPosition();
    }

    public int incrementTurnCount() {
        this.turnCount++;

        return this.turnCount;
    }


    //region Setters and Getters
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    //endregion

}
