package math_tutor_project;

public class CpuPlayer {
    
    private int health;
    private String name = "CPU";

    public CpuPlayer(int health) {
        this.health = health;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
}
