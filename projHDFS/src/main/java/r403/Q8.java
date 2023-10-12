package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class Q8 {

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
            Integer minRuntime = null;
            String minMovieTitle = null;
            while (line != null) {
                if (lineNum != 0) {
                    Movie.fromLine(line);
                    int runtime = Movie.getRuntime();
                    if (minRuntime == null || runtime < minRuntime) {
                        minRuntime = runtime;
                        minMovieTitle = Movie.getTitle();
                    }
                }
                line = br.readLine();
                lineNum++;
            }
            System.out.println("Shortest movie: " + minMovieTitle + " (" + minRuntime + " minutes)");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            inStream.close();
            fs.close();
        }

    }
}
