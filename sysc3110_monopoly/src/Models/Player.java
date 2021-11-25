package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Author: Keefer Belanger Date: 2021-10-20
 */

public class Player {
    private String playerName;
    private double totalMoney;
    private int position;
    private ArrayList<Property> properties;
    private int jailCounter;
    private int rollCounter;
    private HashMap<String, Integer> ownedHouse;


    /**
     * Constructor for the Models.Player class
     *
     * @param name as a string
     */
    public Player(String name) {
        this.playerName = name;
        this.totalMoney = 1500;
        this.position = 0;
        this.properties = new ArrayList<>();
        jailCounter = 0;
        rollCounter = 0;
        ownedHouse = new HashMap<String, Integer>();

    }

    /**
     * Gets the players name
     *
     * @return the players name as a string
     */
    public String getName() {
        return playerName;
    }


    /**
     * Gets the players total money
     *
     * @return the players total money as a double
     */
    public double getMoney() {
        return totalMoney;
    }

    /**
     * Sets the players total money
     *
     * @param money as a double
     */
    public void setMoney(double money) {
        this.totalMoney = money;
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

    /**
     * Adds money to the players total money
     *
     * @param addMoney as an int
     */
    public void addMoney(int addMoney) {
        this.totalMoney += addMoney;
    }

    public int getJailCounter() {
        return jailCounter;
    }



    public void setJailCounter(int jailCounter) {
        this.jailCounter = jailCounter;
    }

    public int getRollCounter() {
        return rollCounter;
    }

    public void setRollCounter(int rollCounter) {
        this.rollCounter = rollCounter;
    }

    /**
     * Deducts the property price from the players total money and sets the property
     * bought to owned by the player that bought it and adds that property to the
     * players list of properties
     *
     * @param property as a Models.Property object
     */
    public void buyProperty(Property property) {
        addMoney((int) -property.getPropertyCost());
        property.setOwned(true);
        property.setCurrentPropertyOwner(this.playerName);
        properties.add(property);
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Double.compare(player.totalMoney, totalMoney) == 0 && position == player.position && Objects.equals(playerName, player.playerName) && Objects.equals(properties, player.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, totalMoney, position, properties);
    }

    public HashMap<String, Integer> getOwnedHouse() {
        return ownedHouse;
    }
}
