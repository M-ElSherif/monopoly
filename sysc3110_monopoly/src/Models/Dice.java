package Models;

/**
 * Author: Keefer Belanger Date: 2021-10-20
 */

public class Dice {
    private int die1;
    private int die2;

    /**
     * Rolls the two Models.Dice
     * 
     * @return a sum value of the two dice
     */
    public int roll() {
        die1 = (int) (Math.random() * 6) + 1;
        die2 = (int) (Math.random() * 6) + 1;
        System.out.println(die1 + " + " + die2);
        return die1 + die2;
    }

    /**
     * Checks if the two dice rolled are equal
     * 
     * @return true if dice rolled are equal and false otherwise
     */
    public boolean rolledDouble() {
        return die1 == die2;
    }
}