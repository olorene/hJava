package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TestDriveParsSN2019 {
    public static void main(String[] args) {
        ArrayList<String> resultTabel = null;
        String pathToFolder = "D:\\tmp\\Inventarizaciya_Huawei_11_10_2019";
        String pathToSnFile = "D:\\tmp\\SerialNumberHuaweiSwitch2019.csv";

        ArrayList<String> showFolder = WorkWithFile.showDir(pathToFolder);
        ArrayList<String> parsedPathToFile = new ArrayList<String>();

        for (int i = 0; i < showFolder.size(); i++) {
            String pathToFile = showFolder.get(i);

            //Processed string
            //D:\tmp\Inventarizaciya_Huawei_11_10_2019\Винница\10.171.1.1.log
            String[] pathOfFile = showFolder.get(i).trim().split("\\\\");


            String ip = getIpDevice(pathOfFile[4]);
            String region = pathOfFile[3];
            ArrayList<String> cliOutput = getCliOutput(pathToFile);

            ParsSN2019.headreFileSn(pathToSnFile);
            String hostname = ParsSN.hostname(cliOutput);

            String typeDevice = getDeviceType(hostname);
            System.out.println(typeDevice);

            ArrayList<String> numSlots = ParsSN.findSlotDispDevice(cliOutput);
//            for (String line :
//                                    numSlots) {
//                                System.out.println(line);
//                            }

            System.out.println("===========================================================");
            // FOR 9312-CSS
            if (typeDevice.equals("S9312-CSS0")) {
                parsBoard9312Scc(ip, region, cliOutput, hostname, numSlots, typeDevice);
            }
            System.out.println("===========================================================");
            //FOR 9303
            if (typeDevice.equals("S9303")) {
                parsBoard9303(ip, region, cliOutput, hostname, typeDevice, numSlots);
            }

        }

    }

    public static void parsBoard9303(String ip, String region, ArrayList<String> cliOutput, String hostname, String typeDevice, ArrayList<String> numSlots) {
        String type2 = "SubRack_1";
        parsBackPlane9312CSS(ip, region, cliOutput, hostname, typeDevice, type2, "BackPlane", 1);
        for(String slot: numSlots) {
            //      [Slot_1] -> BOM=
            String regSlot = "\\[Slot_" + slot + "\\]$";
            Pattern pattStart = Pattern.compile(regSlot);
            Pattern pattEnd = Pattern.compile("BOM=");
            ArrayList<String> blockSlot = ParsSN.findBlockConfigSolot(cliOutput, pattStart, pattEnd);

            /**
             * Get BoardType, BarCode, Description
             */
            //      [Board Properties] -> To an empty line after BOM= find "BoardType"
            String regexBoardType2 = "BoardType=";
            String boardType2 = getValueFormCliOutput(blockSlot, regexBoardType2);

            //      [Board Properties] -> To an empty line after BOM= find "BarCode"
            String regexBareCode2 = "BarCode=";
            String barCode2 = getValueFormCliOutput(blockSlot, regexBareCode2);

            //      [Board Properties] -> To an empty line after BOM= find "Description"
            String regexDescription2 = "Description=";
            String description2 = getValueFormCliOutput(blockSlot, regexDescription2);


            System.out.print(type2 + ";");
            System.out.print("Slot_" + slot + ";");
            System.out.print(boardType2 + ";");
            System.out.print(barCode2 + ";");
            System.out.print(description2 + ";");
            System.out.print(region + ";");
            System.out.print(hostname + ";");
            System.out.print(typeDevice + ";");
            System.out.println(ip);
        }
    }

    public static void parsBoard9312Scc(String ip, String region, ArrayList<String> cliOutput, String hostname, ArrayList<String> numSlots, String typeDevice) {
        int subRec = 1;
        String type2 = "SubRack_1";
        String slot2 = "";


        for (String line : numSlots) {
            Pattern patternReg = Pattern.compile("Chassis");
//                Pattern patternRegSubBoard = Pattern.compile("SubBoard");
            if (patternReg.matcher(line.trim()).find()){
                if (subRec == 1) {
                    parsBackPlane9312CSS(ip, region, cliOutput, hostname, typeDevice, type2, "BackPlane", subRec);
                    subRec += 1;
                } else {
                    parsBackPlane9312CSS(ip, region, cliOutput, hostname, typeDevice, type2, "BackPlane", subRec);
                    type2 = "SubRack_2";
                }
                continue;
            } /*else if (patternRegSubBoard.matcher(line.trim()).find()) {

            }*/ else {
                slot2 = line.trim();
            }

            //      [Slot_1] -> BOM=
            String regSlot = "\\[Slot_" + slot2 + "\\]$";
            Pattern pattStart = Pattern.compile(regSlot);
            Pattern pattEnd = Pattern.compile("BOM=");
            ArrayList<String> blockSlot = ParsSN.findBlockConfigSolot(cliOutput, pattStart, pattEnd);

            /**
             * Get BoardType, BarCode, Description
             */
            //      [Board Properties] -> To an empty line after BOM= find "BoardType"
            String regexBoardType2 = "BoardType=";
            String boardType2 = getValueFormCliOutput(blockSlot, regexBoardType2);

            //      [Board Properties] -> To an empty line after BOM= find "BarCode"
            String regexBareCode2 = "BarCode=";
            String barCode2 = getValueFormCliOutput(blockSlot, regexBareCode2);

            //      [Board Properties] -> To an empty line after BOM= find "Description"
            String regexDescription2 = "Description=";
            String description2 = getValueFormCliOutput(blockSlot, regexDescription2);


            System.out.print(type2 + ";");
            System.out.print("Slot_" + slot2 + ";");
            System.out.print(boardType2 + ";");
            System.out.print(barCode2 + ";");
            System.out.print(description2 + ";");
            System.out.print(region + ";");
            System.out.print(hostname + ";");
            System.out.print(typeDevice + ";");
            System.out.println(ip);
        }
    }

    public static void parsBackPlane9312CSS(String ip, String region, ArrayList<String> cliOutput,
                                            String hostname, String typeDevice, String type2, String slot2, int subRec) {
        String regBackPlane1 = "\\[BackPlane_" + subRec + "\\]$";
        Pattern pattStartBackPlane = Pattern.compile(regBackPlane1);
        Pattern pattEndBackPlane = Pattern.compile("BOM=");
        ArrayList<String> blockregBackPlane1 = ParsSN.findBlockConfigSolot(cliOutput, pattStartBackPlane, pattEndBackPlane);

        /**
         * Get BoardType, BarCode, Description
         */
        //      [Board Properties] -> To an empty line after BOM= find "BoardType"
        String regexBoardType2 = "BoardType=";
        String boardType2 = getValueFormCliOutput(blockregBackPlane1, regexBoardType2);

        //      [Board Properties] -> To an empty line after BOM= find "BarCode"
        String regexBareCode2 = "BarCode=";
        String barCode2 = getValueFormCliOutput(blockregBackPlane1, regexBareCode2);

        //      [Board Properties] -> To an empty line after BOM= find "Description"
        String regexDescription2 = "Description=";
        String description2 = getValueFormCliOutput(blockregBackPlane1, regexDescription2);


        System.out.print(type2 + ";");
        System.out.print(slot2 + ";");
        System.out.print(boardType2 + ";");
        System.out.print(barCode2 + ";");
        System.out.print(description2 + ";");
        System.out.print(region + ";");
        System.out.print(hostname + ";");
        System.out.print(typeDevice + ";");
        System.out.println(ip);
    }

    public static String getDeviceType(String hostname) {
        //Pars type device from hostname
        String[] arrHostname = hostname.trim().split("-");
        boolean startTypeValue = false;
        String typeDevice = "N/A";
        for (String value :
                arrHostname) {
            Pattern patternHw = Pattern.compile("HW");
            if (patternHw.matcher(value.trim()).find()){
                startTypeValue = true;
                typeDevice = "";
            } else if (startTypeValue == true && typeDevice == "") {
                typeDevice = value;
            } else if ((startTypeValue == true && value.equals("CSS0") )) {
                typeDevice = typeDevice + "-" + value;
            }
        }
        return typeDevice;
    }

    public static String getValueFormCliOutput(ArrayList<String> blockRackBoardProperties, String regexBoardType) {
        Pattern pattElement = Pattern.compile(regexBoardType);
        return ParsSN.parsBlockConfig(blockRackBoardProperties, pattElement);
    }

    public static ArrayList<String> getCliOutput(String pathToFile) {
        /**
         * Get the contents of the CLI output file from the device
         */
        String[] tmpCliOutput = WorkWithFile.openFile(pathToFile);
        return new ArrayList<>(Arrays.asList(tmpCliOutput));
    }

    public static String getIpDevice(String s) {
        /**
         * Get IP device from file path
         * D:\tmp\Inventarizaciya_Huawei_11_10_2019\Винница\10.171.1.1.log
         * output - 10.171.1.1
         */
        String[] fullNameOfFile = s.trim().split("\\.");
        return fullNameOfFile[0] + "." +
                fullNameOfFile[1] + "." +
                fullNameOfFile[2] + "." +
                fullNameOfFile[3];
    }


}
