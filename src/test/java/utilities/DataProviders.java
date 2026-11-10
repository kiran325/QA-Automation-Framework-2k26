package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    // Data Provider 1 - Login Data
    @DataProvider(name = "LoginData")
    public static String[][] getData() throws IOException {

        String xlFile = System.getProperty("user.dir") + "/testData/LoginData.xlsx";

        String xlSheet = "LoginData";

        // Get row and column count
        int rows = ExcelUtility.getRowCount(xlFile, xlSheet);
        int cols = ExcelUtility.getCellCount(xlFile, xlSheet, 0);

        // Create 2D array
        String[][] data = new String[rows][cols];

        // Read Excel data, skipping header
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {

                data[i - 1][j] = ExcelUtility.getCellData(xlFile, xlSheet, i, j);
            }
        }

        return data;
    }
}