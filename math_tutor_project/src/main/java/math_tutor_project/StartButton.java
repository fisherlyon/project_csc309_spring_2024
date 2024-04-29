package math_tutor_project;

import javax.swing.*;

public class StartButton extends JButton {

    private String option;
    private int x;
    private int y;
    private int width;
    private int height;

    public StartButton(String option, int x, int y, int width, int height) {
        this.option = option;
        this.x = x - width / 2;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setText(option);
    }

    public void setScreen() {
        GameData.getInstance().setDisplayScreen(option);
    }

    public String getOption() { return option; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
