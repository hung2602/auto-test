package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFile {
    private static Properties properties;
    private static String projectPath = System.getProperty("user.dir");
    private static FileOutputStream fileOut;

    private static String propertiesPath = "\\src\\main\\resources\\data.properties";

    public static void setPropertiesFile() {
        properties = new Properties();
        try {
            FileInputStream fileIn = new FileInputStream(projectPath + propertiesPath);
            properties.load(fileIn);
        } catch (Exception e) {
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
    public static void setPropValue(String key, String value) {
        try {
            fileOut = new FileOutputStream(projectPath + propertiesPath);
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
