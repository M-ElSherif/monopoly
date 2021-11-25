import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * @author Keefer Belanger
 * Editor: Dana El Sherif & Chia-Yu Liu
 */
public class MonopolyFrame extends JFrame {

    Play model;
    private JButton jailButton;

    /**
     * Constructor for the Monopoly GUI
     */
    public MonopolyFrame() {
        super("Monopoly");

        Play model = new Play();
        model.addView(this);
        MonopolyController controller = new MonopolyController(model, this);
        //Set the size of the window

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1980, 1080);

        //Setup for board image
        JLabel imageLabel = new JLabel(new ImageIcon("./diagrams/monopolyBoardResized.jpg"));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(new LineBorder(Color.BLACK));
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        imagePanel.setPreferredSize(new Dimension(200, 200));

        //Setup for Text Area
        JPanel eastPanel = new JPanel();
        JTextArea playerTextArea = new JTextArea(30, 30);
        playerTextArea.setEditable(false);
        playerTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
        playerTextArea.append("Welcome to the game!\n");

        JScrollPane scrollPane = new JScrollPane(playerTextArea);
        eastPanel.add(scrollPane);

        JTextAreaOutputStream out = new JTextAreaOutputStream(playerTextArea);
        System.setOut(new PrintStream(out));

        //Create button panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        //JLabel playerNum = new JLabel("Enter Models.Player Numbers:");

        // Setup for Buttons
        JButton rollButton = new JButton("Roll");
        JButton quitButton = new JButton("Quit");
        JButton playerStatusButton = new JButton("Display Models.Player Status");
        JButton helpButton = new JButton("Help");
        JButton buyButton = new JButton("Buy");
        JButton passButton = new JButton("Pass");
        jailButton = new JButton("Pay jail fine");


        // set up for player numbers drop down list
        JLabel dropdownMenu = new JLabel("Number of player: ");
        String[] numberOfPlayers = new String[4];
        numberOfPlayers[0] = "1";
        numberOfPlayers[1] = "2";
        numberOfPlayers[2] = "3";
        numberOfPlayers[3] = "4";

        JComboBox comboBox = new JComboBox(numberOfPlayers);






        //Setup for action commands
        rollButton.setActionCommand("R");
        quitButton.setActionCommand("Q");
        playerStatusButton.setActionCommand("D");
        helpButton.setActionCommand("H");
        buyButton.setActionCommand("B");
        passButton.setActionCommand("P");
        comboBox.setActionCommand("C");
        jailButton.setActionCommand("J");


        //Setup for action listeners
        rollButton.addActionListener(controller);
        quitButton.addActionListener(controller);
        playerStatusButton.addActionListener(controller);
        helpButton.addActionListener(controller);
        buyButton.addActionListener(controller);
        passButton.addActionListener(controller);
        comboBox.addActionListener(controller);
        jailButton.addActionListener(controller);


        rollButton.setFont(new Font("Verdana", Font.BOLD, 15));
        quitButton.setFont(new Font("Verdana", Font.BOLD, 15));
        playerStatusButton.setFont(new Font("Verdana", Font.BOLD, 15));
        helpButton.setFont(new Font("Verdana", Font.BOLD, 15));
        buyButton.setFont(new Font("Verdana", Font.BOLD, 15));
        passButton.setFont(new Font("Verdana", Font.BOLD, 15));
        jailButton.setFont(new Font("Verdana", Font.BOLD, 15));

        southPanel.add(rollButton);
        southPanel.add(buyButton);
        southPanel.add(passButton);
        southPanel.add(quitButton);
        southPanel.add(playerStatusButton);
        southPanel.add(helpButton);
        southPanel.add(dropdownMenu);
        southPanel.add(comboBox);
        southPanel.add(jailButton);


        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(imagePanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }


    public JButton getJailButton() {
        return jailButton;
    }




    public static void main(String[] args){
        new MonopolyFrame();
    }
}
