package Serialisable.Parsing;

public class TestDrive {
    public static void main(String[] args) {
        String pathToFile = "C:\\java\\tmp\\testXml.xml";
        OpenReadFile openReadFile = new OpenReadFile();
        openReadFile.go(pathToFile);
    }
}
