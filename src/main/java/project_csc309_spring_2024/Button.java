package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

/**
 * A simple button class that makes
 * button adding a bit easier.
 * 
 * @author Fisher Lyon
 */
public class Button extends JButton {

    private String label;
    private int x;
    private int y;
    private int w;
    private int h;
    private Color defaultColor;
    private Color pressedColor;

    private Font customFont;

    public Button(String label, int x, int y, int w, int h) {
        this.customFont = GameData.getInstance().getCustomFont();
        this.label = label;
        this.x = x - w / 2; // center
        this.y = y;
        this.w = w;
        this.h = h;
        buttonSetup();
    }

    public void addSelf(JPanel panel) {
        panel.add(this);
    }

    public void setButtonColor(Color bc, Color fc) {
        setFont(customFont.deriveFont(11f));
        setBackground(bc);
        setForeground(fc);
        this.defaultColor = bc;
    }

    public void setPressedColor(Color bc) {
        this.pressedColor = bc;
    }

    public void setToDefaultColor() {
        setBackground(defaultColor);
    }

    public void setToPressedColor() {
        setBackground(pressedColor);
    }

    private void buttonSetup() {
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setText(this.label);
        setBounds(this.x, this.y, this.w, this.h);
        setPreferredSize(new Dimension(this.w, this.h));
    }

    public String getLabel() { return this.label; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getW() { return this.w; }  
    public int getH() { return this.h; }
}
