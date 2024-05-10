package project_csc309_spring_2024;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.Timer;

import java.lang.Integer;
import java.lang.reflect.Array;

/**
 * 
 * @author Eric Berber
 */
public class Tutor extends JComponent implements KeyEventDispatcher {

    public int TUTOR_BOX_HEIGHT = 150;
    public int TUTOR_BOX_WIDTH = 350;
    private List<String> messages;
    private int msgIndex = 0;
    private String currentMessage = "";
    private int charIndex = 0;
    private Timer typewriterTimer;
    private boolean blink = true;
    private List<Expression> guide;
    private int guideIndex;
    private boolean printing;

    public Tutor(int x, int y) {
        setLocation(x, y);
        setSize(500, 500);
        setVisible(false);
        setFocusable(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
        typewriterTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < getCurrentMessage().length()) {
                    printing = true;
                    currentMessage = getCurrentMessage().substring(0, charIndex + 1);
                    charIndex++;
                    repaint();
                } else {
                    printing = false;
                    repaint();
                }
            }
        });

    }

    private int distance(int a, int b) {
        return Math.abs(a - b);
    }

    private boolean isPrinting() {
        return printing;
    }

    private String getCarot() {
        if (blink) {
            blink = false;
            return "▋";
        }
        blink = true;
        return " ";
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
        msgIndex = 0;
    }

    public void setGuide(List<Expression> guide) {
        this.guide = guide;
        guideIndex = 0;
    }

    public String getCurrentMessage() {
        return messages.get(msgIndex);
    }

    public void nextMessage() {
        if (guide != null) {
            guideIndex++;
        }

        if (msgIndex < messages.size() - 1) {
            msgIndex++;
            charIndex = 0;
            typewriterTimer.restart();
            repaint();
        } else {
            setVisible(false);
        }
    }

    private int operate(int term1, int term2, char operator) {
        switch (operator) {
            case '+':
                term1 += term2;
                break;
            case '-':
                term1 -= term2;
                break;
            case '*':
                term1 *= term2;
                break;
            case '/':
                term1 /= term2;
            default:
                break;
        }
        return term1;
    }

    /*
     * 
     * So we use this function to determine what operator we use for
     * also based on level difficulty
     */
    public char getGuideOperator(int target, int current) {

        if (target < current && distance(target, current) <= 15) {
            return '-';
        } else if (target > current && distance(target, current) > 15 && (target * current > 0)) {
            return '*';
        } else if (target < current && distance(target, current) > 15 && (target * current > 0)) {
            return '/';
        }
        return '+';
    }

    public List<String> getMessageGuide(int amount) {

        List<String> pool = new ArrayList<String>(Arrays.asList(
                "After that...",
                "Next...",
                "Continuing...",
                "Try this..."));

        List<String> messages = new ArrayList<String>();
        Random rand = new Random();
        for (int i = 0; i < amount; i++) {
            int msgIndex = rand.nextInt(0, pool.size());
            String messageToAppend = pool.get(msgIndex);

            if (i == 0) {
                messageToAppend = "Nice try! " + messageToAppend;
            }
            messages.add(messageToAppend);
        }

        return messages;
    }

    private List<Expression> getProblemGuide(int target) {
        List<Expression> solution = new ArrayList<Expression>();
        GameData data = GameData.getInstance();

        List<Block> blocks = data.getUnlockedBlocks();
        Comparator<Block> distanceComparator = (a, b) -> Math.min(Math.abs(target - a.getValue()),
                Math.abs(target - b.getValue()));
        blocks.sort(distanceComparator);
        int current = 0;

        for (Block block : blocks) {
            if (block.getValue() == target) {
                Expression expression = new Expression(block.getValue(), 0, block.getValue(), '+');
                solution.add(expression);
                return solution;
            }

            if (distance(target, current) >= distance(target, blocks.get(0).getValue())) {
                current = blocks.get(0).getValue();
                continue;
            }

            int operated = current;
            char operator = getGuideOperator(target, current);
            operated = operate(operated, block.getValue(), operator);

            if (distance(target, operated) < distance(target, current)) {
                Expression expression = new Expression(current, block.getValue(), operated, operator);
                solution.add(expression);
                current = operated;
            }
        }

        while (current != target) {
            char operator = getGuideOperator(target, current);
            int minDistance = Integer.MAX_VALUE;
            int numToOperate = -1;

            for (int i = 0; i <= 9; i++) {
                int operated = current;
                operated = operate(operated, i, operator);
                if (distance(target, operated) < minDistance) {
                    numToOperate = i;
                    minDistance = distance(target, operated);
                }
            }

            Expression expression = new Expression(current, numToOperate, operate(current, numToOperate, operator),
                    operator);
            solution.add(expression);
            current = operate(current, numToOperate, operator);
        }

        return solution;

    }

    public void help() {

        int problem = GameData.getInstance().getLevel().getTarget();
        List<Expression> guide = getProblemGuide(problem);
        List<String> messages = getMessageGuide(guide.size());

        setMessages(messages);
        setGuide(guide);
        guideIndex = 0;
        typewriterTimer.restart();
        requestFocus();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isVisible() && messages != null) {
            g.drawRect(getX(), getY(), TUTOR_BOX_WIDTH, TUTOR_BOX_HEIGHT);
            g.setFont(new Font("Ariel", Font.PLAIN, 20));
            g.drawString(currentMessage + getCarot(), getX() + 10, getY() + 50);

            if (guide != null && !isPrinting()) {
                g.drawString(guide.get(guideIndex).toString(), getX() + 10, getY() + 80);
            }

            g.setFont(new Font("Ariel", Font.PLAIN, 10));
            g.drawString((msgIndex + 1) + " of " + messages.size(), getX() + TUTOR_BOX_WIDTH - 50,
                    getY() + TUTOR_BOX_HEIGHT - 10);
            g.drawString("Press any button", getX() + 10, getY() + TUTOR_BOX_HEIGHT - 10);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (isVisible()) {
            nextMessage();
        }
        return false;
    }
}
