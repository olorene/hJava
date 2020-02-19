package WorkWithFle;

import java.io.*;
import java.util.ArrayList;

public class WorkWithFile {

    private static ArrayList<String> outGlobDir = new ArrayList<>();

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

    public static void appendInFile(String pathToFile, String[] array) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile, true));
            for (String line : array) {
//                writer.newLine();
                String row = line + "\r\n";
                writer.append(row);
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
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
    public static void writeFile(String pathToFile, String line ) {
        try {
            FileWriter writer = new FileWriter(pathToFile, true);
            writer.write(line);
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

    public static ArrayList<String> showDir(String pathToDir) {
        File folder = new File(pathToDir);
        showDir(folder);

        return outGlobDir;
    }

    public static void showDir(File folder) {
        ArrayList<String> output = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                showDir(fileEntry);
            } else {
//                System.out.println(fileEntry.getAbsolutePath());
                outGlobDir.add(fileEntry.getAbsolutePath());
            }
        }

    }

/*    public static void showDir(String pathToDir) {
        try (Stream<Path> paths = Files.walk(Paths.get(pathToDir))) {
            paths.filter(Files::isRegularFile).forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/
}
