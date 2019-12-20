package Blocking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetDataFromFile {
    public ArrayList<String> getHosts(String path) {
        ArrayList<String> listHosts = new ArrayList<String>();
        try {
            File myFile = new File(path);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                listHosts.add(line);
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listHosts;
    }
}
