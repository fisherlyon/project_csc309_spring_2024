package project_csc309_spring_2024.multiplayer;


/**
 * @author Eric Berber
 */
public class ServerEvent {
    public static int CLIENT_CONNECTED = 0;
    public static int DUEL_INTIATED = 1;
    public static int ENEMY_ATTACK = 2;
    public static int TARGET_CHANGE = 3;
    public static int RECALCULATE = 4;
    public static int TERMINATE = 5;
    private int type;
    private int id;
    private String payload;
    public ServerEvent(int type, int id, String payload) {
        this.id = id;
        this.type = type;
        this.payload = payload;
    }

    public ServerEvent(int type, int id) {
        this(type, id, "");
    }

    public ServerEvent(String res) {
        String[] tokens = res.split(":");

        this.type = Integer.valueOf(tokens[0]);
        this.id = Integer.valueOf(tokens[1]);
        if (tokens.length > 2) {
            this.payload = tokens[2];
        }
    }

    @Override
    public String toString() {
        return Integer.toString(type) + ":" + Integer.toString(id) + ":" + payload;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }
}
