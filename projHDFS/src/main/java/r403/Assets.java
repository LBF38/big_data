package r403;

public enum Assets {
    MOVIES("C:\\tmp\\movie_no_desc.csv"), STUDIOS("C:\\tmp\\studios_2023-03-22.csv"),
    PRODUCTION("C:\\tmp\\production.csv");

    public String path;

    Assets(String path) {
        this.path = path;
    }
}
