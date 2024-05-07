package project_csc309_spring_2024;


import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Our singleton implementation
 * for storing data of all kinds.
 * 
 * @author Fisher Lyon
 */
public class GameData extends PropertyChangeSupport {
    
    private static GameData instance;

    private List<Block> lockedBlocks;
    private List<Block> unlockedBlocks;
    private Block selectedBlock = null;
    private int mouseXOffset = 20; // half block width
    private int mouseYOffset = 20; // half block height
    private int answerBoxXOffset = 5;
    private int answerBoxYOffset = 5;
    private AnswerBox answerBox;
    private TrashBin trashBin;
    private Tutor tutor;
    private int NUM_LOCKED_BLOCKS = 10;
    private Button pressedButton = null;
    private Level level;
    private LevelPanel levelPanel;

    private GameData() {
        super(new Object());
        lockedBlocks = new ArrayList<Block>();
        unlockedBlocks = new ArrayList<Block>();
        answerBox = new AnswerBox(300, 520);
        trashBin = new TrashBin(520, 510);
        tutor = new Tutor(100, 120);
        level = new Level(0);
        this.recalculate();
    }

    public static GameData getInstance() {
        if (instance == null) {
          instance = new GameData();
        }
        return instance;
    }

    public void recalculate() {
        lockedBlocks.clear();
        unlockedBlocks.clear();
        for (int i = 0; i < NUM_LOCKED_BLOCKS; i++) {
            lockedBlocks.add(new Block(550, (i * 50) + 30, 40, i));
        }
    }

    public void removeUnlockedBlock(Block block){
        unlockedBlocks.remove(block);
    }
    
    public void repaint() {
        firePropertyChange("repaint", null, null);
    }

    public void doButtonAction(JPanel panel, Button button) {

        if (pressedButton == null) {
            setPressedButton(button);
        } else {
            pressedButton.setDefaultColor();
            panel.repaint();
            panel.revalidate();
        }

        setPressedButton(button);
        pressedButton.setPressedColor();
        panel.repaint();
        panel.revalidate();
    }

    public void setMainPanel(JFrame main, JPanel panel) {
        main.getContentPane().removeAll();
        main.getContentPane().add(panel);
    }

    public Block getSelectedBlock() { return selectedBlock; }
    public void setSelectedBlock(Block block) { this.selectedBlock = block; }
    public List<Block> getLockedBlocks() { return lockedBlocks; }
    public List<Block> getUnlockedBlocks() { return unlockedBlocks; }
    public int getMouseXOffset() { return mouseXOffset; }
    public void setMouseXOffset(int x) { this.mouseXOffset = x; }
    public int getMouseYOffset() { return mouseYOffset; }
    public void setMouseYOffset(int y) { this.mouseYOffset = y; }
    public AnswerBox getAnswerBox() { return answerBox; }
    public int getAnswerBoxXOffset() { return answerBoxXOffset; }
    public int getAnswerBoxYOffset() { return answerBoxYOffset; }
    public TrashBin getTrashBin(){return trashBin;}
    public Tutor getTutor() {return tutor;}
    public Button getPressedButton() { return pressedButton; }
    public void setPressedButton(Button button) { pressedButton = button; }
    public Level getLevel() { return level; }
    public void setLevelPanel(LevelPanel l) { levelPanel = l; }   
    public LevelPanel getLevelPanel() { return levelPanel; }
}
