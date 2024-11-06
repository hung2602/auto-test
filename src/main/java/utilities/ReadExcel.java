package utilities ;
import java.io.*;
import java.util.HashMap;
import java.util.Set;

import helpers.LogHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import static helpers.PathHelper.projectPath;

public class ReadExcel {
    private static final String FILE_PATH = projectPath + "data" + File.separator + "DataTestOnplus.xlsx";
    private static Sheet sh;
    private static FileOutputStream fileOut;
    private static Logger logger = LogHelper.getLogger();
    private static Workbook workbook = null;
    public static int getRowCount() {
        return sh.getLastRowNum();
    }
    public static int getColCount() {
        return sh.getRow(0).getLastCellNum();
    }
    public static Object[][] getExcelData(){
        Object[][] obj = new Object[getRowCount()][1];
        try {
            for (int i = 1; i <= getRowCount(); i++) {
                HashMap<String, String> testData = getTestDataInMap(i);
                obj[i - 1][0] = testData;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
    public static void ExcelOperations(String sheetName) {
        logger.info("Read sheet excel " + sheetName );
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            workbook = new XSSFWorkbook(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        sh = workbook.getSheet(sheetName);
    }
    public static HashMap<String, String> getTestDataInMap(int rowNum) {
        HashMap<String, String> hm = new HashMap<>();
        for (int i = 0; i < sh.getRow(0).getLastCellNum(); i++) {
            String value;
            if(sh.getRow(rowNum).getCell(i) != null) {
                sh.getRow(rowNum).getCell(i).setCellType(CellType.STRING);
                value = sh.getRow(rowNum).getCell(i).toString();
            }
            else {
                value = "";
            }
            hm.put(sh.getRow(0).getCell(i).toString(), value);
        }
        Set<String> set = hm.keySet();
        for (String key : set) {
            System.out.println("key " + key + " value " + hm.get(key));
        }
        return hm;
    }
    public static int getIndexRowFromKey(String key){
        int index = 0;
        System.out.println("Number rows: " + sh.getPhysicalNumberOfRows());
        for (int i = 0; i < sh.getPhysicalNumberOfRows(); i++) {
            System.out.println("Value colum " + sh.getRow(i).getCell(0).getStringCellValue());
            if(sh.getRow(i).getCell(0).getStringCellValue().equals(key)){
                index = i;
                break;
            }
        }
        logger.info("Index row: " + index);
        return index;
    }
    public static int getIndexCellFromKey(String key){
        int index = 0;
        System.out.println("Number colum: " + sh.getRow(0).getPhysicalNumberOfCells());
        for (int i = 0; i < sh.getRow(0).getPhysicalNumberOfCells(); i++) {
            if(sh.getRow(0).getCell(i).getStringCellValue().equals(key)){
                index = i;
                break;
            }
        }
        logger.info("Index cell: " + index);
        return index;
    }
    public static void setCell(String value, int rowNumber, int cellNumber){
        try {
            logger.info("Set cell: " + value + " On row: " + rowNumber + " On cell: " + cellNumber);
            Row row = sh.getRow(rowNumber);
            Cell cell = row.getCell(cellNumber);
            cell.setCellValue(value);
            fileOut = new FileOutputStream(FILE_PATH);
            workbook.write(fileOut);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
//    public int getIndexCell(int rowNumber, String key){
//            Row row = sh.getRow(rowNumber);
//            return 0;
//    }
}
