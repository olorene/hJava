package Nslookups;

import java.util.*;

public class MainNslookup {
    public static void main(String[] args) {
//        String aDomainName = "ya.ru";
//        String aDomainName = "nasa.gov";

        HashMap<String, String> mapAllDomainIp = new HashMap<>();
        String pathToFile = "C:\\tmp\\domainName.txt";
        String pathToFileResult = "C:\\tmp\\resultDomainIp.txt";
        WorkWithFile readFile = new WorkWithFile(pathToFile);
        ArrayList<String> arrayDomanName = readFile.openAndReadFile();
        for (int i = 0; i < arrayDomanName.size(); i++) {
            String aDomainName;
//            System.out.println(arrayDomanName.get(i));
            aDomainName = arrayDomanName.get(i);

            CmdNslookup nslookup = new CmdNslookup();
            String outputCli = nslookup.Nslookup(aDomainName);

            HashMap<String, String> map = ParsingOutputCli.findIp(aDomainName, outputCli);
            mapAllDomainIp.putAll(map);
        }
        System.out.println(mapAllDomainIp);





    }

}
