import Models.Game;
import Models.Player;
import Models.Property;
import Models.Street;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePlay {

    private Game game;
    private GameGUI view;


    public GamePlay(Game game) {
        this.game = game;

    }

    // Temp methods to allow for testing backend
    public ArrayList<Player> TempSetPlayers() {
        this.game.setPlayerList(new ArrayList<Player>(Arrays.asList(new Player("p1"),
                new Player("p2"),
                new Player("p3"))));
        this.game.setCurrentPlayer(this.game.getPlayerList().get(0));
        return this.game.getPlayerList();
    }


    public int rollDice() {
        int rollNumber = this.game.getDice().rollDice();
        return this.game.getDice().getDiceRoll();
    }


    public int movePlayerPosition(Player player, int diceRoll) {
        int newPosition = player.getPosition() + diceRoll;
        int maxPos = this.game.getBoard().getMaxPosition();

        if (newPosition > maxPos) {
            this.game.getPlayer(player).setPosition(newPosition - maxPos);
        } else {
            this.game.getPlayer(player).setPosition(maxPos - newPosition);
        }

        // TODO maybe will modify this for cleanliness
        return this.game.getPlayer(player).getPosition();
    }

    public boolean checkPositionEvent(Player player) {
        int position = player.getPosition();
        Property currentProperty = this.game.getProperty(position);
        System.out.println(EventAlertHandler.landedOnPropertyEvent(player, currentProperty));

        if (this.payRent(player)) {
            System.out.println(EventAlertHandler.rentPayEvent(player, currentProperty.getOwner()));
        }

        if (position == this.game.getJailPosition()) {
            System.out.println(EventAlertHandler.jailEvent(player));
            // TODO Put in jail event
        }



        return true;
    }

    // TODO check if go has been passed method


    public String printPlayerStatus(Player player) {

        StringBuilder sb = new StringBuilder();
        sb.append(player.toString() + "\n");
        sb.append(this.game.printPlayerProperties(player));

        return sb.toString();
    }

    public Player passTurn(Player player) {

        return this.game.passTurn(player);

    }

    // TODO add a check method to check if the property is chance or chest

    // TODO add a check method to check if the property is jail

    public boolean buyHouse(Player player, Property property) {
        if (this.game.addHouse(property)) {
            ((Street) property).addHouse();
            return true;
        }

        return false;
    }

    public boolean buyHotel(Player player, Property property) {
        if (this.game.addHouse(property)) {
            ((Street) property).addHotel();
            return true;
        }

        return false;
    }

    public boolean buyProperty(Property property) {
        Player cp = this.game.getCurrentPlayer();
        int currentPos = cp.getPosition();

        if (this.game.setPropertyOwner(cp, property)) {
            int newCurrentPlayerWealth = (int) (cp.getWealth() - property.getCost());

            if (newCurrentPlayerWealth < 0) {
                System.out.println(EventAlertHandler.failPurchaseEvent(property, cp));

                return false;
            } else {
                this.game.getCurrentPlayer().setWealth(newCurrentPlayerWealth);
                System.out.println(EventAlertHandler.successPurchaseEvent(property, cp));

                return true;
            }
        }
        return false;
    }

    public boolean payRent(Player player) {
        int position = player.getPosition();

        Property property = this.game.getProperty(position);
        Player owner = property.getOwner();

        if (owner == null) {
            return false;
        } else {
            double rentCost = property.getRentCost();
            player.setWealth(player.getWealth() - rentCost);
            owner.setWealth(owner.getWealth() + rentCost);

            if (player.isBankrupt()) {
                this.game.removePropertyOwner(player);
                this.game.removePlayer(player);
                System.out.println(EventAlertHandler.bankruptcyAlertEvent(player));
            }
        }

        return true;
    }

    public boolean quitGame(Player player) {
        System.out.println(EventAlertHandler.quitGameEvent(player));
        return this.game.removePlayer(player);
    }

    public Game getGame() {
        return this.game;
    }

}
