package Ping;

import WorkWithFle.WorkWithFile;

import java.io.IOException;
import java.net.UnknownHostException;

public class PingHost {
    public static void pingHost(String pathToFileResult, String ipAddress ) throws IOException {
        String[] result = new String[1];
        try {
            if (Pinging.pingingHost(ipAddress)) {
                result[0] = ipAddress + "\t" + "reachable";
                WorkWithFile.writeFile(pathToFileResult, result);
            } else {
                result[0] = ipAddress + "\t" + "-";
                WorkWithFile.writeFile(pathToFileResult, result);
            }
        } catch (UnknownHostException ex) {
            result[0] = ipAddress + "\t" + "-";
            WorkWithFile.writeFile(pathToFileResult, result);
        }
    }

    public static Boolean pingHost(String ipAddress ) throws IOException {
        String[] result = new String[1];
        Boolean reachable = false;
        try {
            if (Pinging.pingingHost(ipAddress)) {
                reachable = true;
            }
        } catch (UnknownHostException ex) {
        }
        return reachable;
    }
}
