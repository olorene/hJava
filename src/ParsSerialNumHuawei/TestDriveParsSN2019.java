package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TestDriveParsSN2019 {
    public static void main(String[] args) {
        ArrayList<String> resultTabel = null;
        String pathToFolder = "D:\\tmp\\Inventarizaciya_Huawei_11_10_2019";
        String pathToSnFile = "D:\\tmp\\SerialNumberHuaweiSwitch2019.csv";

        ArrayList<String> showFolder = WorkWithFile.showDir(pathToFolder);
        ArrayList<String> dataForWrite = new ArrayList<String>();

        iniciateWriteInFileSN(pathToSnFile);


        for (int i = 0; i < showFolder.size(); i++) {
            String pathToFile = showFolder.get(i);

            //Processed string
            String[] pathOfFile = showFolder.get(i).trim().split("\\\\");


            String ip = getIpDevice(pathOfFile[4]);
            String region = pathOfFile[3];
            ArrayList<String> cliOutput = getCliOutput(pathToFile);

            String hostname = ParsSN.hostname(cliOutput);

            String typeDevice = getDeviceType(hostname);
//            System.out.println(region + " - " + ip + " - " + typeDevice);

            ArrayList<String> numSlots = ParsSN.findSlotDispDevice(cliOutput);
//            for (String line :
//                                    numSlots) {
//                                System.out.println(line);
//                            }



            // FOR S9312
            if (typeDevice.equals("S9312") || typeDevice.equals("s9312")) {
                System.out.println(region + " - " + ip + " - " + typeDevice);
                dataForWrite = parsBoardHuawei(ip, region, cliOutput, hostname, typeDevice, numSlots,
                        pathToSnFile, dataForWrite);
            }


            // FOR 9306
            if (typeDevice.equals("S9306")) {
                System.out.println(region + " - " + ip + " - " + typeDevice);
                dataForWrite = parsBoardHuawei(ip, region, cliOutput, hostname, typeDevice, numSlots,
                        pathToSnFile, dataForWrite);
            }


            // FOR 9303
            if (typeDevice.equals("S9303")) {
                System.out.println(region + " - " + ip + " - " + typeDevice);
                dataForWrite = parsBoardHuawei(ip, region, cliOutput, hostname,
                        typeDevice, numSlots, pathToSnFile, dataForWrite);
            }


            // FOR S5328
            if (typeDevice.equals("S5328C") || typeDevice.equals("S5328c") || typeDevice.equals("s5328c")) {
                System.out.println(region + " - " + ip + " - " + typeDevice);
                dataForWrite = parsBoardHuawei(ip, region, cliOutput, hostname,
                        typeDevice, numSlots, pathToSnFile, dataForWrite);
            }


            // FOR 5300
            if (typeDevice.equals("S5300")) {
                System.out.println(region + " - " + ip + " - " + typeDevice);
                dataForWrite = parsBoardHuawei(ip, region, cliOutput, hostname,
                        typeDevice, numSlots, pathToSnFile, dataForWrite);
            }

            // FOR 9312-CSS
            if (typeDevice.equals("S9312-CSS")) {
                System.out.println(region + " - " + ip + " - " + typeDevice);
                dataForWrite = parsBoard9312Scc(ip, region, cliOutput, hostname, numSlots,
                        typeDevice, pathToSnFile, dataForWrite);
            }
        }

        //Write into the file
        String[] dataArrayFoWrite = dataForWrite.toArray(new String[dataForWrite.size()]);
        WorkWithFile.writeFile(pathToSnFile, dataArrayFoWrite);

    }

    public static void writeDataInFileSN(String pathToSnFile, String row) {
        String[] resultLineArr = new String[1];
        resultLineArr[0] = row;
        WorkWithFile.appendInFile(pathToSnFile, resultLineArr);

    }

    public static void iniciateWriteInFileSN(String pathToSnFile) {
        WorkWithFile.deleteFile(pathToSnFile);
        String resultLine = "Type" + ";" + "Slot" + ";" + "BoardTyp" + ";" + "BarCode" + ";" +
                "Region" + ";" + "Hostname" + ";" + "Soft_Version" + ";" + "TypeDevice" + ";" +
                        "Ip" + ";" + "Description" + ";";
        String[] resultLineArr = new String[1];
        resultLineArr[0] = resultLine;
        WorkWithFile.writeFile(pathToSnFile, resultLineArr);
    }

    public static String getVersionSoftware(ArrayList<String> cliOutput) {
        String regVersionStart = "display\\s*version";
        String regVersionEnd = ">q";
        Pattern patternStartVersion = Pattern.compile(regVersionStart);
        Pattern patternEndVersion = Pattern.compile(regVersionEnd);
        ArrayList<String> blockVersion = ParsSN.findBlockConfig(cliOutput, patternStartVersion, patternEndVersion);
        String versionSoftDevice = "N/A";
//        System.out.println(";aslkj;asdfj;asdj - " + blockVersion.size() + " - " + patternStartVersion.toString() + " - " + patternEndVersion.toString());
        for (String line: blockVersion){
//            String regLineVerison = "VRP \\(R\\) software,";
            String regLineVerison = "VRP\\s*\\(R\\)";
            Pattern patternVersion = Pattern.compile(regLineVerison);
            if (patternVersion.matcher(line.trim()).find()) {
//                    System.out.println(line);
                String[] tmpVersion = line.split(",");
                versionSoftDevice = tmpVersion[1];
//                System.out.println(versionSoftDevice);
                break;
            }
        }

        return versionSoftDevice.trim();
    }

    public static String getVersionSoftwareCSS(ArrayList<String> cliOutput) {
//        String regVersionStart = "display\\s*version slot 0\\/0";
        String regVersionStart = "display\\s*version";
        String regVersionEnd = ">q";
        Pattern patternStartVersion = Pattern.compile(regVersionStart);
        Pattern patternEndVersion = Pattern.compile(regVersionEnd);
        ArrayList<String> blockVersion = ParsSN.findBlockConfig(cliOutput, patternStartVersion, patternEndVersion);
        String versionSoftDevice = "N/A";
        for (String line: blockVersion){
//                System.out.println(line);
            String regLineVerison = "VRP \\(R\\) software,";
            Pattern patternVersion = Pattern.compile(regLineVerison);
            if (patternVersion.matcher(line.trim()).find()) {
//                    System.out.println(line);
                String[] tmpVersion = line.split(",");
                versionSoftDevice = tmpVersion[1];
//                System.out.println(versionSoftDevice);
                break;
            }
        }

        return versionSoftDevice.trim();
    }

    public static ArrayList<String> parsBoardHuawei(String ip, String region, ArrayList<String> cliOutput, String hostname,
                                                    String typeDevice, ArrayList<String> numSlots, String pathToSnFile,
                                                    ArrayList<String> dataForWrite) {

        String type2 = "SubRack_1";
        if (typeDevice.equals("S5300") == false &&  typeDevice.equals("S5328C") == false
                && typeDevice.equals("S5328c") == false && typeDevice.equals("s5328c") == false) {

            dataForWrite = parsBackPlane9300Chassis(ip, region, cliOutput, hostname, typeDevice, type2,
                    "BackPlane", 1, dataForWrite);
        }



        String versionSoftware = getVersionSoftware(cliOutput);
//        System.out.println(versionSoftware);

        for(String slot: numSlots) {
            //      [Slot_1] -> BOM=
//            System.out.println(";asdjf;sdjf;aksdjf;pioweorjn;iux + " + slot);
            String regSlot = "\\[Slot_" + slot + "\\]$";
            Pattern pattStart = Pattern.compile(regSlot);
            Pattern pattEnd = Pattern.compile("BOM=");
//            System.out.println("997et-09835tk;j + " + cliOutput.size());
            ArrayList<String> blockSlot = ParsSN.findBlockConfigSolot(cliOutput, pattStart, pattEnd);
//            System.out.println("inuhmny85yoo345khl + " + blockSlot.size());
            String resultRow = getBoardTypeBarCodeDescription(ip, region, hostname, typeDevice, type2, versionSoftware, slot, blockSlot);

            dataForWrite.add(resultRow);
        }
        return dataForWrite;
    }

    public static String getBoardTypeBarCodeDescription(String ip, String region, String hostname, String typeDevice, String type2, String versionSoftware, String slot, ArrayList<String> blockSlot) {
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


//            System.out.println(versionSoftware);
        return type2 + ";" + "Slot_" + slot + ";" + boardType2 + ";" + barCode2 + ";" +
                region + ";" + hostname + ";" + versionSoftware + ";" + typeDevice + ";" +
                ip + ";" + description2 + ";";
    }

    public static ArrayList<String> parsBoard9312Scc(String ip, String region, ArrayList<String> cliOutput,
                                        String hostname, ArrayList<String> numSlots,
                                        String typeDevice, String pathToSnFile, ArrayList<String>  dataForWrite) {
        int subRec = 1;
        String type2 = "SubRack_1";
        String slot2 = "";

        String softVersion = getVersionSoftwareCSS(cliOutput);

        for (String line : numSlots) {
            Pattern patternReg = Pattern.compile("Chassis");
//                Pattern patternRegSubBoard = Pattern.compile("SubBoard");
            if (patternReg.matcher(line.trim()).find()){
                if (subRec == 1) {
                    dataForWrite = parsBackPlane9300Chassis(ip, region, cliOutput, hostname, typeDevice, type2,
                            "BackPlane", subRec, dataForWrite);
                    subRec += 1;
                } else {
                    type2 = "SubRack_2";
                    dataForWrite = parsBackPlane9300Chassis(ip, region, cliOutput, hostname, typeDevice, type2,
                            "BackPlane", subRec, dataForWrite);
                }
                continue;
            } else {
                slot2 = line.trim();
            }


            ArrayList<String> cliOutputSubRack = new ArrayList<String>();
            if (type2.equals("SubRack_2")){
//                System.out.println("asodfjasld;fkja;sdfjk + ");
                String regSubRack2Start = "\\[SubRack_2\\]";
                String regSubRack2SEnd = "<\\w*-";
                Pattern pattSubRack2Start = Pattern.compile(regSubRack2Start);
                Pattern pattSubRack2End = Pattern.compile(regSubRack2SEnd);
                cliOutputSubRack = ParsSN.findBlockConfig(cliOutput, pattSubRack2Start, pattSubRack2End);

            } else if (type2.equals("SubRack_1")){
                String regSubRack1Start = "\\[SubRack_1\\]";
                String regSubRack1SEnd = "\\[SubRack_2\\]";
                Pattern pattSubRack1Start = Pattern.compile(regSubRack1Start);
                Pattern pattSubRack1End = Pattern.compile(regSubRack1SEnd);
                cliOutputSubRack = ParsSN.findBlockConfig(cliOutput, pattSubRack1Start, pattSubRack1End);
//                System.out.println("2019231010460000 + " + cliOutputSubRack.size());
//                System.out.println("2019231010470000 + " + cliOutput.size());
//                System.out.println("-------------------------");
//                for (String line2019231010470000 : cliOutputSubRack
//                ) {
//                    System.out.println("2019231010480000 + " + line2019231010470000);
//                }
//                System.out.println("-------------------------");
            }

            //      [Slot_1] -> BOM=
            String regSlot = "\\[Slot_" + slot2 + "\\]$";
            Pattern pattStart = Pattern.compile(regSlot);
            Pattern pattEnd = Pattern.compile("BOM=");
            ArrayList<String> blockSlot = ParsSN.findBlockConfigSolot(cliOutputSubRack, pattStart, pattEnd);
//            System.out.println("---------------");
//            for (String v20192310 : blockSlot
//            ) {
//                System.out.println("201923101056 + " + v20192310);
//            }
//            System.out.println("---------------");
//            System.out.println("20192310104900 + " + "Slot_" + slot2 + " - " + pattStart.toString() + " - " + pattEnd.toString() + " - " + blockSlot.size());


            //      [Board Properties] -> To an empty line after BOM= find "BoardType"
            String resultRow = getBoardTypeBarCodeDescription(ip, region, hostname, typeDevice,
                    type2, softVersion, slot2, blockSlot);

            dataForWrite.add(resultRow);

            // [Slot_14] ->
            // BOM=
            //
            //
            //
            //[Port_0]

            if (slot2.equals("13") || slot2.equals("14")) {
                String regStartDouter = "\\[Slot_" + slot2 + "\\]$";
                String regEndDouter = "\\[Port_0\\]";
                Pattern pattDouterStart = Pattern.compile(regStartDouter);
                Pattern pattDouterEnd = Pattern.compile(regEndDouter);
                ArrayList<String> blockSlotWithDouter = ParsSN.
                        findBlockConfig(cliOutputSubRack, pattDouterStart, pattDouterEnd);

                String regStartDouterSn = "\\[Daughter_Board_1\\]";
                String regEndDouterSn = "BOM=";
                Pattern pattDouterSnStart = Pattern.compile(regStartDouterSn);
                Pattern pattDouterSnEnd = Pattern.compile(regEndDouterSn);
                ArrayList<String> blockDouter = ParsSN.findBlockConfig(blockSlotWithDouter,
                        pattDouterSnStart, pattDouterSnEnd);
                String slotDoughter = slot2 + "_Daughter";
                String resultDoughter = getBoardTypeBarCodeDescription(ip, region, hostname, typeDevice,
                        type2, softVersion, slotDoughter, blockDouter);

                dataForWrite.add(resultDoughter);

//                System.out.println("20192310105300 + " + "Slot_" + slot2 + " - " + blockDouter.size());
//                System.out.println("---------------");
//                for (String v20192310 : blockDouter
//                ) {
//                    System.out.println("201923101101 + " + v20192310);
//                }
//                System.out.println("---------------");

            }
        }
        return dataForWrite;
    }

    public static ArrayList<String> parsBackPlane9300Chassis(String ip, String region, ArrayList<String> cliOutput,
                                                             String hostname, String typeDevice, String type2,
                                                             String slot2, int subRec, ArrayList<String> dataForWrite) {

        String regBackPlane1 = "\\[BackPlane_" + subRec + "\\]$";
        Pattern pattStartBackPlane = Pattern.compile(regBackPlane1);
        Pattern pattEndBackPlane = Pattern.compile("BOM=");
        ArrayList<String> blockregBackPlane1 = ParsSN.findBlockConfigSolot(cliOutput, pattStartBackPlane, pattEndBackPlane);
        String softVersion = getVersionSoftwareCSS(cliOutput);

        /**
         * Get BoardType, BarCode, Description
         */
        //      [Board Properties] -> To an empty line after BOM= find "BoardType"
        String resultRow = getBoardTypeBarCodeDescription(ip, region, hostname, typeDevice, type2,
                softVersion, slot2, blockregBackPlane1);

        dataForWrite.add(resultRow);

        return dataForWrite;
    }

    public static String getDeviceType(String hostname) {
        //Pars type device from hostname
        String[] arrHostname = hostname.trim().split("-");
        boolean startTypeValue = false;
        String typeDevice = "N/A";
        for (String value :
                arrHostname) {
//            System.out.println("-=======-" + value);
            if (value.equals("CSS0")
                    || value.equals("CSS1") || value.equals("CSS2") || value.equals("CSS3") || value.equals("CSS4")) {
                value = "CSS";
            }
            Pattern patternHw = Pattern.compile("HW");
            Pattern patternHw2 = Pattern.compile("hw");
            if (patternHw.matcher(value.trim()).find() || patternHw2.matcher(value.trim()).find()){
//                System.out.println("-=*" + hostname);
                startTypeValue = true;
                typeDevice = "";
            } else if (startTypeValue == true && typeDevice == "") {
                typeDevice = value;
            } else if ((startTypeValue == true && value.equals("CSS") /*value.equals("CSS0")
                    || value.equals("CSS1") || value.equals("CSS2") || value.equals("CSS3") || value.equals("CSS4")*/)) {
                typeDevice = typeDevice + "-" + value;
            }
        }
//        System.out.println("++++++++- " + hostname + " -=- " + typeDevice);
        return typeDevice;
    }

    public static String getValueFormCliOutput(ArrayList<String> blockRackBoardProperties, String regexBoardType) {
        Pattern pattElement = Pattern.compile(regexBoardType);
//        System.out.println(";laksdjfas;ldjf + " + blockRackBoardProperties.size() + " -=- " + pattElement.toString() );
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
