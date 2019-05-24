package excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class CreateWorkBook {
        public static void main(String[] args)throws Exception {
            String fileLocation = "D:\\tmp\\Ser_Num_Devices3.xlsx";

            //Create Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create file system using specific name
            FileOutputStream out = new FileOutputStream(new File(fileLocation));

            //write operation workbook using file out object
            workbook.write(out);
            out.close();
            System.out.println("createworkbook.xlsx written successfully");
        }
}
