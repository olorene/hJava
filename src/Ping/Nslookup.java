package Ping;

import WorkWithFle.WorkWithFile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Nslookup {
    public static void main(String[] args) throws IOException {
        String pathToFileSource = "D:\\tmp\\NslookupDomainName.txt";
        String pathToFileResult = "D:\\tmp\\NslookupDomainName-result.txt";
        WorkWithFile.deleteFile(pathToFileResult);

        String[] outFromFile = WorkWithFile.openFile(pathToFileSource);

        for (String domainName : outFromFile) {
//            System.out.println(domainName);
            String[] result = new String[1];
            domainName = domainName.trim();
            try {
//                Pinging.getIpFromHost(domainName);
                InetAddress[] getAll = InetAddress.getAllByName(domainName);
                for (InetAddress var : getAll) {

                    String hostAdress = var.getHostAddress();
                    System.out.println(domainName + " ~ " +  hostAdress);
                    result[0] = domainName + " ~ " +  hostAdress;
                    WorkWithFile.writeFile(pathToFileResult, result);
                }

            } catch (UnknownHostException ex) {
                result[0] = domainName + " + ";
                WorkWithFile.writeFile(pathToFileResult, result);
            }
        }
    }

}
