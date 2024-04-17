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

    private List<NumBlock> numBlocks;
    private List<NumBlock> freeBlocks;
    private NumBlock selectedBlock = null;
    private int mouseXOffset = 25; // half block width
    private int mouseYOffset = 25; // half block height

    private int NUM_OF_BLOCKS = 10;

    private GameData() {
        super(new Object());
        numBlocks = new ArrayList<NumBlock>();
        freeBlocks = new ArrayList<NumBlock>();
        this.recalculate();
    }

    public static GameData getInstance() {
        if (instance == null) {
          instance = new GameData();
        }
        return instance;
    }

    public void recalculate() {
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            numBlocks.add(new NumBlock(1400, (i * 60) + 50, 50, i));
        }
    }

    public void repaint() {
        firePropertyChange("repaint", null, null);
    }

    public NumBlock getSelectedBlock() { return selectedBlock; }
    public void setSelectedBlock(NumBlock block) { this.selectedBlock = block; }
    public List<NumBlock> getNumBlocks() { return numBlocks; }
    public List<NumBlock> getFreeBlocks() { return freeBlocks; }
    public int getMouseXOffset() { return mouseXOffset; }
    public void setMouseXOffset(int x) { this.mouseXOffset = x; }
    public int getMouseYOffset() { return mouseYOffset; }
    public void setMouseYOffset(int y) { this.mouseYOffset = y; }
}
