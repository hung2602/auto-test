package helpers;
import org.testng.annotations.BeforeSuite;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DeviceHelper {
    @BeforeSuite()
    public static void setInformDevice() {
        try {
            // Run the adb devices command
            Process process = Runtime.getRuntime().exec("adb devices");
            // Capture the command output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<String> deviceList = new ArrayList<>();
            // Read each line and add it to the device list
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 1 && "device".equals(parts[1])) {
                        deviceList.add(parts[0]);
                    }
                }
            }
            PropertiesFile.setDataPropValue("ID_DEVICE",deviceList.get(0));
            String command = String.format("adb -s %s shell getprop %s", deviceList.get(0) , "ro.build.version.release");
            process = Runtime.getRuntime().exec(command);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = reader.readLine();
            PropertiesFile.setDataPropValue("PLAT_FORM_VERSION", result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
