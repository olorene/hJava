package Ping;

import WorkWithFle.WorkWithFile;

import java.io.IOException;

public class PingTool {
    public static void main(String[] args)  throws /*UnknownHostException,*/ IOException {
        String pathToFileSource = "D:\\tmp\\HostMonitor-List.txt";
        String pathToFileResult = "D:\\tmp\\HostMonitor-Result.txt";
        WorkWithFile.deleteFile(pathToFileResult);

        PingTool pingTool = new PingTool();
        String[] outFromFile = WorkWithFile.openFile(pathToFileSource);

        for (String ipAddress: outFromFile) {
//            System.out.println(".");
//            String[] result = new String[1];
            ipAddress = ipAddress.trim();
//            PingHost.pingHost(pathToFileResult, ipAddress);
        }

    }

}
