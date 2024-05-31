package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CpuPlayer extends Player {
    private Image currentImage;
    private String name = "CPU";
    private CharacterImages characterImages;

    public CpuPlayer(int x, int y, int health, CharacterImages characterImages) {
        super(x, y, health);
        this.characterImages = characterImages;
        this.currentImage = characterImages.getIdleImage();
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public String getName() {
        return name;
    }

    public void setCharacterImages(CharacterImages characterImages) {
        this.characterImages = characterImages;
        this.currentImage = characterImages.getIdleImage();
        repaint();
    }

    public void attackAnimation() {
        currentImage = characterImages.getAttackImage();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImage = characterImages.getIdleImage();
            }
        });
        timer.setRepeats(false);
        timer.start();
        repaint();
    }

    public void damageAnimation() {
        currentImage = characterImages.getHitImage();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImage = characterImages.getIdleImage();
            }
        });
        timer.setRepeats(false);
        timer.start();
        repaint();
    }
}
