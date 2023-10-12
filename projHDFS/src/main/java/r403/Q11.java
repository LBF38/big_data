package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
        FSDataInputStream inStreamMovie = fs.open(pathMovie);
        FSDataInputStream inStreamProd = fs.open(pathProd);
        try {
            // Production lines
            InputStreamReader isrProd = new InputStreamReader(inStreamProd);
            BufferedReader brProd = new BufferedReader(isrProd);
            String lineProd = brProd.readLine();
            // Get the table in a map
            Map<String, Integer> prodMovie2Studio = new HashMap<String, Integer>();
            int lineNum = 0;
            while (lineProd != null) {
                if (lineNum != 0) {
                    Production.fromLine(lineProd);
                    prodMovie2Studio.put(Production.getIdMovie(), Production.getIdStudio());
                }
                lineProd = brProd.readLine();
                lineNum++;
            }
            inStreamProd.close();
            // Movie lines
            InputStreamReader isrMovie = new InputStreamReader(inStreamMovie);
            BufferedReader brMovie = new BufferedReader(isrMovie);
            String lineMovie = brMovie.readLine();
            // Algorithm
            lineNum = 0;
            while (lineMovie != null) {
                if (lineNum != 0) {
                    Movie.fromLine(lineMovie);
                    if (prodMovie2Studio.containsKey(Movie.getId())) {
                        System.out.println(
                                "Movie: " + Movie.getTitle() + " - StudioID: " + prodMovie2Studio.get(Movie.getId()));
                    }
                }
                lineMovie = brMovie.readLine();
                lineNum++;
            }
        } finally {
            inStreamMovie.close();
            fs.close();
        }
    }
}
