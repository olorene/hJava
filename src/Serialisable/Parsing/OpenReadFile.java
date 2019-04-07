package Serialisable.Parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//test
public class OpenReadFile {
    public void go(String pathToFle) {
        File file = new File(pathToFle);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
