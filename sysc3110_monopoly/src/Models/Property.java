package Models;// Integrates with play.java
// Will receive a property object from the play.java

/**
 * Author: Kareem El Assad Date: 2021-10-20
 */
public class Property {

    private Player owner;
    private String name;
    private String color;
    private double cost;
    private double rentCost;
    private double houseCost;
    private double hotelCost;
    private int position;

    /**
     * Constructor for the Models.Property class
     *
     * @param name
     * @param position
     * @param color
     * @param cost
     * @param owner
     */
    public Property(String name,
                    int position,
                    String color,
                    double cost,
                    Player owner) {
        this.name = name;
        this.cost = cost;
        this.rentCost = cost * 0.15;
        this.houseCost = cost * 0.05;
        this.hotelCost = cost * 0.10;
        this.color = color;
        this.position = position;
        this.owner = owner;
    }

    // TODO docs
    public boolean removeOwner(Player player) {
        this.owner = null;
        return true;
    }

    /**
     * Gets the property name
     *
     * @return The property name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the property cost
     *
     * @return The property cost as a double
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the rent cost
     *
     * @return The rent cost as a double
     */
    public double getRentCost() {
        return rentCost;
    }

    /**
     * Gets the property color
     *
     * @return The property color as a string
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the property position as an index in the properties list
     *
     * @return The property position as an int
     */
    public int getPosition() {
        return position;
    }

    /**
     * Gets the current property owner
     *
     * @return The property owner as a string
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the current property owner
     *
     * @param owner as a string
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // TODO fix
    @Override
    public String toString() {
        return name;
    }
}
