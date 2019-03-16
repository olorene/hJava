package Serialisable;

import java.io.FileWriter;

public class WriteAFile {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("C:\\tmp\\Game.txt");
            writer.write("Привет Foo!");
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
