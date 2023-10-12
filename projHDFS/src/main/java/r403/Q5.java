package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class Q5 {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        // HDFS
        // FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
        // Local FS
        LocalFileSystem fs = FileSystem.getLocal(conf);
        Path path = new Path(Assets.STUDIOS.path);
        int lineNum = 0;
        FSDataInputStream inStream = fs.open(path);
        try {
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            List<String> countries = new ArrayList<String>();
            while (line != null) {
                if (lineNum != 0) {
                    Studio.fromLine(line);
                    String country = Studio.getCountry();
                    if (!countries.contains(country)) {
                        countries.add(country);
                        System.out.println(country);
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
