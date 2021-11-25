import Models.Player;
import Models.Property;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Dana El Sherif & Chia Yu
 */

public class MonopolyController implements ActionListener {

    private MonopolyFrame frame;
    private JFrame secondFrame;
    private Play theModel;

    public MonopolyController(Play model, MonopolyFrame frame) {
        this.frame = frame;
        this.theModel = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("R")) {
            if (theModel.getCurrentPlayer() == null) {
                System.out.println("Please choose number of players first");
            } else {
                Player currentPlayer = theModel.getCurrentPlayer();
                Property currentPropery = theModel.properties.get((currentPlayer.getPosition()));
                if (theModel.checkBuyable(currentPlayer) == true) {
                    System.out.println("Please choose Buy or Pass first");

                } else {
                    if (theModel.checkJail(currentPlayer) == true && currentPlayer.getRollCounter() == 0) {
                        //call roll the first time and warn them
                        Player player = theModel.changeTurn();
                        theModel.rollDie(player);
                        //they choose roll again -> now they can't pay fine
                    } else if (theModel.checkJail(currentPlayer) == true && currentPlayer.getRollCounter() == 1) {
                        theModel.rollDie(currentPlayer);
                    } else if (theModel.checkJail(currentPlayer) == true && currentPlayer.getRollCounter() == 2) {
                        if (currentPlayer.getJailCounter() != 3) {
                            frame.getJailButton().setEnabled(false);
                            theModel.rollDie(currentPlayer);
                        } else {
                            System.out.println("You failed at rolling doubles three times.");
                            System.out.println("You must now pay $50 to get out of jail.");
                            frame.getJailButton().setEnabled(true);
                        }
                    } else {
                        Player player = theModel.changeTurn();
                        theModel.rollDie(player);
                        frame.getJailButton().setEnabled(true);
                    }
                }
            }
        }

        else if(e.getActionCommand().equals("Q")){
            System.out.print("Game is over! ");
            System.out.println("Thank you for playing");
            theModel.setGameEnd(true);
        }

        else if(e.getActionCommand().equals("D")){

            JOptionPane.showMessageDialog(null,theModel.printStatus().toString());

        }


        else if(e.getActionCommand().equals("H")){
            System.out.println("Please visit the link:");
            System.out.println("https://www.hasbro.com/common/instruct/00009.pdf");
        }

        else if(e.getActionCommand().equals("B")){
            if(theModel.checkBuyable(theModel.getCurrentPlayer())){
                theModel.buyProperty(theModel.getCurrentPlayer());
            }else{
                JOptionPane.showMessageDialog(null,"You can't buy! Next Models.Player's turn!");
            }
        }


        else if(e.getActionCommand().equals("P")){
            if(theModel.checkBuyable(theModel.getCurrentPlayer())){
                System.out.println("Pass to next player");
                Player player = theModel.changeTurn();
                theModel.rollDie(player);
            }
        }
        else if(e.getActionCommand().equals("C")){
            JComboBox source = (JComboBox) e.getSource();
            int playerNUmber = source.getSelectedIndex()+1;
            theModel.setNumberOfPlayers(playerNUmber);
            //theModel.welcomeMessage();
            theModel.playerSetup();
            ((JComboBox<?>) e.getSource()).setEnabled(false);

        } else if(e.getActionCommand().equals("J")) {
            Player p = theModel.getCurrentPlayer();
            if (theModel.checkJail(p) == true){
                p.setMoney(p.getMoney() - 50);
                theModel.getInJail().remove(p);
                theModel.rollDie(p);
            }
        } else if(e.getActionCommand().equals("Back")){
            secondFrame.dispose();
            frame.setVisible(true);
        }
    }
}
