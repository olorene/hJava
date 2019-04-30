package SocetThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class FirstSocketClient {
    public void go() {
        try {
            Socket s = new Socket("127.0.0.1", 80);
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            String advice = reader.readLine();
            System.out.println("Сегодня ты должен " + advice);
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        FirstSocketClient client = new FirstSocketClient();
        client.go();
    }
}
