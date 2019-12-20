package Blocking;

import WorkWithFle.WorkWithFile;

import java.io.PrintWriter;
import java.net.InetAddress;

public class Nslookup {

    public void nsLookupGetIP(String host) {
        try {
            System.setProperty("sun.net.spi.nameservice.nameservers", "213.179.249.135");
//            System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
            InetAddress[] result = InetAddress.getAllByName(host);
            for (InetAddress var : result) {

                String hostAdress = var.getHostAddress();
                System.out.println(host + "/" +  hostAdress);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
