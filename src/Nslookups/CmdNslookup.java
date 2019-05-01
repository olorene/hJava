package Nslookups;

import java.io.*;
import java.util.ArrayList;

public class CmdNslookup {
    public String Nslookup(String domainName) {
        ArrayList<String> outputCli = new ArrayList<String>();
        String outputStringCli = null;
        String ss = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd.exe /c nslookup " + domainName);
            OutputStreamWriter os = new OutputStreamWriter(p.getOutputStream());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//            System.out.println("Here is the standard output of the command:");
            for (ss = stdInput.readLine(); ss != null; ss = stdInput.readLine()) {
                outputStringCli = outputStringCli + ss + '\n';
            }

            while ((ss = stdError.readLine()) != null) {
//                outputCli.add(ss);
//                outputStringCli = outputStringCli + ss + '\n';
//                System.out.println(ss);
            }
        } catch (IOException e) {
            System.out.println("FROM CHAT " + e.toString());
        }
        return outputStringCli;
    }
}
