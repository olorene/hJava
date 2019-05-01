package Nslookups;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class  ParsingOutputCli {
    public static HashMap<String, String> findIp(String aDomainName, String outputCli) {
        //What we get over from Omnissiah!
//        System.out.println(outputCli);
//        System.out.println("===========================");

        HashMap<String, String> map = new HashMap<>();

        String ip = "\\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.)" +
                "{3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\b";
        Pattern patternIp = Pattern.compile(ip);
        // There is a word "Address:" in the output of the CLI
        String wordAddress = "Address:";
        Pattern patternWordAddress = Pattern.compile(wordAddress);
        // This is an extra IP address
        String ExtraIp = "192.168.13.254";
        Pattern patternExtraIp = Pattern.compile(ExtraIp);

        boolean findIpForDomainName = false;
        for (String retval : outputCli.split("\\n")) {
            retval = retval.trim();
            Boolean addToHashmap = false;
            if (patternIp.matcher(retval).find() && !(patternWordAddress.matcher(retval).find())) {
                addToHashmap = true;
            } else if (patternWordAddress.matcher(retval).find()){
                // Parsing this "Address:  192.168.13.254"
                String[] ipAddress = retval.split("\\s", 2);
                retval = ipAddress[1].trim();
                addToHashmap = true;
            } else {
                retval = null;
            }
            if (retval != null && addToHashmap == true && !(patternExtraIp.matcher(retval).find())) {
                map.put(retval, aDomainName);
                findIpForDomainName = true;
            }

        }
        if (findIpForDomainName == false) {
            map.put(aDomainName, aDomainName);
        }

        return map;
    }
}
