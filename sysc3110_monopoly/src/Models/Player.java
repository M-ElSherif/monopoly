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


    public Player(String name) {
        this.name = name;
        this.wealth = 1500;
        this.position = 0;
    }

    // TODO docs
    public boolean isBankrupt() {
        return this.getWealth() <= 0 ? true : false;
    }


    public String getName() {
        return name;
    }

    // TODO add docs
    public void setName(String name) {
        this.name = name;
    }


    public double getWealth() {
        return wealth;
    }


    public void setWealth(double money) {
        this.wealth = money;
    }


    public double addWealth(int addMoney) {
        this.wealth += addMoney;
        return this.wealth;
    }

    public double subtractWealth(int addMoney) {
        this.wealth -= addMoney;
        return this.wealth;
    }

    public int getPosition() {
        return position;
    }


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
                ", jailed=" + inJail +
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
