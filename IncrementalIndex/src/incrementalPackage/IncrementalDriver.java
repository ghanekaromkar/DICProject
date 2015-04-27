package incrementalPackage;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;

public class IncrementalDriver {

	public static void main(String args[]) throws IOException{
		JobClient client = new JobClient();
		JobConf config = new JobConf(IncrementalDriver.class);
		config.setOutputKeyClass(Text.class);
		config.setOutputValueClass(Text.class);
		config.setInputFormat(KeyValueTextInputFormat.class);
		Path origIndex=new Path(args[0]);
		Path newIndex=new Path(args[1]);
		Path updatedIndex= new Path(args[2]);
		FileInputFormat.setInputPaths(config,origIndex,newIndex);
		FileOutputFormat.setOutputPath(config,updatedIndex);
		config.setMapperClass(IncrementalMapper.class);
		config.setReducerClass(IncrementalReducer.class);
		config.setNumMapTasks(3);
		config.setNumReduceTasks(3);
		client.setConf(config);
		JobClient.runJob(config);
		config.addResource(new Path("/home/oghanek/hadoop-2.6.0/etc/hadoop/core-site.xml"));
		FileSystem fs= FileSystem.get(config);
		fs.setConf(config);
		fs.delete(origIndex,true);
		fs.rename(updatedIndex,origIndex);
		//FileUtil.copyMerge(fs, new Path("/home/omkar/newinput"), fs, new Path("/home/omkar/input/"), true, config,null);
		fs.delete(newIndex,true);

		
	}
}
