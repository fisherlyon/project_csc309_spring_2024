package project_csc309_spring_2024;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import project_csc309_spring_2024.multiplayer.*;

/**
 * Game client for interaction with server
 * 
 * @author Eric Berber
 * 
 */
public class Client extends Thread implements DuelListener {
    private Socket socket;
    public DataOutputStream writer;
    public DataInputStream reader;
    public Level level;
    public String host;
    public int port;
    public UserPlayer userPlayer;
    public UserPlayer networkPlayer;
    public boolean isReady = false;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // Start network duel
    @Override
    public void run() {

        findNetworkPlayer();

        userPlayer = new UserPlayer(0, 0, "Local Player", 100);
        networkPlayer = new UserPlayer(0, 0, "Network Player", 100);
        userPlayer.setLocalPlayer();

        Duel networkDuel = new Duel(userPlayer, networkPlayer);
        networkDuel.addDuelListener(this);
        level = new Level(networkDuel, Duel.DEFAULT_NETWORK_DUEL_DIFFICULTY);
        GameData.getInstance().setLevel(
                level);

        isReady = true;
        while (socket.isConnected() && isReady) {
            try {
                if (reader.available() > 0) {
                    ServerEvent serverResponse = new ServerEvent(reader.readUTF());

                    if (serverResponse.getType() == ServerEvent.RECALCULATE) {
                        networkDuel.castAttack(networkPlayer, userPlayer);

                        level.setTarget(Integer.valueOf(serverResponse.getPayload()));
                        GameData.getInstance().repaint();
                    }
                }
            } catch (IOException e) {
                e.getStackTrace();
                System.out.println("Failed to read server data.");
            }
        }

    }

    public void findNetworkPlayer() {
        try {
            this.socket = new Socket(host, port);
            System.out.println("Client connected to server..");

            writer = new DataOutputStream(socket.getOutputStream());
            reader = new DataInputStream(socket.getInputStream());

            System.out.println("Waiting for another player to join...");
            while (socket.isConnected()) {
                if (reader.available() > 0) {
                    String serverResponse = reader.readUTF();
                    ServerEvent event = new ServerEvent(serverResponse);

                    if (event.getType() == ServerEvent.DUEL_INTIATED) {
                        System.out.println("Found player duel intiated... ");
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.getStackTrace();
            System.out.println("Failed to find battle.");
        }
    }

    @Override
    public void onPlayerAttack(Player attacker, Player attacked) {
        if (socket.isConnected()) {
            try {
                // Network Duel so we can safely cast to UserPlayer
                if (((UserPlayer) attacker).isLocalPlayer()) {
                    level.nextProblem();
                    int updatedTarget = level.getTarget();
                    ServerEvent recalculateEvent = new ServerEvent(ServerEvent.RECALCULATE, 1,
                            Integer.toString(updatedTarget));
                    writer.writeUTF(recalculateEvent.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDuelEnd(Player winner, Player loser) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isReady = true;
        System.exit(0);
    }

    public boolean isReady() {
        return isReady;
    }

}
