package ParsSerialNumHuawei;

import WorkWithFle.WorkWithFile;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DeviceList {

    private String pathToFile;
    private String pathToResultFile;
    private ArrayList<String> sourceData = new ArrayList<String>();
    private ArrayList<String> resultData = new ArrayList<String>();

    DeviceList(String aPathToFile) {
        setPathToFile(aPathToFile);
        setSourceData();
    }

    public void setPathToFile(String aPathToFile) {
        Pattern delimeter = Pattern.compile("\\\\");
        if (delimeter.matcher(aPathToFile).find()) {
            pathToFile = aPathToFile;
        }
    }

    public void setPathToResultFile(String aPathToFile) {
        Pattern delimeter = Pattern.compile("\\\\");
        if (delimeter.matcher(aPathToFile).find()) {
            pathToResultFile = aPathToFile;

        }
    }

    private void setSourceData() {
        String[] outputFromFile = WorkWithFile.openFile(pathToFile);
        for (int i = 0; i < outputFromFile.length; i++) {
            sourceData.add(outputFromFile[i]);
        }
    }

    public ArrayList<String> getResultData() {

        return resultData;
    }

    public ArrayList<String> getSourceData() {
        return sourceData;
    }

    public void setSourceData(ArrayList<String> list) {
        sourceData = list;
    }

    public ArrayList<String> getOneDivice(String ip) {
        ArrayList<String> oneDevic = new ArrayList<String>();
        ip = ip.trim() + ";";
        Pattern pattIp = Pattern.compile(ip);

        for (int i = 0; i < sourceData.size(); i++) {
            String aLine = sourceData.get(i);
            if (pattIp.matcher(aLine).find()) {
                oneDevic.add(aLine);
            }
        }

        return oneDevic;

    }

    public String findHostname(String ip) {
        String hostname = "N/A";
        ip = ip.trim();
        ip = ip + ";";
        Pattern pattIp = Pattern.compile(ip);

        for (int i = 0; i < sourceData.size(); i++) {
            String aLine = sourceData.get(i);

            if (pattIp.matcher(aLine).find()) {
                String[] parts = aLine.split(";");
//                Type;Slot;BoardType;BarCode;Description;Region;Hostname;IP;Chassis;Date
                hostname = parts[6];
                break;
            }
        }

        return hostname;
    }

    public String findDate(String ip) {
        String date = "N/A";
        ip = ip.trim() + ";";
        Pattern pattIp = Pattern.compile(ip);
        for (int i = 0; i < sourceData.size(); i++) {
            String aLine = sourceData.get(i);
            if (pattIp.matcher(aLine).find()) {
                String[] parts = aLine.split(";");
                date = parts[parts.length - 1];
                break;
            }
        }

        return date;
    }

    public void setDate(String ip, String date) {

        Pattern pattIp = Pattern.compile(ip + ";");
        for (int i = 0; i < sourceData.size(); i++) {
            if (pattIp.matcher(sourceData.get(i)).find()) {
                String[] parts = sourceData.get(i).split(";");
                String newLine = parts[0];
                for (int j = 1; j < parts.length; j++) {
                    newLine = newLine + ";" + parts[i];
                }
                sourceData.set(i, newLine);
            }
        }
    }
}
