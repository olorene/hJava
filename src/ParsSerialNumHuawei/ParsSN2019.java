package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

public class ParsSN2019 {
    public static void headreFileSn(String pathToSnFile){
        //        Type  Slot    BoardType	 BarCode	 Description	 Region	 Hostname	 IP	 Chassis	date
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
    }
}
