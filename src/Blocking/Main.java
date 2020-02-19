package Blocking;

import WorkWithFle.WorkWithFile;
import Ping.PingHost;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileHosts = "D:\\Data\\HostForBloack.txt";
        String fileDnsServersList = "D:\\Data\\DnsServerList.txt";
        String fileOutResult = "D:\\Data\\resolveAndIcmpCheck.txt";
        ArrayList<String> listHosts = new ArrayList<String>();
        ArrayList<String> listDnsServers = new ArrayList<String>();
        GetDataFromFile hosts = new GetDataFromFile();

        listHosts = hosts.getHosts(fileHosts);
        listDnsServers = hosts.getHosts(fileDnsServersList);

        DNSResolver dnsResolve = new DNSResolver();


        for (int i = 0; i < listHosts.size(); i++) {
            String aSite = listHosts.get(i);
//            System.out.println(listHosts.get(i));
            int countAllDnsServers = 0;
            boolean corpDns = false;
            String aIpDnsServer = null;
            Boolean icmpAvailable = false;
            try {
                if (PingHost.pingHost(aSite) == true) {
                    icmpAvailable = true;
                }
            } catch (Exception ex) {
                icmpAvailable = false;
            }
            for (String dns : listDnsServers) {
                String[] result = dns.split("/");
                aIpDnsServer = result[1];
                if (aIpDnsServer.equals("10.36.1.101") || aIpDnsServer.equals("10.36.1.102")) {
                    corpDns = true;
                }
                try {
                    String dateResove = dnsResolve.resolveARecord(aIpDnsServer, aSite);
//                    System.out.println(aSite + " " + aIpDnsServer + " " + dateResove);

                } catch (Exception ex) {
                    if (corpDns == true) {
                        corpDns = false;
//                    System.out.println(aSite + " " + aIpDnsServer + " " + "N/A");
                    }
                    countAllDnsServers += 1;
                }
            }
            if (countAllDnsServers == 0 && corpDns == true) {
//                System.out.println(countAllDnsServers);
                System.out.println(aSite + " " + "Resolve" + " Resolve " + icmpAvailable.toString());
                String result =  aSite + " " + "Resolve" + " Resolve " + icmpAvailable.toString() + "\r\n";
                WorkWithFile.writeFile(fileOutResult, result);
                countAllDnsServers = 0;
            } else {
//                System.out.println(countAllDnsServers);
                if (corpDns == false) {
//                    System.out.println(aSite + " " + "Not resolve" + " CorpDns " + " Not Resolve");
                    System.out.println(aSite + " " + "Not_resolve" + " Not_Resolve " + icmpAvailable.toString());
                    String result = aSite + " " + "Not_resolve" + " Not_Resolve " + icmpAvailable.toString() + "\r\n";
                    WorkWithFile.writeFile(fileOutResult, result);
                } else {

//                    System.out.println(aSite + " " + "Not resolve" + " CorpDns " + " 2Resolve");
                    System.out.println(aSite + " " + "Not_resolve" + " Resolve " + icmpAvailable.toString());
                    String result = aSite + " " + "Not_resolve" + " Resolve " + icmpAvailable.toString() + "\r\n";
                    WorkWithFile.writeFile(fileOutResult, result);
                }
                countAllDnsServers = 0;
                corpDns = false;
            }
        }


/*        try {
            String dataResove = dnsResolve.resolveARecord("213.179.249.135", "iklad.biz");
            System.out.println("213.179.249.135" + ";" + "iklad.biz" + ";" + dataResove);
        } catch (Exception ex) {
            System.out.println("213.179.249.135" + ";" +  "iklad.biz" + ";" + "N/A");
        }

        try {
            String dataResove = dnsResolve.resolveARecord("213.179.249.135", "i.ua");
            System.out.println("213.179.249.135" + ";" + "i.ua" + ";" + dataResove);
        } catch (Exception ex) {
            System.out.println("213.179.249.135" + ";" + "i.ua" + ";" + "N/A");
        }*/





    }
}
