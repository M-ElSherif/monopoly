package Models;

// Integrates with play.java
// Will receive a property object from the play.java

/**
 * Author: Kareem El Assad Date: 2021-11-22
 *
 */
public class Hotel {
    private Integer hotelLocation;
    private double hotelCost;
    private double newRent;
    private int hotelCount;

    /**
     * Constructor for the Models.Hotel class
     * 
     * @param houseLocation
     * @param hotelCost
     * @param newRent
     * @param hotelCount
     */
    public Hotel(int hotelLocation, double houseCost, double newRentCost, int hotelCount) {
        this.hotelLocation = hotelLocation;
        this.hotelCost = houseCost;
        this.newRent = newRentCost;
        this.hotelCount = hotelCount;
    }

    /**
     * Gets the hotel name
     * 
     * @return The hotel name as a string
     */
    public int getHotelCount() {
        return hotelCount;
    }

    /**
     * Gets the hotel location
     * 
     * @return The hotel location as an integer
     */
    public int getHotelLocation() {
        return hotelLocation;
    }

    /**
     * Gets the hotel cost
     * 
     * @return The hotel cost as a double
     */
    public double getHotelCost() {
        return hotelCost;
    }

    /**
     * Gets the new rent cost
     * 
     * @return The new rent cost as a double
     */
    public double getNewRent() {
        return newRent;
    }

    /**
     * Sets the new rent cost
     * 
     * @param newRent
     */
    public void setNewRent(double newRent) {
        this.newRent = newRent;
    }

    /**
     * Sets the hotel cost
     * 
     * @param hotelCost
     */
    public void setHotelCost(double hotelCost) {
        this.hotelCost = 400;
    }

}