package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriteData {

    public static void main(String[] args) throws IOException {

        // Create workbook
        Workbook workbook = new XSSFWorkbook();

        // Create sheet
        Sheet sheet = workbook.createSheet("LoginData");

        // Excel test data
        String[][] data = {
            {"username", "password", "result"},
            {"kiran325@gmail.com", "Myname@24", "Valid"},
            {"lakshimi@yahoo.com", "Laxmi", "Valid"},
            {"laksh@yahoo.com", "Lakshami", "Invalid"},
            {"laks@yahoo.com", "xyz", "Invalid"},
            {"abc123@gmail.com", "test@123", "Valid"},
            {"  "," ", "Invalid"},
            {"abc123@gmail.com"," ", "Invalid"},
            {"","test@123", "Invalid"}
        };

        // Write data into Excel
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i);

            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);

                cell.setCellValue(data[i][j]);
            }
        }

        // Automatically adjust column width
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        // Create testData folder if it doesn't exist
        Files.createDirectories(Paths.get("testData"));

        // Save Excel file
        try (FileOutputStream file = new FileOutputStream("testData/LoginData.xlsx")) {
            workbook.write(file);
        }

        workbook.close();
        System.out.println("Excel file created successfully!");
    }
}
