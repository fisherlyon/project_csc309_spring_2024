package project_csc309_spring_2024;


import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * Our singleton implementation
 * for storing data of all kinds.
 * 
 * @author Fisher Lyon
 */
public class GameData extends PropertyChangeSupport {
    
    private static GameData instance;
    private JFrame mainFrame = null;

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
    private Level level;
    private String operationString;
    private String gameMode;
    private int timeAttackScore;

    private Font customFont;
    private AudioPlayer audioPlayer;
    private Music music;

    private CpuPlayer cpuPlayer;


    private GameData() {
        super(new Object());
        lockedBlocks = new ArrayList<Block>();
        unlockedBlocks = new ArrayList<Block>();
        music = new Music();
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
        answerBox = new AnswerBox(300, 520);
        answerBox.setBoxColor(Color.white);
        trashBin = new TrashBin(520, 510);
        tutor = new Tutor(50, 50);
    }

    public void setCharacter(String character) {
        firePropertyChange("characterSelected", null, character);
    }

    public void setCpuPlayer(CpuPlayer cpuPlayer) {
        this.cpuPlayer = cpuPlayer;
    }

    public CpuPlayer getCpuPlayer() {
        return cpuPlayer;
    }
    public MusicManager getMusicManager() {
        return music.getMusicManager();
    }

    public void removeUnlockedBlock(Block block){
        unlockedBlocks.remove(block);
    }
    
    public void repaint() {
        firePropertyChange("repaint", null, null);
    }

    public void setSceneSelectionString(String str) {
        firePropertyChange("sceneButtonPressed", null, str);
    }

    public void setGameOver(boolean isWinner) {
        firePropertyChange("gameOver", null, isWinner);
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        firePropertyChange("correctAnswer", null, correctAnswer);
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
    public Level getLevel() { return level; }
    public void setLevel(Level level) {  this.level = level; }
    public String getOperationString() { return operationString; }
    public void setOperationString(String str) { operationString = str; }
    public String getGameMode() { return gameMode; }
    public void setGameMode(String mode) { gameMode = mode; }
    public int getTimeAttackScore() { return timeAttackScore; }
    public void setTimeAttackScore(int score) { timeAttackScore = score; }

    public Font getCustomFont() { return customFont; }
    public void setCustomFont(Font font) { this.customFont = font; }

    public AudioPlayer getAudioPlayer() { return audioPlayer; }
    public void setAudioPlayer(AudioPlayer player) { this.audioPlayer = player; }

    public JFrame getMainFrame() { return mainFrame; }
    public void setMainFrame(JFrame frame) { mainFrame = frame; }

}
