package Ping;

import WorkWithFle.WorkWithFile;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Pinging {
    // Sends ping request to a provided IP address
    public static void sendPingRequest(String ipAddress)
            throws UnknownHostException, IOException
    {
        InetAddress geek = InetAddress.getByName(ipAddress);
        System.out.println("Sending Ping Request to " + ipAddress);
        if (geek.isReachable(5000))
            System.out.println("Host is reachable");
        else
            System.out.println("Sorry ! We can't reach to this host");
    }

    public static Boolean pingingHost (String ipAddress) throws UnknownHostException, IOException {
        Boolean result = false;
        InetAddress geek = InetAddress.getByName(ipAddress);
        System.out.println("Sending Ping Request to " + ipAddress);
        if (geek.isReachable(5000)) {
            result = true;
//            System.out.println("Host is reachable");
        }

        return result;
    }

    public static void getIpFromHost (String domainName) throws  UnknownHostException, IOException {
//        Boolean result = false;
//        System.out.println("nslookup for " + domainName);
//        InetAddress ipForDomain = InetAddress.getByName(domainName);
        InetAddress[] getAll = InetAddress.getAllByName(domainName);
//        System.out.println("IP for hostname " + ipForDomain.getHostAddress());
        for (InetAddress var :
                getAll) {

            String hostAdress = var.getHostAddress();
            System.out.println(domainName + " ~ " +  hostAdress);

        }


    }

    // Driver code
    public static void main(String[] args) throws UnknownHostException, IOException
    {
//        String ipAddress = "127.0.0.1";
//        sendPingRequest(ipAddress);
//
//        ipAddress = "google.com.ua";
//        sendPingRequest(ipAddress);
//
//        ipAddress = "145.154.42.58";
//        sendPingRequest(ipAddress);

        String domainName = "labrc.net";
        getIpFromHost(domainName);

    }
}
