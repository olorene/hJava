import java.io.*;

public class Cmd {
    public static void main(String[] args) {
        try {
            String ss = null;
            String domainName = "ya.ru";
            Process p = Runtime.getRuntime().exec("cmd.exe /c nslookup " + domainName);
            BufferedWriter writeer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            writeer.write("dir");
            writeer.flush();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            System.out.println("Here is the standard output of the command:\\n");
            PrintStream outStream = new PrintStream(System.out, true, "UTF-8");
            while ((ss = stdInput.readLine()) != null) {
                outStream.println(ss);
            }
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((ss = stdError.readLine()) != null) {
                outStream.println(ss);
            }
        } catch (IOException e) {
            System.out.println("FROM CHAT " + e.toString());
        }
    }
}
