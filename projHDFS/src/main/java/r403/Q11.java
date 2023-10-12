package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class Q11 {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        // HDFS
        // FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
        // Local FS
        LocalFileSystem fs = FileSystem.getLocal(conf);
        Path pathMovie = new Path(Assets.MOVIES.path);
        Path pathProd = new Path(Assets.PRODUCTION.path);
        int lineNum = 0;
        FSDataInputStream inStreamMovie = fs.open(pathMovie);
        FSDataInputStream inStreamProd = fs.open(pathProd);
        try {
            // Movie lines
            InputStreamReader isrMovie = new InputStreamReader(inStreamMovie);
            BufferedReader brMovie = new BufferedReader(isrMovie);
            String lineMovie = brMovie.readLine();
            // Production lines
            InputStreamReader isrProd = new InputStreamReader(inStreamProd);
            BufferedReader brProd = new BufferedReader(isrProd);
            String lineProd = brProd.readLine();
            // Algorithm
            while (lineMovie != null || lineProd != null) {
                if (lineNum != 0) {
                    Movie.fromLine(lineMovie);
                    Production.fromLine(lineProd);
                    // System.out.println(Movie.getId() + " - " + Production.getIdMovie());
                    if (Movie.getId().equals(Production.getIdMovie()))
                        System.out.println("Movie : " + Movie.getTitle() + " - Studio ID: " + Production.getIdStudio());
                }
                lineMovie = brMovie.readLine();
                lineProd = brProd.readLine();
                lineNum++;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            inStreamMovie.close();
            inStreamProd.close();
            fs.close();
        }
    }
}
