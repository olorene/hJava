package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ParsSN {
    public ArrayList<String> outDisplayDevice(String pathToFile) {
//        String pathToFile = "D:\\tmp\\Inventarizaciya_93xx_2017\\Івано-Франківськ\\10.171.8.1.txt";
        String[] result = WorkWithFile.openFile(pathToFile);
        ArrayList<String> out = new ArrayList<String>();

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
//                System.out.println(result[i]);
                out.add(result[i]);
            }

        }

        return out;
    }

    public void findSlotDispDevice(ArrayList<String> outDispDevice) {
//        String[] result = WorkWithFile.openFile(pathToFile);
//        ArrayList<String> out = new ArrayList<String>();

//        12    -
        Pattern patternRegex = Pattern.compile("^\\d.+\\s.+-");
        Pattern patternChassis = Pattern.compile("Chassis");

        for (int i = 0; i < outDispDevice.size(); i++) {
            String trimLine = outDispDevice.get(i).trim();
            outDispDevice.set(i, trimLine);

            if (patternRegex.matcher(outDispDevice.get(i)).find() ||
                    patternChassis.matcher(outDispDevice.get(i)).find()) {
//                System.out.println(outDispDevice.get(i));
            }
        }
    }

    public ArrayList<String> findBlockConfig(ArrayList<String> resultArray, Pattern pattStart, Pattern pattEnd) {
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

    public String parsBlockConfig(ArrayList<String> blockConfig, Pattern pattern) {
        String[] output = null;
        for (int i = 0; i < blockConfig.size(); i++) {
            if (pattern.matcher(blockConfig.get(i)).find()) {
                output = blockConfig.get(i).trim().split("=");

                break;
            }
        }

        return output[1];
    }

}



