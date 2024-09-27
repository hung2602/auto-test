package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesFile {
    private static Properties properties;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;
    private static String projectPath = System.getProperty("user.dir");
    private static String dataPropertiesPath = "\\src\\main\\resources\\data.properties";
    private static String configPropertiesPath = "\\src\\main\\resources\\config.properties";
    public static void setPropertiesFile() {
        LinkedList<String> files = new LinkedList<>();
        files.add(projectPath + dataPropertiesPath);
        files.add(projectPath + configPropertiesPath);
        properties = new Properties();
        try {
            for (String file : files) {
                Properties tempProp = new Properties();
                fileIn = new FileInputStream(file);
                tempProp.load(fileIn);
                properties.putAll(tempProp);
            }
            fileIn.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    public static String getPropValue(String key){
        String value = null;
        try {
            value = properties.getProperty(key);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return value;
    }
    public static void setDataPropValue(String key, String value) {
        try {
            fileOut = new FileOutputStream(projectPath + dataPropertiesPath);
            properties.setProperty(key, value);
            properties.store(fileOut, "set new value");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
