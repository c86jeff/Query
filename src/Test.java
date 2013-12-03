import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;
import org.apache.hadoop.util.ReflectionUtils;

public class Test {
	
	private String InputDir;
	private String OutputDir;
	public Test(String InputDir, String OutputDir){
		this.InputDir = InputDir;
		this.OutputDir = OutputDir;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
		String word = "adequate";
		index = "";
		Configuration conf = new Configuration();
		conf.set("word", word);
		//conf.set("location",location);
		
		
		Job job = new Job(conf, "test");                                         
	    job.setJarByClass(Test.class);                                            
	    job.setMapperClass(TestMapper.class);  
	    job.setNumReduceTasks(0);  
	    job.setOutputKeyClass(Text.class);  
	    job.setOutputValueClass(Text.class);  
	    //job.setOutputFormatClass(Text.class);
	    job.setOutputFormatClass(TextOutputFormat.class);
	    FileInputFormat.addInputPath(job, new Path("input"));  
	    FileOutputFormat.setOutputPath(job, new Path("output"));
	    job.waitForCompletion(true);
	    System.out.println(index);
		 
	}
	
	
	private static String index;
	
	public static class TestMapper extends 
			Mapper<Object, Text, Text, Text> {
		
		private String word="";
		//private String location;
		public void setup(Context context){
			word = context.getConfiguration().get("word");
			//location = context.getConfiguration().get("location");
		}
		public void map(Object key, Text value,Context context
						) throws IOException,InterruptedException {
			
			Text first = new Text();
			Text other = new Text();
			String[] s = value.toString().split("\\s+");
			String wordGet = s[0].trim();
			if(wordGet.equals(word)){
				index =	s[1].trim();
				first.set(wordGet);
				other.set(s[1].trim());
				context.write(first,other);
			}
		    /*Configuration conf = new Configuration();	
			FileSystem fs = FileSystem.get(URI.create(location), conf);
			SequenceFile.Reader sfr = new SequenceFile.Reader(fs, new Path(location), conf);
			
			key = (Text) ReflectionUtils.newInstance(
					sfr.getKeyClass(), conf);
			
			value = (Text) ReflectionUtils.newInstance(
					sfr.getValueClass(), conf);
			
			
			try {
				while (sfr.next(key, value)) {
					if(key.toString().equals(word)){
						context.write(key, value);
					}
				}	
				} catch (IOException e1) {
					e1.printStackTrace();
				}finally {
	                IOUtils.closeStream(sfr);
				}
			}*/
			
		}
		
	
		
		
		public static class TestReducer extends Reducer<Text, Text, Text, Text>{
			
			public void reduce(Text key, Iterator<Text> values,Context context
							)throws IOException, InterruptedException {
				context.write(key,values.next());
			}
		}

		
		
	}
}
