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

        String patternForIp = "\\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.)" +
                "{3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\b";
        Pattern patternIp = Pattern.compile(patternForIp);
        // There is a word "Address:" in the output of the CLI
        String patternForWordAddress = "Address:";
        Pattern patternWordAddress = Pattern.compile(patternForWordAddress);


        for (String retval : outputCli.split("\\n")) {
            retval = retval.trim();
            if (patternIp.matcher(retval).find() && !(patternWordAddress.matcher(retval).find())) {
//                System.out.println(aDomainName + " : " + retval);
                map.put(retval, aDomainName);
            } else if (patternWordAddress.matcher(retval).find()){
                // Parsing this "Address:  192.168.13.254"
                String[] ipAddress = retval.split("\\s", 2);
//                    System.out.println(aDomainName + " : " + ipAddress[1].trim());
                map.put(ipAddress[1].trim(), aDomainName);
            }

        }

        return map;
    }
}
