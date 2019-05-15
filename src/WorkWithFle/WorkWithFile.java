package WorkWithFle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

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
            FileWriter writer = new FileWriter(pathToFile, true);
            for (String entry : array) {
                String line = entry + "\r\n";
                writer.write(line);
            }
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteFile(String pathToFile) {
        try {
            File file = new File(pathToFile);
            file.delete();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showDir(String pathToDir) {
        try (Stream<Path> paths = Files.walk(Paths.get(pathToDir))) {
            paths.filter(Files::isRegularFile).forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
