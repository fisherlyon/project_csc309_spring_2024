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
            DataInputStream client1DataInputStream = new DataInputStream(player1.getInputStream());
            DataInputStream client2DataInputStream = new DataInputStream(player2.getInputStream());

            DataOutputStream client1PrintWriter = new DataOutputStream(player1.getOutputStream());
            DataOutputStream client2PrintWriter = new DataOutputStream(player2.getOutputStream());

            ServerEvent duelIntiateEvent = new ServerEvent(ServerEvent.DUEL_INTIATED, 0);

            //Notify clients;
            client1PrintWriter.writeUTF(duelIntiateEvent.toString());
            client2PrintWriter.writeUTF(duelIntiateEvent.toString());

            while (player1.isConnected() && player2.isConnected()) {

                if (client1DataInputStream.available() > 0) {
                    ServerEvent event = new ServerEvent(client1DataInputStream.readUTF());

                    client2PrintWriter.writeUTF(event.toString());
                }
                if (client2DataInputStream.available() > 0) {
                    ServerEvent event = new ServerEvent(client2DataInputStream.readUTF());

                    client1PrintWriter.writeUTF(event.toString());
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
            System.out.println("Failed to connect with clients.");
        }
    }

}
