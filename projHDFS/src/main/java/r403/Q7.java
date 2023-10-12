package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class Q7 {
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
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, 0, 1);
            Date comparisonDate = calendar.getTime();
            while (line != null) {
                if (lineNum != 0) {
                    Movie.fromLine(line);
                    Date release = Movie.getRelease();
                    if (release.before(comparisonDate)) {
                        System.out.println(Movie.getTitle());
                    }
                }
                line = br.readLine();
                lineNum++;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            inStream.close();
            fs.close();
        }
    }
}
