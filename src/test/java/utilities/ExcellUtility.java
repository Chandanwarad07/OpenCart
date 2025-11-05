package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellUtility {
    private FileInputStream fis;
    private Workbook workbook;
    
    public ExcellUtility(String filePath) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
    }
    
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum() + 1;
    }
    
    public int getCellCount(String sheetName, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        return row.getLastCellNum();
    }
    
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
    
    public void close() throws IOException {
        workbook.close();
        fis.close();
    }
}