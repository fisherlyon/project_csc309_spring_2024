package project_csc309_spring_2024.multiplayer;

/**
 * @author Eric Berber
 */
public class ServerMain {
    public static void main(String[] args) {
        int PORT = 5555; 
        Server server = new Server(PORT);
 
        System.out.println("Starting server...");
        NetworkDriver networkDriver = new NetworkDriver(server);
        server.addServerListener(networkDriver);
        server.run();
   } 
}