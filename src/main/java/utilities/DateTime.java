package utilities;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public static String getCurrentDate() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }
    public static String getCurrentDateTime(String formatTime) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(formatTime);
        return formatter.format(now);
    }
}
