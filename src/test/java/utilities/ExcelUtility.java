package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	// Reusable static stream and POI object variables
		public static FileInputStream fi; 
		public static FileOutputStream fo; 
		public static XSSFWorkbook wb; 
		public static XSSFSheet ws; 
		public static XSSFRow row; 
		public static XSSFCell cell; 
		public static XSSFCellStyle style;
		String xlFile;
		String xlSheet;
		
		public ExcelUtility(String xlFile) {
			this.xlFile=xlFile;
		}
		
		// 1\. Get Total Row Count 
		public static int getRowCount(String xlFile, String xlSheet) throws IOException { 
			fi = new FileInputStream(xlFile); 
			wb = new XSSFWorkbook(fi); 
			ws = wb.getSheet(xlSheet); 
			int rowCount = ws.getLastRowNum(); 
			// Returns 0-based index of last row 
			wb.close(); 
			fi.close(); 
			return rowCount;
		}
		
		// 2\. Get Total Cell/Column Count in a specific Row 
		public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException { 
			fi = new FileInputStream(xlFile); 
			wb = new XSSFWorkbook(fi); 
			ws = wb.getSheet(xlSheet); 
			row = ws.getRow(rowNum); 
			int cellCount = row.getLastCellNum(); 
			// Returns total 1-based count of cells 
			wb.close(); 
			fi.close(); 
			return cellCount; 
			}
		
		// 3\. Read Cell Data as String 
		public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException { 
			fi = new FileInputStream(xlFile); 
			wb = new XSSFWorkbook(fi); 
			ws = wb.getSheet(xlSheet); 
			row = ws.getRow(rowNum); 
			cell = row.getCell(colNum); 
			String data; try { 
				// DataFormatter converts any cell type (Numeric, Formula, Text) to formatted String 
				DataFormatter formatter = new DataFormatter(); 
				data = formatter.formatCellValue(cell); 
				// Alternative approach: data = cell.toString(); 
			} catch (Exception e) 
			{ 
				data = ""; // Return empty string if cell is null or empty 
			} 
			wb.close(); 
			fi.close(); 
			return data; 
			}
			
		// 4\. Write Data into a Cell (Read + Write Combination) 
		public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data) throws IOException { 
			fi = new FileInputStream(xlFile); 
			wb = new XSSFWorkbook(fi); 
			ws = wb.getSheet(xlSheet); 
			row = ws.getRow(rowNum); 
			if (row == null) { 
				row = ws.createRow(rowNum); 
			} 
			cell = row.createCell(colNum); 
			cell.setCellValue(data); 
			fo = new FileOutputStream(xlFile); 
			wb.write(fo); 
			// Save changes to file 
			wb.close(); 
			fi.close(); 
			fo.close(); 
			}
		
		// 5\. Fill Green Background Color (Pass Status) 
		public static void fillGreenColor(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException { 
			fi = new FileInputStream(xlFile); 
			wb = new XSSFWorkbook(fi); 
			ws = wb.getSheet(xlSheet); 
			row = ws.getRow(rowNum); 
			cell = row.getCell(colNum); 
			style = wb.createCellStyle(); 
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex()); 
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
			cell.setCellStyle(style); fo = new FileOutputStream(xlFile); 
			wb.write(fo); 
			wb.close(); 
			fi.close(); 
			fo.close(); 
		    }
		
		// 6\. Fill Red Background Color (Fail Status) 
		public static void fillRedColor(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException { 
			fi = new FileInputStream(xlFile); 
			wb = new XSSFWorkbook(fi); 
			ws = wb.getSheet(xlSheet); 
			row = ws.getRow(rowNum); 
			cell = row.getCell(colNum); 
			style = wb.createCellStyle(); 
			style.setFillForegroundColor(IndexedColors.RED.getIndex()); 
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
			cell.setCellStyle(style); fo = new FileOutputStream(xlFile); 
			wb.write(fo); 
			wb.close(); 
			fi.close(); 
			fo.close(); 
			}
}
