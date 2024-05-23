package project_csc309_spring_2024.multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Stack;

/**
 * Handles the network communication
 * between two players for a duel.
 * 
 * @author Eric Berber
 * 
 */
public class NetworkDriver implements ServerListener, Runnable {
    private Socket player1;
    private Socket player2;
    private Server server;
    public NetworkDriver(Server server) {
        this.server = server;
    }

    @Override
    public void onClientConnection(ServerEvent event) {
        Stack<Socket> sockets = server.getPool();
        if(sockets.size() >= 2){
            Socket player1 = sockets.pop();
            Socket player2 = sockets.pop();
            networkDuel(player1, player2);
        }
    }

    private void networkDuel(Socket player1, Socket player2) {
        Thread thread = new Thread(this);
        this.player1 = player1;
        this.player2 = player2;
        thread.run();
    }

    // Network Duel handler 
    @Override
    public void run() {
        try {
            DataInputStream player1InputStream = new DataInputStream(player1.getInputStream());
            DataInputStream player2InputStream = new DataInputStream(player2.getInputStream());

            DataOutputStream player1OutputStream = new DataOutputStream(player1.getOutputStream());
            DataOutputStream player2OutputStream = new DataOutputStream(player2.getOutputStream());

            ServerEvent duelIntiateEvent = new ServerEvent(ServerEvent.DUEL_INTIATED, 0);

            // Notify clients;
            player1OutputStream.writeUTF(duelIntiateEvent.toString());
            player2OutputStream.writeUTF(duelIntiateEvent.toString());

            while (!player1.isClosed() && !player2.isClosed()) {
                handlePlayerInput(player1InputStream, player1OutputStream, player2OutputStream);
                handlePlayerInput(player2InputStream, player2OutputStream, player1OutputStream);
            }

            //Clean up sockets
            server.closeConnection(player1);
            server.closeConnection(player2);
        } catch (IOException e) {
            e.getStackTrace();
            System.out.println("Failed to connect with clients.");
        }
    }

    private void handlePlayerInput(DataInputStream playerInputStream, DataOutputStream playerOutputStream,
            DataOutputStream opponentOutputStream) throws IOException {
        if (playerInputStream.available() > 0) {
            ServerEvent event = new ServerEvent(playerInputStream.readUTF());
            opponentOutputStream.writeUTF(event.toString());
        }
    }

}
