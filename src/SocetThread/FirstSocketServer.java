package SocetThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FirstSocketServer {
    String[] adviceList = {"Hello world", "No sleep!", "Get up!"};

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
//                System.out.println("Before accept");
                Socket socket = serverSocket.accept();
//                System.out.println("After accept");
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        FirstSocketServer server = new FirstSocketServer();
        server.go();
    }


}
