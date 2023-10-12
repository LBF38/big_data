package r403;

public class Studio {
    static String[] fields;

    public static void fromLine(String ligne) {
        fields = ligne.split(";");
    }

    public static String getName() {
        return fields[1];
    }

    public static int getId() throws NumberFormatException {
        return Integer.parseInt(fields[0]);
    }

    public static double getFloat() throws NumberFormatException {
        return Double.parseDouble(fields[99]);
    }

}
