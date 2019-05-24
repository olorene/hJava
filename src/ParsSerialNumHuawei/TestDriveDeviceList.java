package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.util.ArrayList;

public class TestDriveDeviceList {
    public static void main(String[] args) {
        String pathToListDevice = "D:\\tmp\\List_Huawei_Switch.txt";
        String pathToListDate = "D:\\tmp\\List_Huawei_Date.txt";
        String pathToResultListDevice = "D:\\tmp\\List_Result_Huawei_Switch.csv";
        WorkWithFile.deleteFile(pathToResultListDevice);

        String[] outputIpDate = WorkWithFile.openFile(pathToListDate);

        DeviceList deviceList = new DeviceList(pathToListDevice);
        deviceList.setPathToResultFile(pathToResultListDevice);

        for (int i = 0; i < outputIpDate.length; i++) {
            String aLine = outputIpDate[i];

            String[] parts = aLine.split(";");
            String ip = parts[1];
            String date = parts[2];

            deviceList.setDate(ip, date);
        }

        printArrayList(deviceList.getSourceData());
//        String ip = "10.171.25.1";
//        String date = "10.05.2019";


//        System.out.println(deviceList.findHostname(ip));
//        printArrayList(deviceList.getOneDivice(ip));


    }

    private static void printArrayList(ArrayList<String> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    private static void printStringArray(String[] sourceDate) {
        for (int i = 0; i < sourceDate.length; i++) {
            String line = sourceDate[i];
            System.out.println(line);
        }
    }
}
