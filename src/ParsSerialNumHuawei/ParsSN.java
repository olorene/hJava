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
        String regex = "^\\d.+\\s.+-";
        Pattern patternRegex = Pattern.compile(regex);

        String chassis9312 = "Chassis";
        Pattern patternChassis = Pattern.compile(chassis9312);

        for (int i = 0; i < outDispDevice.size(); i++) {
            String trimLine = outDispDevice.get(i).trim();
            outDispDevice.set(i, trimLine);
            if (patternRegex.matcher(outDispDevice.get(i)).find() ||
            patternChassis.matcher(outDispDevice.get(i)).find()) {
                System.out.println(outDispDevice.get(i));
            }
        }


    }

}
