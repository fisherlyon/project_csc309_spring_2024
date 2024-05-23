package project_csc309_spring_2024;

import java.io.IOException;
import java.net.Socket;
import org.junit.jupiter.api.Test;
import project_csc309_spring_2024.multiplayer.*;

public class ServerTest {

    @Test
    public void TestServerConnectionNotify() {
        final boolean[] clientConnectionNotified = {false};
        ServerListener serverListener = new ServerListener() {

            @Override
            public void onClientConnection(ServerEvent event) {
                clientConnectionNotified[0] = true;
            }

        };

        Server server = new Server(8080);
        server.addServerListener(serverListener);
        server.notifyAllLisenters(new ServerEvent(ServerEvent.CLIENT_CONNECTED, 0));
    }
}
