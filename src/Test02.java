import java.io.IOException;
import java.util.*;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapFile;
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


public class Test02 {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		Configuration conf=new Configuration();  
		String uri = "output/part-00000";
		FileSystem fs=FileSystem.get(URI.create(uri),conf);  
		Path mapFile=new Path("output");  
		//Reader内部类用于文件的读取操作  
		MapFile.Reader reader=new MapFile.Reader(fs,mapFile.toString(),conf);  
		//Writer内部类用于文件的写操作,假设Key和Value都为Text类型  
		MapFile.Writer writer=new MapFile.Writer(conf,fs,mapFile.toString(),Text.class,Text.class);  
		//通过writer向文档中写入记录  
		writer.append(new Text("key"),new Text("value"));  
		IOUtils.closeStream(writer);//关闭write流  
		//通过reader从文档中读取记录  
		Text key=new Text();  
		Text value=new Text();  
		while(reader.next(key,value)){  
		    System.out.println(key);  
		    System.out.println(key);  
		}  
		IOUtils.closeStream(reader);//关闭read流 
	}
}
