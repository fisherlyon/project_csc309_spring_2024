package project_csc309_spring_2024;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ...
 *
 * @author Leo Rivera
 */
public class UserPlayer extends Player{
    private Image currentImage;
    private String name;
    private int exp;
    private int userLevel;
    private boolean isLocalPlayer;
    private Image bobbyIdle = new ImageIcon(getClass().getResource("/bobby.png")).getImage();
    private Image bobbyHit = new ImageIcon(getClass().getResource("/bobbydamaged.png")).getImage();
    private Image bobbyAttack = new ImageIcon(getClass().getResource("/bobbyattack.png")).getImage();
    public UserPlayer(int x, int y, String name, int health) {
        super(x,y, health );
        this.name = name;
        this.exp = 0;
        this.userLevel = 0;
        currentImage = bobbyIdle;
        
    }
    public Boolean gainExp(int expToGain){
        exp += expToGain;
        if(exp >= 100){
            this.userLevel ++;
            this.exp = 0;
            return true;
        }
        return false;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isLocalPlayer() {
        return isLocalPlayer;
    }
    public void setLocalPlayer(){
        isLocalPlayer = true; 
    }
    public Image getCurrentImage(){
        return currentImage;
    }

    public void attackAnimation(){
        currentImage = bobbyAttack;
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentImage = bobbyIdle;
                }

            });

            timer.setRepeats(false);
            timer.start();

        repaint();
    }

    public void damageAnimation(){
        currentImage = bobbyHit;
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentImage =bobbyIdle;
                }

            });

            timer.setRepeats(false);
            timer.start();

        repaint();
    }
}
