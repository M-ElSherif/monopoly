package Models;// Integrates with play.java
// Will receive a property object from the play.java

/**
 * Author: Kareem El Assad Date: 2021-11-22
 *
 */
public class House {
    private Integer houseLocation;
    private double houseCost;
    private int houseCount;
    private double newRentCost;

    /**
     * Constructor for the Models.House class
     * 
     * @param houseLocation
     * @param houseCost
     * @param newRentCost
     * @param houseCount
     */
    public House(int houseLocation, double houseCost, double newRentCost, int houseCount) {
        this.houseLocation = houseLocation;
        this.houseCost = houseCost;
        this.newRentCost = newRentCost;
        this.houseCount = houseCount;
    }

    /**
     * Gets the house name
     * 
     * @return The house name as a string
     */
    public int getHouseCount() {
        return houseCount;
    }

    /**
     * Gets the house location
     * 
     * @return The house location as an integer
     */
    public int getHouseLocation() {
        return houseLocation;
    }

    /**
     * Gets the house cost
     * 
     * @return The house cost as a double
     */
    public double getHouseCost() {
        return houseCost;
    }

    /**
     * Gets the new rent cost
     * 
     * @return The new rent cost as a double
     */
    public double getNewRentCost() {
        return newRentCost;
    }

    /**
     * Sets the new rent cost
     * 
     * @param newRentCost
     */
    public void setNewRent(double newRent) {
        this.newRentCost = newRent;
    }

    /**
     * Sets the house cost
     * 
     * @param houseCost
     */
    public void setHouseCost(double houseCost) {
        this.houseCost = 75;
    }

}