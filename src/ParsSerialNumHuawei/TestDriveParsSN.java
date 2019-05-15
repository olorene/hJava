package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.util.ArrayList;

public class TestDriveParsSN {
    public static void main(String[] args) {
//        String pathToDir = "D:\\tmp\\Inventarizaciya_93xx_2017";
//        WorkWithFile.showDir(pathToDir);

        String pathToFile = "D:\\tmp\\Inventarizaciya_93xx_2017\\Івано-Франківськ\\10.171.8.1.txt";
        String[] result = WorkWithFile.openFile(pathToFile);
//        BoardType	 BarCode	 Description	 Region	 Hostname	 IP	 Chassis	date
//        for (int i = 0; i < result.length; i++) {
//            System.out.println("==============================");
//            System.out.println(result[i]);
//        }
        ParsSN parsSN = new ParsSN();
        ArrayList<String> outDispDevice = parsSN.outDisplayDevice(pathToFile);
        parsSN.findSlotDispDevice(outDispDevice);




    }
}
