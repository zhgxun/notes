package github.banana.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {

    public static void main(String[] args) {
        int port = 8088;
        try {
            ServerSocket socket = new ServerSocket(port);
            Socket socket1 = socket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
