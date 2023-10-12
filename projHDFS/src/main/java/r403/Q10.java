package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class Q10 {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        // HDFS
        // FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
        // Local FS
        LocalFileSystem fs = FileSystem.getLocal(conf);
        Path path = new Path(Assets.MOVIES.path);
        int lineNum = 0;
        FSDataInputStream inStream = fs.open(path);
        try {
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            // TODO: simplify the HashMap to contain the year and not the full release date.
            Map<Date, Integer> moviesPerYear = new HashMap<Date, Integer>();
            while (line != null) {
                if (lineNum != 0) {
                    Movie.fromLine(line);
                    Date releaseDate = Movie.getRelease();
                    if (moviesPerYear.containsKey(releaseDate)) {
                        moviesPerYear.put(releaseDate, moviesPerYear.get(releaseDate) + 1);
                    } else {
                        moviesPerYear.put(releaseDate, 1);
                    }
                }
                line = br.readLine();
                lineNum++;
            }
            moviesPerYear.forEach((k, v) -> System.out.println(new SimpleDateFormat("yyyy").format(k) + ": " + v));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            inStream.close();
            fs.close();
        }
    }
}
