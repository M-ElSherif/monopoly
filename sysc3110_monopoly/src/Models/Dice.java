package Models;

/**
 * Author: Keefer Belanger Date: 2021-10-20
 */

public class Dice {

    private int die1;
    private int die2;

    // TODO add docs
    public int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }

    /**
     * Rolls the two Models.Dice
     *
     * @return a sum value of the two dice
     */
    public int rollDice() {
        this.die1 = rollDie();
        this.die2 = rollDie();
        return this.die1 + this.die2;
    }

    /**
     * Checks if the two dice rolled are equal
     *
     * @return true if dice rolled are equal and false otherwise
     */
    public boolean rolledDouble() {
        return die1 == die2;
    }

    public int getDiceRoll() {
        return this.die1 + this.die2;
    }

    public String toString() {
        return die1 + " + " + die2;
    }


}