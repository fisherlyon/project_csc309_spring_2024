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
public class CpuPlayer extends Player{
    private Image currentImage;
    private String name = "CPU";
    private Image grampsHit = new ImageIcon(getClass().getResource("/grampsdamaged.png")).getImage();
    private Image grampsAttack = new ImageIcon(getClass().getResource("/grampsattack.png")).getImage();
    private Image grampsIdle = new ImageIcon(getClass().getResource("/gramps.png")).getImage();
    public CpuPlayer(int x, int y, int health) {
        super(x, y, health);
        currentImage = grampsIdle;
    }


    public Image getCurrentImage(){
        return currentImage;
    }

    public String getName() { return name; }


    public void attackAnimation(){
        currentImage = grampsAttack;
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   currentImage = grampsIdle;
                }
            });
            timer.setRepeats(false);
            timer.start();

        repaint();
    }
    public void damageAnimation(){
        currentImage = grampsHit;
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   currentImage = grampsIdle;
                }
            });
            timer.setRepeats(false);
            timer.start();
        repaint();
    }
}
