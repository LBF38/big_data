package r403;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DisplayFolder {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        // HDFS
        // FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
        // Local FS
        LocalFileSystem fs = FileSystem.getLocal(conf);
        FileStatus[] fileStatus = fs.listStatus(new Path("C:\\tmp\\production.csv"));
        for (FileStatus status : fileStatus) {
            Path fullname = new Path(status.getPath().toString());
            System.out.println("Path: " + fullname);
            FSDataInputStream inStream = fs.open(fullname);
            try {
                InputStreamReader isr = new InputStreamReader(inStream);
                BufferedReader br = new BufferedReader(isr);
                String line = br.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = br.readLine();
                }
            } finally {
                inStream.close();
            }
        }
        fs.close();
    }
}
