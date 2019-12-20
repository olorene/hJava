package Blocking;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileHosts = "D:\\Data\\HostForBloack.txt";
        String fileDnsServersList = "D:\\Data\\DnsServerList.txt";
        ArrayList<String> listHosts = new ArrayList<String>();
        ArrayList<String> listDnsServers = new ArrayList<String>();
        GetDataFromFile hosts = new GetDataFromFile();
        listHosts = hosts.getHosts(fileHosts);
        listDnsServers = hosts.getHosts(fileDnsServersList);

        for (String dns :
                listDnsServers) {
            String[] result = dns.split("/");
            System.out.println(result[1]);

        }

/*        DNSResolver dnsResolve = new DNSResolver();
        try {
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
