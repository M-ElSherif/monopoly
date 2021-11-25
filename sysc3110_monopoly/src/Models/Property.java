package Models;// Integrates with play.java
// Will receive a property object from the play.java

/**
 * Author: Kareem El Assad Date: 2021-10-20
 *
 */
public class Property {
    private String propertyName;
    private double propertyCost;
    private double rentCost;
    private boolean isOwned;
    private String propertyColor;
    private int propertyPosition;
    private String currentPropertyOwner;

    /**
     * Constructor for the Models.Property class
     * @param propertyName
     * @param propertyPosition
     * @param propertyColor
     * @param propertyCost
     * @param isOwned
     * @param currentPropertyOwner
     */
    public Property(String propertyName, int propertyPosition, String propertyColor, double propertyCost,
                    boolean isOwned, String currentPropertyOwner) {
        this.propertyName = propertyName;
        this.propertyCost = propertyCost;
        rentCost = propertyCost * 0.10;
        this.isOwned = isOwned;
        this.propertyColor = propertyColor;
        this.propertyPosition = propertyPosition;
        this.currentPropertyOwner = currentPropertyOwner;
    }

    /**
     * Gets the property name
     * @return The property name as a string
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Gets the property cost
     * @return The property cost as a double
     */
    public double getPropertyCost() {
        return propertyCost;
    }

    /**
     * Gets the rent cost
     * @return The rent cost as a double
     */
    public double getRentCost() {
        return rentCost;
    }

    /**
     * Checks if isOwned is true or false
     * @return IsOwned as a boolean
     */
    public boolean isOwned() {
        return isOwned;
    }

    /**
     * Sets the owner as true or flase
     * @param isOwned
     */
    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    /**
     * Gets the property color
     * @return The property color as a string
     */
    public String getPropertyColor() {
        return propertyColor;
    }

    /**
     * Gets the property position as an index in the properties list
     * @return The property position as an int
     */
    public int getPropertyPosition() {
        return propertyPosition;
    }

    /**
     * Gets the current property owner
     * @return The property owner as a string
     */
    public String getCurrentPropertyOwner() {
        return currentPropertyOwner;
    }

    /**
     * Sets the current property owner
     *
     * @param currentPropertyOwner as a string
     */
    public void setCurrentPropertyOwner(String currentPropertyOwner) {
        this.currentPropertyOwner = currentPropertyOwner;
    }
}