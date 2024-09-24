package utilities ;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcel {
    @DataProvider(name = "Read file excel")
    public Object[][] getData(Method m) throws EncryptedDocumentException, IOException
    {
            String [][] dataTest = new String[1][1];
           try {
               File F = new File("");
               FileInputStream fis = new FileInputStream(F);
               XSSFWorkbook wb = new XSSFWorkbook(fis);
               Sheet sheetName = wb.getSheetAt(0);
               int totalRow = sheetName.getLastRowNum();
               Row rowCell = sheetName.getRow(1);
               int totalCell = rowCell.getLastCellNum();

               DataFormatter format = new DataFormatter();
               dataTest = new String[totalRow][totalCell];
               for (int i = 1; i <= totalRow; i++) {
                   for (int j = 0; j < totalCell; j++) {
                       dataTest[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));

                   }
               }
           }
           catch (FileNotFoundException e){
               System.out.println("can't read sheet excel");
           }
           catch(IOException e){
               System.out.println("can't read sheet excel");
           }
        return (dataTest);
    }
}
