package math_tutor_project;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * ...
 * 
 * @author Fisher Lyon
 */
public class GameData extends PropertyChangeSupport {
    
    private static GameData instance;

    private List<Block> lockedBlocks;
    private List<Block> unlockedBlocks;
    private Block selectedBlock = null;
    private int mouseXOffset = 25; // half block width
    private int mouseYOffset = 25; // half block height

    private int NUM_LOCKED_BLOCKS = 10;

    private GameData() {
        super(new Object());
        lockedBlocks = new ArrayList<Block>();
        unlockedBlocks = new ArrayList<Block>();
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

    public void repaint() {
        firePropertyChange("repaint", null, null);
    }

    public Block getSelectedBlock() { return selectedBlock; }
    public void setSelectedBlock(Block block) { this.selectedBlock = block; }
    public List<Block> getLockedBlocks() { return lockedBlocks; }
    public List<Block> getUnlockedBlocks() { return unlockedBlocks; }
    public int getMouseXOffset() { return mouseXOffset; }
    public void setMouseXOffset(int x) { this.mouseXOffset = x; }
    public int getMouseYOffset() { return mouseYOffset; }
    public void setMouseYOffset(int y) { this.mouseYOffset = y; }
}
