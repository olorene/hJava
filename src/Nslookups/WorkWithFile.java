package Nslookups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class WorkWithFile {
    private String pathToFile = null;

    public WorkWithFile(String aPathToFile) {
        pathToFile = aPathToFile;
    }
    public ArrayList<String> openAndReadFile() {
        ArrayList<String> arrayDomanName = new ArrayList<>();
        try {
            File myFile = new File(pathToFile);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                arrayDomanName.add(line);
//                System.out.println(line);
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return arrayDomanName;
    }

    public void writeInFile(String fileForWrite, HashMap<String, String> map) {
        try {
            FileWriter writer = new FileWriter(fileForWrite);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
