package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * The controller of all events that 
 * take place in main.
 *
 * @author Fisher, Leo, Eric
 */
public class GameController implements MouseListener, MouseMotionListener {
    GameData data = GameData.getInstance();
    private UserPlayer userPlayer;
    private CpuPlayer cpuPlayer;
    private Image bobbyhit = new ImageIcon(getClass().getResource("/bobbydamaged.png")).getImage();
    private Image bobbyattack = new ImageIcon(getClass().getResource("/bobbyattack.png")).getImage();
    private Image grampshit = new ImageIcon(getClass().getResource("/grampsdamaged.png")).getImage();
    private Image grampsattack = new ImageIcon(getClass().getResource("/grampsattack.png")).getImage();
    private MergeMath mm = new MergeMath();

    public GameController(UserPlayer userPlayer, CpuPlayer cpuPlayer) {
        this.userPlayer = userPlayer;
        this.cpuPlayer = cpuPlayer;
    }
    @Override
    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < data.getLockedBlocks().size(); i++) {
            Block block = data.getLockedBlocks().get(i);
            if (block.contains(e.getX(), e.getY())) {
                Block unlockedBlock = new Block(block.getBlockX(), block.getBlockY(), block.getDim(), block.getValue());
                unlockedBlock.setSelected(true);
                data.getUnlockedBlocks().add(unlockedBlock);
                data.setSelectedBlock(unlockedBlock);
                break;
            }
        }

        for (int i = 0; i < data.getUnlockedBlocks().size(); i++) {
            Block block = data.getUnlockedBlocks().get(i);
            if (block.contains(e.getX(), e.getY())) {
                data.setSelectedBlock(block);
                block.setSelected(true);
                break;
            }
        }
    }

    // MERGE!
    @Override
    public void mouseReleased(MouseEvent e) {

        if (data.getSelectedBlock() != null) {
            Block selectedBlock = data.getSelectedBlock();
            boolean merged = false;

            for (Block block : data.getUnlockedBlocks()) {
                if (!block.isSelected() && block.contains(e.getX(), e.getY())) {
                    String operationString = GameData.getInstance().getOperationString();
                    if (operationString != null) {
                        int mergeVal;
                        try {
                            mergeVal = mm.doOperation(operationString, block.getValue(), selectedBlock.getValue());
                        } catch (ArithmeticException er) {
                            System.out.println("div by zero");
                            break;
                        }
                        Block mergedBlock = new Block(block.getBlockX() + data.getMouseXOffset(), block.getBlockY() + data.getMouseYOffset(), block.getDim(), mergeVal);
                        data.getUnlockedBlocks().add(mergedBlock);
                        data.getUnlockedBlocks().remove(selectedBlock);
                        data.getUnlockedBlocks().remove(block);
                        merged = true;
                    }

                    break;
                }
            }

            if (!merged) {
                selectedBlock.setBlockX(e.getX() - data.getMouseXOffset());
                selectedBlock.setBlockY(e.getY() - data.getMouseYOffset());
            }

            TrashBin trashBin = data.getTrashBin();
            if(trashBin.contains(e.getX(), e.getY())){
                // Data change runs repaint
                data.removeUnlockedBlock(selectedBlock);
            }

            AnswerBox answerBox = data.getAnswerBox();
            if (answerBox.contains(e.getX(), e.getY())) {
                selectedBlock.setBlockX(answerBox.getX() + data.getAnswerBoxXOffset());
                selectedBlock.setBlockY(answerBox.getY() + data.getAnswerBoxYOffset());
                selectedBlock.setAnswer(true);
                answerBox.setAnswerBlock(selectedBlock);
                answerBox.setFilled(true);

                int targetAnswer = data.getLevel().getTarget();
                Tutor  tutor = data.getTutor();
                Level level = data.getLevel();

                if (selectedBlock.getValue() != targetAnswer){
                    if (data.getGameMode().equals("Time Attack"))
                        data.setCorrectAnswer(false);
                    tutor.help();
                    Duel d = level.getDuel();

                    if (!(data.getGameMode().equals("Time Attack")))
                        d.castAttack(d.getPlayer2(), d.getPlayer1());

                    answerBox.setBoxColor(Color.red);

                    Image tempGramps = cpuPlayer.getPlayerTwo();
                    cpuPlayer.setPlayerTwo(grampsattack);
                    Timer timer2 = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cpuPlayer.setPlayerTwo(tempGramps);
                        }
                    });

                    timer2.setRepeats(false);
                    timer2.start();


                    Image temp = userPlayer.getPlayerOne();
                    userPlayer.setPlayerOne(bobbyhit);
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            userPlayer.setPlayerOne(temp);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();

                }
                else  {
                    if (data.getGameMode().equals("Time Attack"))
                        data.setCorrectAnswer(true);
                    level.nextProblem();
                    tutor.setVisible(false);

                    Duel d = level.getDuel();
                    if (!(data.getGameMode().equals("Time Attack")))
                        d.castAttack(d.getPlayer1(), d.getPlayer2());
                        
                    answerBox.setBoxColor(Color.green);
                    GameData.getInstance().getUnlockedBlocks().remove(selectedBlock);
                    answerBox.setAnswerBlock(null);
                    answerBox.setFilled(false);
                    // need to get minus 10 here for cpu

                    Image tempBobby = userPlayer.getPlayerOne();
                    userPlayer.setPlayerOne(bobbyattack);
                    Timer timer2 = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            userPlayer.setPlayerOne(tempBobby);
                        }
                    });

                    timer2.setRepeats(false);
                    timer2.start();



                    Image tempGramps = cpuPlayer.getPlayerTwo();
                    cpuPlayer.setPlayerTwo(grampshit);
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cpuPlayer.setPlayerTwo(tempGramps);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();



                }
            }

            if (!answerBox.contains(selectedBlock.getBlockX(), selectedBlock.getBlockY())) {
                selectedBlock.setAnswer(false);
                answerBox.setAnswerBlock(null);
                answerBox.setFilled(false);
                answerBox.setBoxColor(Color.white);
            }

            data.getSelectedBlock().setSelected(false);
            data.setSelectedBlock(null);
            data.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (data.getSelectedBlock() != null) {
            data.getSelectedBlock().setBlockX(e.getX() - data.getMouseXOffset());
            data.getSelectedBlock().setBlockY(e.getY() - data.getMouseYOffset());
            data.repaint();
        }
    }

    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}