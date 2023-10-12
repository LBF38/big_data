package r403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class Q1 {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        // HDFS
        // FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
        // Local FS
        LocalFileSystem fs = FileSystem.getLocal(conf);
        Path path = new Path("C:\\tmp\\studios_2023-03-22.csv");
        int lineNum = 0;
        FSDataInputStream inStream = fs.open(path);
        try {
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            Integer countStudio = 0;
            while (line != null) {
                if (lineNum != 0) {
                    Studio.fromLine(line);
                    countStudio++;
                }
                line = br.readLine();
                lineNum++;
            }
            System.out.println("Number of studios:" + countStudio); // Result: 580
        } finally {
            inStream.close();
            fs.close();
        }
    }
}
