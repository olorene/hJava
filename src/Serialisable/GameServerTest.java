package Serialisable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameServerTest {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50, "Эльф", new String[]{"Лук", "Меч", "Кстет"});
        GameCharacter two = new GameCharacter(200, "Троль", new String[]{"Голые_руки", "Большой_топор"});
        GameCharacter three = new GameCharacter(120, "Маг", new String[]{"Заклинания", "Невидемость"});

        one.setPower(70);
        two.setWeapons("Голова");
        three.setType("Super_mag");
        System.out.println(one.i);

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:\\tmp\\Game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        } catch (Exception ex) {
            System.out.println("File Game.ser doesn't create");
            ex.printStackTrace();
        }

        one = null;
        two = null;
        three = null;

        GameCharacter oneRestore = null;
        GameCharacter twoRestore = null;
        GameCharacter threeRestore = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("C:\\tmp\\Game.ser"));
            oneRestore = (GameCharacter) is.readObject();
            twoRestore = (GameCharacter) is.readObject();
            threeRestore = (GameCharacter) is.readObject();

            System.out.println();

        } catch (Exception ex) {
            System.out.println("File Game.ser ");
            ex.printStackTrace();
        }
        System.out.println(oneRestore.getPower());
        System.out.println(twoRestore.getWeapons());
        System.out.println(threeRestore.getType());
        System.out.println(oneRestore.i);

    }
}
