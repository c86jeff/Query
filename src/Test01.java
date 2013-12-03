import java.io.IOException;
import java.util.*;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.ReflectionUtils;

public class Test01 {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
	
		String uri = "output/part-00000";
        Configuration conf = new Configuration();
        SequenceFile.Reader sfr = null;
        try {
            FileSystem fs = FileSystem.get(URI.create(uri), conf);
            try {
                sfr = new SequenceFile.Reader(fs, new Path(uri), conf);
                Writable key = (Writable) ReflectionUtils.newInstance(sfr
                        .getKeyClass(), conf);
                Writable value = (Writable) ReflectionUtils.newInstance(sfr
                        .getValueClass(), conf);
                while (sfr.next(key, value)) {
                    System.out.println(key+":"+value);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                IOUtils.closeStream(sfr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
