package helpers;
import java.io.File;

public class PathHelper {
    public static String projectPath = System.getProperty("user.dir");

    public static String getFileName(String nameFolder){
        File folder = new File(projectPath + "\\" + nameFolder);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles[0].getName();
    }
}
