package Ping;

import WorkWithFle.WorkWithFile;

import java.io.IOException;
import java.net.UnknownHostException;

public class PingTool {
    public static void main(String[] args)  throws /*UnknownHostException,*/ IOException {
        String pathToFileSource = "D:\\tmp\\HostMonitor-List.txt";
        String pathToFileResult = "D:\\tmp\\HostMonitor-Result.txt";
        WorkWithFile.deleteFile(pathToFileResult);

        PingTool pingTool = new PingTool();
        String[] outFromFile = WorkWithFile.openFile(pathToFileSource);

        for (String ipAddress: outFromFile) {
//            System.out.println(".");
            String[] result = new String[1];
            ipAddress = ipAddress.trim();
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

    }
}
