package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

public class PlayerHealth {
    private Image playerhealthbar;
    private  int x;
    private int y;

    public PlayerHealth(int x, int y) {
        this.x = x;
        this.y = y;
        playerhealthbar = new ImageIcon(getClass().getResource("/userhealth.png")).getImage();
    }

    public Image getPlayerhealthbar() {
        return playerhealthbar;
    }

    public int getUserHealthBarX() {
        return x;
    }

    public int getUserHealthBarY() {
        return y;
    }
}
