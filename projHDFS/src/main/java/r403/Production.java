package r403;

public class Production {
    static String[] fields;

    public static void fromLine(String ligne) {
        fields = ligne.split(";");
    }

    public static int getIdStudio() throws NumberFormatException {
        return Integer.parseInt(fields[0]);
    }

    public static String getIdMovie() {
        return fields[1];
    }

}
