package utilities ;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import static helpers.PathHelper.projectPath;

public class ReadExcel {
    private static final String FILE_PATH = projectPath + "data\\DataTestOnplus.xlsx";

    public static Object[][] getExcelData(String sheetName)
    {
        Object[][] data = null;
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new Object[rowCount - 1][colCount];
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            int rowIndex = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                for (int colIndex = 0; colIndex < colCount; colIndex++) {
                    data[rowIndex][colIndex] = row.getCell(colIndex).toString();
                }
                rowIndex++;
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        return data;
    }
}
