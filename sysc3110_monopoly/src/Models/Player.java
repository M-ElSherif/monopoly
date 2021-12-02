package Models;

import java.util.*;

/**
 * Author: Keefer Belanger Date: 2021-10-20
 */

public class Player {

    private String name;
    private double wealth;
    private int position;
    private boolean inJail;

    /**
     * Constructor for the Models.Player class
     *
     * @param name as a string
     */
    public Player(String name) {
        this.name = name;
        this.wealth = 1500;
        this.position = 0;
    }

    // TODO docs
    public boolean isBankrupt() {
        return this.getWealth() <= 0 ? true : false;
    }

    /**
     * Gets the players name
     *
     * @return the players name as a string
     */
    public String getName() {
        return name;
    }

    // TODO add docs
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the players total money
     *
     * @return the players total money as a double
     */
    public double getWealth() {
        return wealth;
    }

    /**
     * Sets the players total money
     *
     * @param money as a double
     */
    public void setWealth(double money) {
        this.wealth = money;
    }

    /**
     * Adds money to the players total money
     *
     * @param addMoney as an int
     */
    public void addWealth(int addMoney) {
        this.wealth += addMoney;
    }

    /**
     * Gets the players position on the board
     *
     * @return the players position as an int
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the players position on the board
     *
     * @param position as an int
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", wealth=" + wealth +
                ", position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
