package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CharacterManager {
    private Map<String, CharacterImages> characters = new HashMap<>();

    public CharacterManager() {
        initializeCharacters();
    }

    private void initializeCharacters() {
        characters.put("gramps", new CharacterImages(
                new ImageIcon(getClass().getResource("/gramps.png")).getImage(),
                new ImageIcon(getClass().getResource("/grampsattack.png")).getImage(),
                new ImageIcon(getClass().getResource("/grampsdamaged.png")).getImage()
        ));

        characters.put("alien", new CharacterImages(
                new ImageIcon(getClass().getResource("/alien.png")).getImage(),
                new ImageIcon(getClass().getResource("/alien_attack.png")).getImage(),
                new ImageIcon(getClass().getResource("/alien_damaged.png")).getImage()
        ));

        characters.put("elf", new CharacterImages(
                new ImageIcon(getClass().getResource("/elf.png")).getImage(),
                new ImageIcon(getClass().getResource("/elf_attack.png")).getImage(),
                new ImageIcon(getClass().getResource("/elf_damaged.png")).getImage()
        ));

        characters.put("jgs", new CharacterImages(
                new ImageIcon(getClass().getResource("/jgs.png")).getImage(),
                new ImageIcon(getClass().getResource("/jgsattack.png")).getImage(),
                new ImageIcon(getClass().getResource("/jgs_damaged.png")).getImage()
        ));




    }

    public CharacterImages getCharacterImages(String characterName) {
        return characters.get(characterName);
    }
}
