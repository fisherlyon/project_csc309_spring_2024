package project_csc309_spring_2024;

import javax.swing.JComponent;

/**
 * Abstract class for working with Duels
 * @author Eric Berber 
 */
public abstract class Player extends JComponent {
    private int x;
    private int y;
    private int health;
    public Player(int x, int y, int health){
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getplayerX() { return x; }
    public int getplayerY() { return y; }
}
