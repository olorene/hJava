package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class TmpExcel {
    public static void main(String[] args) throws Exception {
/*        String fileLocation = "D:\\tmp\\Ser_Num_Devices2.xls";

        TmpExcel tmpExcel = new TmpExcel();

        try {
            tmpExcel.writeIntoExcel(fileLocation);
            tmpExcel.readFromExcel(fileLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */


    }
//https://tproger.ru/translations/how-to-read-write-excel-file-java-poi-example/
    public static void writeIntoExcel(String file) throws FileNotFoundException, IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Birthdays");

        // ��������� ���������� � ����
        Row row = sheet.createRow(0);

        // �� ������� ��� � ���� � ��� �������
        // ��� ����� String, � ���� �������� --- Date,
        // ������� dd.mm.yyyy
        Cell name = row.createCell(0);
        name.setCellValue("John");

        Cell birthdate = row.createCell(1);

        DataFormat format = book.createDataFormat();
        CellStyle dateStyle = book.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        birthdate.setCellStyle(dateStyle);


        // ��������� ��� ���������� � 1900-��
        birthdate.setCellValue(new Date(110, 10, 10));

        // ������ ������ �������
        sheet.autoSizeColumn(1);

        // ���������� �� � ����
        book.write(new FileOutputStream(file));
        book.close();
    }

    public static void readFromExcel(String file) throws IOException{
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("Birthdays");
        HSSFRow row = myExcelSheet.getRow(0);

        if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
            String name = row.getCell(0).getStringCellValue();
            System.out.println("name : " + name);
        }

        if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            Date birthdate = row.getCell(1).getDateCellValue();
            System.out.println("birthdate :" + birthdate);
        }

        myExcelBook.close();

    }
}
