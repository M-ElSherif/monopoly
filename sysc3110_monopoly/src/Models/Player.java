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
    private HashMap<Property, List<Integer>> ownedAssets; // Integer[] array represents houses then hotels

    /**
     * Constructor for the Models.Player class
     *
     * @param name as a string
     */
    public Player(String name) {
        this.name = name;
        this.wealth = 1500;
        this.position = 0;
        ownedAssets = new HashMap<Property, List<Integer>>();
    }


    //TODO add docs
    public boolean addAsset(Property property, Integer houses, Integer hotels) {

        if (this.ownedAssets.containsKey(property)) {
            List<Integer> housesAndHotels = this.ownedAssets.get(property);
            int houseCount = this.getHouseCount(property);
            int hotelCount = this.getHotelCount(property);

            if (houses != null && hotels == null) {
                housesAndHotels.set(0, houseCount + houses);
            } else if (houses == null && hotels != null) {
                housesAndHotels.set(0, hotelCount + hotels);
            } else {
                housesAndHotels.set(0, houseCount + houses);
                housesAndHotels.set(0, hotelCount + hotels);
            }
        } else {
            List<Integer> housesAndHotels = new ArrayList<Integer>();
            housesAndHotels.add(0, (houses == null ? 0 : houses));
            housesAndHotels.add(1, (hotels == null ? 0 : hotels));
            this.ownedAssets.put(property, housesAndHotels);
        }

        return true;
    }

    public boolean ownsProperty(Property property) {
        if (this.ownedAssets.containsKey(property)) {
            return true;
        }
        return false;
    }

    // TODO docs
    public int getHouseCount(Property property) {
        return this.ownedAssets.get(property).get(0);
    }

    // TODO docs
    public int getHotelCount(Property property) {
        return this.ownedAssets.get(property).get(1);
    }

    // TODO docs
    public boolean isBankrupt() {
        return this.getWealth() <= 0 ? true : false;
    }

    // TODO docs
    public Set<Property> getProperties() {
        return this.ownedAssets.keySet();
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
                ", ownedAssets=" + ownedAssets +
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
