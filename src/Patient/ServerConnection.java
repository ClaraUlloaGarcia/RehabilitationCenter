package Patient;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {

    private String IP;

    // Singleton
    private static ServerConnection connection = null;

    private Socket socket;

    private ServerConnection() {
    }

    public static ServerConnection getInstance() {
        if (connection == null) {
            connection = new ServerConnection();
        }
        return connection;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Socket getSocket() {
        if (socket == null) {
            try {
                socket = new Socket(IP, 9000);
            } catch (IOException ex) {
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return socket;
    }
}
