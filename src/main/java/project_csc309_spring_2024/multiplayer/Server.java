package project_csc309_spring_2024.multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Eric Berber
 */
public class Server {

    final private int PORT;
    Stack<Socket> pool;
    ArrayList<ServerListener> serverLisenters;

    public Server(int PORT) {
        this.PORT = PORT;
        this.serverLisenters = new ArrayList<>();
        this.pool = new Stack<>();
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            // Look for incoming connections
            while (true) {
                Socket clientSocket;
                clientSocket = serverSocket.accept();
                System.out.println("Got Connection...");
                pool.add(clientSocket);

                ServerEvent clientConnectionEvent = new ServerEvent(ServerEvent.CLIENT_CONNECTED, 1234);
                notifyAllLisenters(clientConnectionEvent);
            }
        } catch (IOException e) {
            System.out.println("Failed to start server: " + e.getMessage());
        }

    }

    public void notifyAllLisenters(ServerEvent event) {
        for (ServerListener serverLisenter : serverLisenters) {
            serverLisenter.onClientConnection(event);
        }
    }

    public void addServerListener(ServerListener serverLisenter) {
        serverLisenters.add(serverLisenter);
    }

    public Stack<Socket> getPool() {
        return pool;
    }
}