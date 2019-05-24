package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TestDriveParsSN {

    public static void main(String[] args) {
        Boolean debug = true;

        String pathToDir = "D:\\tmp\\Inventarizaciya_Huawei_2019";
        if(debug == false) {
//            ArrayList<String> outDir = WorkWithFile.showDir(pathToDir);
//            for (int i = 0; i < outDir.size(); i++) {
//                System.out.println(outDir.get(i));
//            }
        }
        String pathToSnFile = "D:\\tmp\\SerialNumberHuaweiSwitch.csv";
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
        //        Prepear file, write header
        WorkWithFile.deleteFile(pathToSnFile);
        String resultLine = type + ";" + slot + ";" + boardType + ";" + barCode + ";" + description +
                ";" + region + ";" + hostname + ";" + ip + ";" + chassis + ";" + date;
        String[] resultLineArr = new String[1];
        resultLineArr[0] = resultLine;
        WorkWithFile.writeFile(pathToSnFile, resultLineArr);
        ArrayList<String> outDir = WorkWithFile.showDir(pathToDir);


        for (int j = 0; j < outDir.size(); j++) {
            String pathToFile = outDir.get(j);
//            System.out.println(line);

    //        String pathToFile = "D:\\tmp\\Inventarizaciya_93xx_2017\\Винница\\10.171.1.1.txt";
    //        String pathToFile = "D:\\tmp\\Inventarizaciya_Huawei_2019\\Kyiv\\10.171.0.22.log";
//            String pathToFile = "D:\\tmp\\Inventarizaciya_Huawei_2019\\kv\\10.171.0.1.log";
    //        S5328C-HI-24S - 10.171.30.123 - kv - <lab-5300-hi-agds>

            String[] result = WorkWithFile.openFile(pathToFile);
            ArrayList<String> resultArray = new ArrayList<>(Arrays.asList(result));

            ParsSN parsSN = new ParsSN();

    //        IP and Region
            String[] absolutNameFile = pathToFile.split("\\\\");
            String nameOfFile = absolutNameFile[absolutNameFile.length - 1];
            String nameOfRegion = absolutNameFile[absolutNameFile.length - 2];
            ip = parsSN.identifyIp(nameOfFile);
            region = nameOfRegion;

    //      Hostname
            Pattern patternHostname = Pattern.compile("<.+>");
            for (int i = 0; i < resultArray.size(); i++) {
                String line = resultArray.get(i);
                if (patternHostname.matcher(line).find()) {
                    String[] firstPartName = line.split(">");
                    String[] secondPartName = firstPartName[0].split("<");
                    hostname = secondPartName[1];
    //                System.out.println(secondPartName[1]);
                    break;
                }
            }

    //      Chassis
            Pattern patternDeviceType = Pattern.compile("Device status:");
            Pattern patternCss = Pattern.compile("Chassis 1");
            for (int i = 0; i < resultArray.size(); i++) {
                String line = resultArray.get(i).trim();
                if (patternCss.matcher(line).find()) {
                    //        <vc-HW-S9312-CSS0>display device
                    //        Chassis 1 (Master Switch)
                    //        S9312's Device status:
                    chassis = "S9312-CSS";
//                    System.out.println(chassis);
                    break;
                } else if (patternDeviceType.matcher(line).find()) {
                    //        S9312's Device status:
                    String[] parts = line.split("'s");
                    chassis = parts[0];
//                    System.out.println(chassis);
                    break;
                }
            }


    ///////        Get num slots
    //        <vc-HW-S9312-CSS0>display device
    //        Chassis 1 (Master Switch)
    //        S9312's Device status:
            Pattern patternChassis = Pattern.compile("^"+chassis+"$");
            ArrayList<String> fullSlots = new ArrayList<>();
            ArrayList<String> fullSlotsChassis2 = new ArrayList<>();
            if (patternChassis.matcher("S9312-CSS").find()) {
                ArrayList<String> outDispDevice = parsSN.outDisplayDevice(pathToFile);
                fullSlotsChassis2 = parsSN.findSlotClaster(outDispDevice);

    //        <kv-obuhiv-HW-S9312-dsag0>display device
    //        S9312's Device status:
    //        10.171.0.22
            } else if (patternChassis.matcher("S9312").find()) {
                ArrayList<String> outDispDevice = parsSN.outDisplayDevice(pathToFile);
                fullSlots = parsSN.findSlotDispDevice(outDispDevice);
    //
    //        <vc-zhmerynka-HW-S9306-ag-0>display device
    //        S9306's Device status:
            } else if (patternChassis.matcher("S9306").find()) {
                ArrayList<String> outDispDevice = parsSN.outDisplayDevice(pathToFile);
                fullSlots = parsSN.findSlotDispDevice(outDispDevice);
    //
    //        <vc-vapnyarka-HW-S9303-ag-0>display device
    //        S9303's Device status:
            } else if (patternChassis.matcher("S9303").find()) {
                ArrayList<String> outDispDevice = parsSN.outDisplayDevice(pathToFile);
                fullSlots = parsSN.findSlotDispDevice(outDispDevice);
    //
    //        <vc-nemyriv-HW-S5300-ag-0>display device
    //        S5300-28X-LI-24S-DC's Device status:
            } else if (patternChassis.matcher("S5300-28X-LI-24S-DC").find()) {
//                System.out.println("S5300===");
            } else {
                System.out.println("New type Switch " + chassis);
            }
/////////////////////////////////////////////////
    //        [Rack_1]
            if (!(patternChassis.matcher("S5300-28X-LI-24S-DC").find())) {
                type = "Backplane";
                slot = "Rack_1";
                String rack = "Rack_1";
                rack1(debug, pathToSnFile, resultArray, region, hostname, ip, chassis, date, parsSN, type, slot, rack);
            }

    //        [SubRack_2]
            if (patternChassis.matcher("S9312-CSS").find()) {
                slot = "SubRack_2";
                String rack = "SubRack_2";
                rack1(debug, pathToSnFile, resultArray, region, hostname, ip, chassis, date, parsSN, type, slot, rack);

                ArrayList<String> slotsChassis1 = new ArrayList<>();
                ArrayList<String> slotsChassis2 = new ArrayList<>();
                for (int i = 0; i < fullSlotsChassis2.size(); i++) {
                    String line = fullSlotsChassis2.get(i);
                    String[] parts = line.split(";");
                    Pattern pattLocalChassis = Pattern.compile(parts[0]);
//                    System.out.println( hostname + " " + parts[0]);
                    System.out.println(chassis + " " + ip);
                    if (pattLocalChassis.matcher("Chassis 1").find()) {
                        slotsChassis1.add(parts[1]);
                    }

                    if (pattLocalChassis.matcher("Chassis 2").find()) {
                        slotsChassis2.add(parts[1]);
                    }

//                    System.out.println(line);

                }

                for (int i = 0; i < slotsChassis1.size(); i++) {
                    String line = slotsChassis1.get(i);
                    type = "Chassis 1";
                    slot = "Slot_" + line;
                    Pattern pattStart = Pattern.compile("\\[SubRack_1\\]");
                    Pattern pattEnd = Pattern.compile("\\[SubRack_2\\]");
                    ArrayList<String> rack1Array = parsSN.findBlockConfig(resultArray, pattStart, pattEnd);

                    pattStart = Pattern.compile("\\[Slot_" + line + "\\]");
                    pattEnd = Pattern.compile("^BOM=$");
                    parsSlot(pathToSnFile, rack1Array, region, hostname, ip, chassis, date,
                            parsSN, pattStart, pattEnd, type, slot);

                }

                for (int i = 0; i < slotsChassis2.size(); i++) {
                    String line = slotsChassis2.get(i);
                    type = "Chassis 2";
                    slot = "Slot_" + line;
                    Pattern pattStart = Pattern.compile("\\[SubRack_2\\]");
                    Pattern pattEnd = Pattern.compile("\\[Slot_26\\]");
                    ArrayList<String> rack1Array = parsSN.findBlockConfig(resultArray, pattStart, pattEnd);

                    pattStart = Pattern.compile("\\[Slot_" + line + "\\]");
                    pattEnd = Pattern.compile("^BOM=$");
                    parsSlot(pathToSnFile, rack1Array, region, hostname, ip, chassis, date,
                            parsSN, pattStart, pattEnd, type, slot);

                }
            }

            if (patternChassis.matcher("S5300-28X-LI-24S-DC").find()) {
                type = "MonoSwitch";
                slot = "N/A";
                String rack = "Rack_1";
                pars5300(pathToSnFile, resultArray, region, hostname, ip, chassis, date, parsSN, type, slot);
            }

    /////////////////////////////////////////////////
    //        [Slot_1]
            if (!(patternChassis.matcher("S5300-28X-LI-24S-DC").find()) ||
                    !(patternChassis.matcher("S9312-CSS").find())) {
                for (int i = 0; i < fullSlots.size(); i++) {
                    String line = fullSlots.get(i);
                    type = "Board";
                    slot = "Slot_" + line;
                    Pattern pattStart = Pattern.compile("\\[Slot_" + line + "\\]");
                    Pattern pattEnd = Pattern.compile("^BOM=$");
                    parsSlot(pathToSnFile, resultArray, region, hostname, ip, chassis, date,
                            parsSN, pattStart, pattEnd, type, slot);

                }
            }
        }
    }

    private static void pars5300 (String pathToSnFile, ArrayList<String> resultArray, String region,
                                  String hostname, String ip, String chassis, String date, ParsSN parsSN,
                                  String type, String slot) {
        String boardType = "N/A";
        String barCode = "N/A";
        String description = "N/A";

        Pattern pattStart = Pattern.compile("\\[Board Properties\\]");
        Pattern pattEnd = Pattern.compile("^BOM=$");
        ArrayList<String> blockSlot = parsSN.findBlockConfig(resultArray, pattStart, pattEnd);

        boardType = getParams(parsSN, blockSlot, "BoardType");
        barCode = getParams(parsSN, blockSlot, "BarCode");
        description = getParams(parsSN, blockSlot, "Description");

//        System.out.println("boardType - " + boardType );


        writeInFileSn(pathToSnFile, region, hostname, ip, chassis, date, type, slot, boardType, barCode, description);

    }


    private static void parsSlot(String pathToSnFile, ArrayList<String> resultArray,
                                 String region, String hostname, String ip, String chassis, String date,
                                 ParsSN parsSN, Pattern pattStart, Pattern pattEnd, String type, String slot) {
        String boardType;
        String barCode;
        String description;



        ArrayList<String> blockSlot = parsSN.findBlockConfig(resultArray, pattStart, pattEnd);
//     Get "BoardType", "BarCode", "Description"
        boardType = getParams(parsSN, blockSlot, "BoardType");
        barCode = getParams(parsSN, blockSlot, "BarCode");
        description = getParams(parsSN, blockSlot, "Description");

        writeInFileSn(pathToSnFile, region, hostname, ip, chassis, date, type, slot, boardType, barCode, description);
    }

    private static void rack1(Boolean debug, String pathToSnFile, ArrayList<String> resultArray, String region,
                              String hostname, String ip, String chassis, String date, ParsSN parsSN,
                              String type, String slot, String rack) {
        String boardType;
        String barCode;
        String description;
        String resultLine;
        String[] resultLineArr;
        //////////////////////////////////////////////////
//      [Rack_1]


        Pattern pattStart = Pattern.compile("\\[" + rack + "\\]");
        Pattern pattEnd = Pattern.compile("^BOM=$");
        ArrayList<String> blockRack = parsSN.findBlockConfig(resultArray, pattStart, pattEnd);

//      [Rack_1] -> [Board Properties]
        pattStart = Pattern.compile("\\[Board Properties\\]");
        pattEnd = Pattern.compile("^BOM=$");
        ArrayList<String> blockRackBoardProperties = parsSN.findBlockConfig(blockRack, pattStart, pattEnd);

//      Get "BoardType", "BarCode", "Description"
//      [Rack_1] -> [Board Properties] find "BoardType"
        boardType = getParams(parsSN, blockRackBoardProperties, "BoardType=");

//      [Rack_1] -> [Board Properties] find "BarCode"
        barCode = getParams(parsSN, blockRackBoardProperties, "BarCode=");

//      [Rack_1] -> [Board Properties] find "Description"
        description = getParams(parsSN, blockRackBoardProperties, "Description=");

        writeInFileSn(pathToSnFile, region, hostname, ip, chassis, date, type, slot, boardType, barCode, description);
    }

    private static void writeInFileSn(String pathToSnFile, String region, String hostname, String ip, String chassis, String date, String type, String slot, String boardType, String barCode, String description) {
        String resultLine;
        String[] resultLineArr;
        resultLine = type + ";" + slot + ";" + boardType + ";" + barCode + ";" + description +
                ";" + region + ";" + hostname + ";" + ip + ";" + chassis + ";" + date;
        resultLineArr = new String[1];
        resultLineArr[0] = resultLine;
        WorkWithFile.writeFile(pathToSnFile, resultLineArr);
    }

    private static String getParams(ParsSN parsSN, ArrayList<String> blockRackBoardProperties, String regexp) {
        Pattern pattElement;
        String boardType;
        pattElement = Pattern.compile(regexp);
        boardType = parsSN.parsBlockConfig(blockRackBoardProperties, pattElement);

        return boardType;
    }


}
