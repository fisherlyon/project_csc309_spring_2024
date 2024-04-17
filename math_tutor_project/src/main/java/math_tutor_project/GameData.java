package math_tutor_project;

import java.beans.PropertyChangeSupport;


/**
 * ...
 * 
 * @author Fisher Lyon
 */
public class GameData extends PropertyChangeSupport {
    
    private static GameData instance;

    private NumBlock selectedBlock = null;

    private GameData() {
        super(new Object());
    }

    public static GameData getInstance() {
        if (instance == null) {
          instance = new GameData();
        }
        return instance;
    }

    public void repaint() {
        firePropertyChange("repaint", null, null);
    }

    public NumBlock getSelectedBlock() { return selectedBlock; }
    public void setSelectedBlock(NumBlock block) { this.selectedBlock = block; }
}
