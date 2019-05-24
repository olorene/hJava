package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ParsSN {

    public String identifyIp(String nameOfFile) {
        String ip = "N/A";

        String[] fullNameFile =  nameOfFile.trim().split("\\.log" );
        ip = fullNameFile[0];

        return ip;
    }

    public String identifyRegion(String nameRegion) {
        nameRegion = nameRegion.trim();
        String regionId = "N/A";
        String vc = "Винница";
        Pattern patVc = Pattern.compile(vc);

        if (patVc.matcher(nameRegion).find()) {
            regionId = "vc";
//            System.out.println(nameRegion + " - " + vc);
        }

        return regionId;
    }



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

    public ArrayList<String> findSlotClaster(ArrayList<String> outDispDevice) {
        ArrayList<String> result = new ArrayList<>();
//                1     -   LE0MG48SC    Present   PowerOn    Registered     Normal     NA
        Pattern patternRegex = Pattern.compile("^\\d.+\\s.+-");
        Pattern patternChassis1 = Pattern.compile("Chassis 1");
        Pattern patternChassis2 = Pattern.compile("Chassis 2");
        Boolean chassis1 = false;
        Boolean chassis2 = false;

        for (int i = 0; i < outDispDevice.size(); i++) {
            String line = outDispDevice.get(i);
            if (patternChassis1.matcher(line).find()) {
                chassis1 = true;
            }
            if (patternChassis2.matcher(line).find()) {
                chassis1 = false;
                chassis2 = true;
            }
            if (patternRegex.matcher(line).find()) {
                String numSlot = "N/A";
                String[] parts = line.split("\\s+");
                numSlot = parts[0].trim();
                if (chassis1 == true) {
                    numSlot = "Chassis 1;" + numSlot;
                }
                if (chassis2 == true) {
                    numSlot = "Chassis 2;" + numSlot;

                }
                result.add(numSlot);
            }
        }

        return result;
    }

    public ArrayList<String> findSlotDispDevice(ArrayList<String> outDispDevice) {

        ArrayList<String> result = new ArrayList<>();
//                1     -   LE0MG48SC    Present   PowerOn    Registered     Normal     NA
        Pattern patternRegex = Pattern.compile("^\\d.+\\s.+-");


        for (int i = 0; i < outDispDevice.size(); i++) {
            String line = outDispDevice.get(i).trim();

            if (patternRegex.matcher(line).find()) {

                String[] parts = line.split("\\s+");
                result.add(parts[0].trim());
//                System.out.println(parts[0]);
            }
        }

        return result;
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
        String[] output = new String[2];
        String result = "N/A";
        for (int i = 0; i < blockConfig.size(); i++) {
            if (pattern.matcher(blockConfig.get(i)).find()) {
                output = blockConfig.get(i).trim().split("=");
                if (output.length != 2 ) {
                    result = "N/A";
                } else {
                    result = output[1];
                }

                break;
            }
        }

        return result;
    }

}



