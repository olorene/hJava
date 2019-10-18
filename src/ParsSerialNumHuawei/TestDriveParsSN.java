package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TestDriveParsSN {

    public static void main(String[] args) {
        Boolean debug = true;

        String pathToDir = "D:\\tmp\\Inventarizaciya_93xx_2017";
        if(debug == true) {
            ArrayList<String> outDir = WorkWithFile.showDir(pathToDir);
            for (int i = 0; i < outDir.size(); i++) {
                System.out.println(outDir.get(i));
            }
        }

        String pathToSnFile = "D:\\tmp\\SerialNumberHuaweiSwitch.csv";
        String pathToFile = "D:\\tmp\\Inventarizaciya_93xx_2017\\Івано-Франківськ\\10.171.8.1.txt";
        String[] result = WorkWithFile.openFile(pathToFile);
        ArrayList<String> resultArray = new ArrayList<>(Arrays.asList(result));

        ParsSN2019.headreFileSn(pathToFile);

//*
///        Type  Slot    BoardType	 BarCode	 Description	 Region	 Hostname	 IP	 Chassis	date
        String type = "Type";
        String slot = "Slot";
        String boardType = "BoardType";
        String barCode = "BarCode";
        String description = "Description";
        String region = "Region";
        String hostname = "Hostname";
        String ip = "IP";
        String chassis = "Chassis";
        String date = "Date";

        WorkWithFile.deleteFile(pathToSnFile);
        String resultLine = type + ";" + slot + ";" + boardType + ";" + barCode + ";" + description +
                ";" + region + ";" + hostname + ";" + ip + ";" + chassis + ";" + date;
        String[] resultLineArr = new String[1];
        resultLineArr[0] = resultLine;
        WorkWithFile.writeFile(pathToSnFile, resultLineArr);



        ParsSN parsSN = new ParsSN();

//        Process header output file from Huawei 9312. Process have not ended.
//        ArrayList<String> outDispDevice = parsSN.outDisplayDevice(pathToFile);
//        parsSN.findSlotDispDevice(outDispDevice);

//        Device type
//        System.out.println(ParsSN.deviceType(cliOutput));

//      [Rack_1]
        type = "Backplane";
        slot = "Rack_1";

        Pattern pattStart = Pattern.compile("\\[Rack_1\\]");
        Pattern pattEnd = Pattern.compile("\\[Slot_1\\]");
        ArrayList<String> blockRack = parsSN.findBlockConfig(resultArray, pattStart, pattEnd);
//      [Rack_1] -> [Board Properties]
        pattStart = Pattern.compile("\\[Board Properties\\]");
        pattEnd = Pattern.compile("^$");
        ArrayList<String> blockRackBoardProperties = parsSN.findBlockConfig(blockRack, pattStart, pattEnd);
        if(debug == false) {
            for (int i = 0; i < blockRackBoardProperties.size(); i++) {
                System.out.println(blockRackBoardProperties.get(i));
            }
        }

//      [Rack_1] -> [Board Properties] find "BoardType"
        Pattern pattElement = Pattern.compile("BoardType=");
        boardType = parsSN.parsBlockConfig(blockRackBoardProperties, pattElement);

//      [Rack_1] -> [Board Properties] find "BarCode"
        pattElement = Pattern.compile("BarCode=");
        barCode = parsSN.parsBlockConfig(blockRackBoardProperties, pattElement);

//      [Rack_1] -> [Board Properties] find "Description"
        pattElement = Pattern.compile("Description=");
        description = parsSN.parsBlockConfig(blockRackBoardProperties, pattElement);



//        resultLine = type + ";" + slot + ";" + boardType + ";" + barCode + ";" + description +
//                ";" + region + ";" + hostname + ";" + ip + ";" + chassis + ";" + date;
//        resultLineArr = new String[1];
//        resultLineArr[0] = resultLine;
//        WorkWithFile.writeFile(pathToSnFile, resultLineArr);







    }


}
