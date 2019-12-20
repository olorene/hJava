package Blocking;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class DNSResolver {
//    public static void main(String args[]) {
//        try {
//            System.out.println("IP:" + resolveARecord("213.179.249.135", "iklad.biz"));
//        } catch (Exception ex) {
////            ex.printStackTrace();
//            System.out.println("iklad.biz don't resolve on 213.179.249.135");
//        }
//    }

    public  String resolveARecord(String dnsServer, String hostname) {
        final String[] DNSTYPE = new String[] { "A" };
        try {
            final Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
            env.put(Context.PROVIDER_URL, "dns://" + dnsServer + "/.");
            final DirContext ictx = new InitialDirContext(env);
            final Attributes attr = ictx.getAttributes(hostname, DNSTYPE);
            Attribute attrib = attr.get("A");
            return (String) attrib.get(0);
        } catch (NamingException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
