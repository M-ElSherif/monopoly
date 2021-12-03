package Helpers;

import Models.Player;
import Models.Property;
import Models.Property;
import Models.Tax;

public class EventAlertHandler {

    public EventAlertHandler() {
    }

    public static String buyPropertyEvent(Property property) {
        return String.format("Would you like to purchase %s",
                property.getName());
    }

    public static String successPurchaseEvent(Property property, Player owner) {
        return String.format("Player %s has purchased %s for $%.2f",
                owner.getName(),
                property.getName(),
                property.getCost());
    }

    public static String failPurchaseEvent(Property property, Player player) {
        return String.format("Player %s cannot afford %s",
                property.getName(),
                player.getName());
    }

    public static String bankruptcyAlertEvent(Player player) {
        return String.format("Player %s is bankrupt!",
                player.getName());
    }

    public static String landedOnPropertyEvent(Player player, Property property) {
        return String.format("Player %s landed on %s, cost is $%.2f. Property is owned by: %s ",
                player.getName(),
                property.getName(),
                property.getCost(),
                property.getOwner() == null ? "NO OWNER" : property.getOwner());
    }

    public static String rentPayEvent(Player player, Player owner) {
        return String.format("Player %s has to pay rent to %s!",
                player.getName(),
                owner.getName());
    }

    public static String jailEnterEvent(Player player) {
        return String.format("Player %s has been jailed!!",
                player.getName());
    }

    public static String jailExitPayEvent(Player player) {
        return String.format("Player %s has paid the fine and left jail!!",
                player.getName());
    }

    public static String jailExitDoubleEvent(Player player) {
        return String.format("Player %s has rolled a double and left jail!!",
                player.getName());
    }

    public static String goEvent(Player player) {
        return String.format("Player %s has landed on GO, collect $200!!",
                player.getName());
    }

    public static String incomeTaxEvent(Player player, Property property) {
        return String.format("Player %s has landed on %s and must pay $%.2f",
                player.getName(),
                property.getName(),
                property.getRentCost());
    }

    public static String quitGameEvent(Player player) {
        return String.format("Player %s has quit the game!",
                player.getName());
    }
}
