package WorkWithFle;

import tmp.Parent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class WorkWithFile {
/*    private  String pathToFile;

    public WorkWithFile(String aPathToFile) {
        setPathToFile(aPathToFile);
    }

    public  String getPathToFile() {
        return pathToFile;
    }

    public  void setPathToFile(String pathToFile) {
        //Reg exp for c:\\tmp\\ListDomainIP.txt
        String regExpPath = "(c|d):\\\\\\\\tmp\\\\\\\\\\w.+\\.txt";
        Pattern patternPath = Pattern.compile(regExpPath);
        if (patternPath.matcher(pathToFile).find()) {
            this.pathToFile = pathToFile;
        }
    }*/

    public static String[] openFile(String pathToFile){
        String[] array = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            array = lines.toArray(new String[lines.size()]);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return array;
    }

    public static void writeFile(String pathToFile, String[] array ) {
        try {
            FileWriter writer = new FileWriter(pathToFile);
            for (String entry : array) {
                String line = entry + "\r\n";
                writer.write(line);
            }
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
