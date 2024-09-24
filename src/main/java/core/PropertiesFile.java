package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFile {
    private static Properties properties;
    private static String path = System.getProperty("user.dir");
    private static String propertiesPath = "\\src\\main\\resources\\data.properties";

    public static void setPropertiesFile() {
        properties = new Properties();
        try {
            FileInputStream fileIn = new FileInputStream(path + propertiesPath);
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
}
