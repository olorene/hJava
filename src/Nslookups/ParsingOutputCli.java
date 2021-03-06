package Nslookups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class  ParsingOutputCli {
    public static ArrayList<String> findIp(String aDomainName, String outputCli) {
        //What we get over from Omnissiah!
//        System.out.println(outputCli);
//        System.out.println("===========================");

        ArrayList<String> map = new ArrayList<>();

        String ip = "\\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.)" +
                "{3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\b";
        Pattern patternIp = Pattern.compile(ip);
        // There is a word "Address:" in the output of the CLI
        String wordAddress = "Address:";
        String wordAddresses = "Addresses:";
        Pattern patternWordAddress = Pattern.compile(wordAddress);
        Pattern patternWordAddresses = Pattern.compile(wordAddresses);
        // This is an extra IP address
        String ExtraIp = "10.36.1.101";
        Pattern patternExtraIp = Pattern.compile(ExtraIp);

        boolean findIpForDomainName = false;
        for (String retval : outputCli.split("\\n")) {
            retval = retval.trim();
            Boolean addToHashmap = false;
            if (patternIp.matcher(retval).find()) {
                if (patternWordAddress.matcher(retval).find() || patternWordAddresses.matcher(retval).find()) {
                    // Parsing this "Address:  192.168.13.254"
                    String[] ipAddress = retval.split("\\s", 2);
                    retval = ipAddress[1].trim();
                }
                addToHashmap = true;
            }
/*            if (patternIp.matcher(retval).find() && !(patternWordAddress.matcher(retval).find())
                    || !(patternWordAddresses.matcher(retval).find())) {
                addToHashmap = true;
            } else if (patternIp.matcher(retval).find() && patternWordAddress.matcher(retval).find() || (patternWordAddresses.matcher(retval).find())){
                // Parsing this "Address:  192.168.13.254"
                String[] ipAddress = retval.split("\\s", 2);
                retval = ipAddress[1].trim();
                addToHashmap = true;
            } else {
                retval = null;
            }*/
            if (retval != null && addToHashmap == true && !(patternExtraIp.matcher(retval).find())) {
                map.add(retval + "=" + aDomainName);
                findIpForDomainName = true;
            }

        }
        if (findIpForDomainName == false) {
            map.add(aDomainName + "=" + aDomainName);
        }

        return map;
    }
}
