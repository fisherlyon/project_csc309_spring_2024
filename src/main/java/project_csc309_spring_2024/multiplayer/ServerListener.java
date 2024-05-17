package project_csc309_spring_2024.multiplayer;

/**
 * @author Eric Berber
 */
public interface ServerListener {
    public void onClientConnection(ServerEvent event);
}
