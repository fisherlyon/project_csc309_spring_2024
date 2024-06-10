package project_csc309_spring_2024;

import java.awt.Image;

public class CharacterImages {
    private Image idleImage;
    private Image attackImage;
    private Image hitImage;
    private String characterName;
    public CharacterImages(String characterName, Image idleImage, Image attackImage, Image hitImage) {
        this.characterName = characterName;
        this.idleImage = idleImage;
        this.attackImage = attackImage;
        this.hitImage = hitImage;
    }

    public Image getIdleImage() {
        return idleImage;
    }

    public Image getAttackImage() {
        return attackImage;
    }

    public Image getHitImage() {
        return hitImage;
    }

    public String getCharacterName() {
        return characterName;
    }
}
