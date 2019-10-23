package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ParsSN {
    public static ArrayList<String> outDisplayDevice(String pathToFile) {
//        String pathToFile = "D:\\tmp\\Inventarizaciya_93xx_2017\\Івано-Франківськ\\10.171.8.1.txt";
        String[] result = WorkWithFile.openFile(pathToFile);
        ArrayList<String> out = new ArrayList<String>();

//        <vc-HW-S9312-CSS0>display device
//        <vc-HW-S9312-CSS0>display device
//        <vc-HW-S9312-CSS0>display elabel
        String dispDevice = "display device$";
        Pattern patternDispDevice = Pattern.compile(dispDevice);

        String dispElabel = "display elabel$";
        Pattern patternDispElabel = Pattern.compile(dispElabel);

        Boolean stratDisplayDevice = false;
        Boolean endDisplayDevice = false;
        for (int i = 0; i < result.length; i++) {
//            result[i] = result[i].trim();
            if (patternDispDevice.matcher(result[i]).find()) {
                stratDisplayDevice = true;
            }
            if (patternDispElabel.matcher(result[i]).find()) {
                endDisplayDevice = true;
            }
            if (stratDisplayDevice == true && endDisplayDevice == false) {
                System.out.println(result[i]);
                out.add(result[i]);
            }

        }

        return out;
    }

    public static  ArrayList<String> findSlotDispDevice(ArrayList<String> outDispDevice) {
//        String[] result = WorkWithFile.openFile(pathToFile);
        ArrayList<String> out = new ArrayList<String>();

//        12    -
        Pattern patternRegex = Pattern.compile("^\\d.+\\s*-");
//        Pattern patternRegexSubBoard = Pattern.compile("^\\s+\\d\\s+\\w+");
        Pattern patternChassis = Pattern.compile("Chassis\\s\\d");

        for (int i = 0; i < outDispDevice.size(); i++) {
            String trimLine = outDispDevice.get(i).trim();
            outDispDevice.set(i, trimLine);
            if (patternRegex.matcher(outDispDevice.get(i)).find() ||
                    patternChassis.matcher(outDispDevice.get(i)).find()) {

                String[] arrOutDispDevice = outDispDevice.get(i).trim().split("\\s");
                out.add(arrOutDispDevice[0]);
//                System.out.println(outDispDevice.get(i));
//                System.out.println(arrOutDispDevice[0]);
            }
//            if (patternRegexSubBoard.matcher(outDispDevice.get(i)).find()) {
//                out.add("SubBoard");
//            }

        }

        return out;
    }

    public static ArrayList<String> findBlockConfig(ArrayList<String> resultArray, Pattern pattStart, Pattern pattEnd) {
        Boolean beginBlock = false;
        ArrayList<String> output = new ArrayList<String>();

        for (int i = 0; i < resultArray.size(); i++) {
            //            Process block
            if (pattStart.matcher(resultArray.get(i).trim()).find()) {
                beginBlock = true;
            }
            if (pattEnd.matcher(resultArray.get(i).trim()).find()) {
                beginBlock = false;
//                break;
            }

            if (beginBlock == true) {
//                System.out.println(resultArray.get(i));
                output.add(resultArray.get(i));
            }
        }

        return output;
    }

    public static ArrayList<String> findBlockConfigSolot(ArrayList<String> resultArray, Pattern pattStart, Pattern pattEnd) {
        Boolean beginBlock = false;
        Boolean endBlod = false;
        ArrayList<String> output = new ArrayList<String>();
//        System.out.println("ijlou856p4uioojioeufg + " +  resultArray.size());
        for (int i = 0; i < resultArray.size(); i++) {
            //            Process block
//            System.out.println("pwiorj;slkcjpjo;slkjcv + " + resultArray.get(i) + " -=- " + pattEnd.toString());
            if (pattStart.matcher(resultArray.get(i).trim()).find()) {
                beginBlock = true;
            }
            if (beginBlock ==  true && pattEnd.matcher(resultArray.get(i).trim()).find()) {
                endBlod = true;
//                break;
            }

            if (beginBlock == true) {
//                System.out.println(resultArray.get(i));
                output.add(resultArray.get(i));
                if (endBlod == true) {
                    beginBlock = false;
                    endBlod = false;
                }
            }
        }

        return output;
    }

    public static String parsBlockConfig(ArrayList<String> blockConfig, Pattern pattern) {
        String[] output = null;
        String outString = "N/A";
//        System.out.println("15919159 + " + blockConfig.size());
        for (int i = 0; i < blockConfig.size(); i++) {
//            System.out.println("-=lasdkjfhljkv=-" + blockConfig.get(i));
            if (pattern.matcher(blockConfig.get(i)).find()) {
//                System.out.println("-=111111111111111=-" + blockConfig.get(i));
                output = blockConfig.get(i).trim().split("=");
//                System.out.println("333333333333333" + output[1]);
                break;
            }
        }
//        System.out.println("+++++++++++" + output[0] + "=-=-=" + output[1]);
        if (output.length == 2) {
            outString = output[1];
        } else {
            outString = "N/A";
        }

        return outString;
    }

    public static String hostname(ArrayList<String> cliOutput) {
        Pattern pattDispDev = Pattern.compile("display device");
        String[] output = null;
        for (int j = 0; j < cliOutput.size(); j++) {
            if (pattDispDev.matcher(cliOutput.get(j)).find()) {
                output = cliOutput.get(j).trim().split(">");
                output = output[0].trim().split("<");

                break;
            }
        }

        return output[1];
    }
}



