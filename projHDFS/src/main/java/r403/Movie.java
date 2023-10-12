package r403;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
    static String[] fields;

    public static void fromLine(String ligne) {
        fields = ligne.split(";");
    }

    public static String getTitle() {
        return fields[1];
    }

    public static int getId() throws NumberFormatException {
        return Integer.parseInt(fields[0]);
    }

    public static double getRating() throws NumberFormatException {
        return Double.parseDouble(fields[7].replace("\"", ""));
    }

    public static int getRuntime() {
        return Integer.parseInt(fields[3]);
    }

    public static Date getRelease() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(fields[2]);
    }

    public static double getFloat() throws NumberFormatException {
        return Double.parseDouble(fields[99]);
    }
}
