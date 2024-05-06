package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;

public class CpuHealth {
    private Image cpuhealthbar;
    private  int x;
    private int y;

    public CpuHealth(int x, int y) {
        this.x = x;
        this.y = y;
        cpuhealthbar = new ImageIcon(getClass().getResource("/cpuhealth.png")).getImage();
    }


    public Image getCpuhealthbar() {
        return cpuhealthbar;
    }
    public int getCpuHealthBarX() {
        return x;
    }

    public int getCpuHealthBarY() {
        return y;
    }
}
