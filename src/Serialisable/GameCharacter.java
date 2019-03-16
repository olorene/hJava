package Serialisable;

import java.io.Serializable;

public class GameCharacter implements Serializable {
    private int power;
    private String type;
    private String[] weapons;
    public transient int i = 13;

    public GameCharacter(int p, String t, String[] w) {
        power = p;
        type = t;
        weapons = w;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeapons(String aWeapons) {
        String[] tmpWeapons = new String[weapons.length + 1];

        for (int i = 0; i < weapons.length; i++) {
            tmpWeapons[i] = weapons[i];
        }
        tmpWeapons[weapons.length] = aWeapons;
        weapons = tmpWeapons;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public String getWeapons() {
        String weaponList = "";

        for (int i = 0; i < weapons.length; i++) {
            weaponList += weapons[i] + " ";
        }
        return weaponList;
    }
}
