package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class DisplayFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        // HDFS
        // FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
        // Local FS
        LocalFileSystem fs = FileSystem.getLocal(conf);
        Path fullname = new Path("C:\\tmp\\production.csv"); // change the path to your file
        FSDataInputStream inStream = fs.open(fullname);
        try {
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                try {
                    System.out.println(line);
                } catch (Exception e) {
                }
                line = br.readLine();
            }
        } finally {
            inStream.close();
            fs.close();
        }
    }
}
