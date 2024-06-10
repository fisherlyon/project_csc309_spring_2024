package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A panel that displays the world map with a series
 * of buttons that activate different level previews
 * on LevelPanel.
 * 
 * @author Fisher and Leo
 */
public class MapPanel extends JPanel implements ActionListener {

    private final Image earth; 
    private Button pressedButton = null;
    boolean buttonPressed = false;

    public MapPanel() {
        earth = new ImageIcon(getClass().getResource("/earth.png")).getImage();
        setLayout(null);
        setPreferredSize(new Dimension(earth.getWidth(null), earth.getHeight(null)));
        loadMaps();
    }

    public void loadMaps(){
        HashMap<Button, Character> mapToOperationBindings = new HashMap<Button, Character>();
        mapToOperationBindings.put(addButton("Moon", 70, 40,80, 25), '+');
        mapToOperationBindings.put(addButton("North Pole", 270, 50, 120, 25), '-');
        mapToOperationBindings.put(addButton("CSC 309", 100, 150, 100, 25), '*');
        mapToOperationBindings.put(addButton("Brazil", 230, 300, 90, 25), '/');
        for( Map.Entry<Button, Character> entry : mapToOperationBindings.entrySet()){
            Level level = GameData.getInstance().getLevel();
            Button map = entry.getKey();
            Character linkedOperation = entry.getValue();

            if(level.isLockedOperation(linkedOperation)){
                map.setVisible(false);
            }
        }
    }

    private Button addButton(String text, int x, int y, int width, int height) {
        Button button = new Button(text, x, y, width, height);
        button.addActionListener(this);
        button.setButtonColor(Color.white, Color.black);
        button.setPressedColor(Color.lightGray);
        button.addSelf(this);
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(earth, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            buttonPressed = true;
            Button button = (Button) e.getSource();

            if (pressedButton != null) {
                pressedButton.setToDefaultColor();
            }

            button.setToPressedColor();
            pressedButton = button;

            GameData.getInstance().setSceneSelectionString(button.getLabel());
        }  
    }
}
