package tmp;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Thoughts {
    ArrayList<String> songList = new ArrayList<String>();

    public static void main(String[] args) {
        new Thoughts().getList();
//        System.out.println(songList);
    }

    public void go() {
        getList();
        System.out.println(songList);
    }

    public void getList() {
        try {
            File file = new File("D:\\tmp\\MyJavaStudy\\SongList.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
            System.out.println("================");
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                songList.add(line);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
