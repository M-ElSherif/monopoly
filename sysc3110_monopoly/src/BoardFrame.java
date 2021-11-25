import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BoardFrame extends JFrame {

    String path = "Models.Board.json";

    public BoardFrame() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray tiles = jsonObject.getJSONArray("tiles");

            for (int k = 0; k < tiles.length(); k++) {
                JSONObject obj = (JSONObject) tiles.get(k);

                String type = (String) obj.get("type");
                String name = (String) obj.get("name");
                //String color = (String) obj.get("color");
                //Integer price = (Integer) obj.get("price");

                JLabel tileName = new JLabel();
                tileName.setFont(new Font("Verdana",Font.BOLD,10));

                //Setup the Layout
                JPanel boardPanel = new JPanel();
                GridBagLayout boardLayout = new GridBagLayout();
                boardLayout.rowWeights = new double[]{0.2, 0.1, 0.1, 0.1, 0.1,
                        0.1, 0.1, 0.1, 0.1, 0.1, 0.2};
                boardLayout.columnWeights = new double[]{0.2, 0.1, 0.1, 0.1, 0.1,
                        0.1, 0.1, 0.1, 0.1, 0.1, 0.2};
                boardPanel.setLayout(boardLayout);

                //Default Grid values
                int gridX = 0;
                int gridY = 0;
                //Add Panels for Each of the four sides
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 11; i++) {
                        JPanel tempPanel = new JPanel();
                        switch (j) {
                            case 0://Top Spaces
                                gridX = i;
                                gridY = 0;
                                break;
                            case 1://Left Spaces
                                gridX = 0;
                                gridY = i;
                                break;
                            case 2://Right Spaces
                                gridX = 10;
                                gridY = i;
                                break;
                            case 3://Bottom Spaces
                                gridX = i;
                                gridY = 10;
                                break;
                        }

                        /*
                        if(type == "property"){
                            tileName = new JLabel(name);
                            tempPanel.add(tileName);
                        }
                         */

                        boardPanel.add(tempPanel,
                                new GridBagConstraints(gridX,// XGridSpot
                                        gridY,// YGridSpot
                                        1,// XGridSpaces
                                        1,// YGridSpaces
                                        0.0, 0.0, GridBagConstraints.CENTER,
                                        GridBagConstraints.BOTH,//Fill
                                        new Insets(0, 0, 0, 0), 0, 0));
                        tempPanel.setBorder(BorderFactory
                                .createLineBorder(Color.BLACK));
                    }
                }

                {// Main Inner Area Notice Starts at (1,1) and takes up 11x11
                    JPanel innerPanel = new JPanel();
                    boardPanel.add(
                            innerPanel,
                            new GridBagConstraints(1,
                                    1,
                                    11,
                                    11,
                                    0.0, 0.0,
                                    GridBagConstraints.CENTER,
                                    GridBagConstraints.BOTH,
                                    new Insets(0, 0, 0, 0), 0, 0));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
