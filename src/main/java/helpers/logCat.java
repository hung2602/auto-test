package helpers;

import java.io.File;

import static helpers.PathHelper.projectPath;

public class logCat {
    public static void getLog() {
        try {
            File filename = new File(projectPath + "mylog.log");
            filename.createNewFile();
            String cmd = "logcat -d -f" + filename.getAbsolutePath();
            Runtime.getRuntime().exec(cmd);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
