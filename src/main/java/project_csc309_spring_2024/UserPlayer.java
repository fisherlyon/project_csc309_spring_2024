package project_csc309_spring_2024;


import javax.swing.*;
import java.awt.*;

/**
 * ...
 *
 * @author Leo Rivera
 */
public class UserPlayer {
    private Image playerone;
    private String name;
    private int health;
    private  int x;
    private int y;


    public UserPlayer(int x, int y, String name, int health) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.health = health;
        playerone = new ImageIcon(getClass().getResource("/bobby.png")).getImage();

    }

    public int getplayerX() { return x; }
    public int getplayerY() { return y; }
    public Image getImage() { return playerone; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPlayerHealth() { return health; }
    public void setPlayerHealth(int health) { this.health = health; }
}
