package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        addButton("Moon", 70, 40,80, 25);
        addButton("North Pole", 270, 50, 110, 25);
        addButton("CSC 309", 100, 150, 100, 25);
        addButton("Brazil", 230, 300, 80, 25);
    }

    private void addButton(String text, int x, int y, int width, int height) {
        Button button = new Button(text, x, y, width, height);
        button.addActionListener(this);
        button.setButtonColor(Color.white, Color.black);
        button.setPressedColor(Color.lightGray);
        button.addSelf(this);
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
