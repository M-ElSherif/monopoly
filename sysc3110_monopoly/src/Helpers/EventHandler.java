package Helpers;

import Models.Player;
import Models.Property;

public class EventHandler {

    public EventHandler() {
    }

    public static String buyPropertyEvent(Property property) {
        return String.format("Would you like to purchase %s", property.getName());
    }

    public static String successPurchaseEvent(Property property, Player owner) {
        return String.format("Player %s has purchased %s", property.getName(), owner.getName());
    }

    public static String failPurchaseEvent(Property property, Player player) {
        return String.format("Player %s cannot afford %s", property.getName(), player.getName());
    }

    public static String bankruptcyAlertEvent(Player player) {
        return String.format("Player %s is bankrupt!", player.getName());
    }
}
