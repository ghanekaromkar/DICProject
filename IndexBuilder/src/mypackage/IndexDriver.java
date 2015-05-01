package mypackage;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class IndexDriver {

	public static void main(String arg[]) throws IOException,
			ClassNotFoundException, InterruptedException {
		JobClient client = new JobClient();
		JobConf config = new JobConf(IndexDriver.class);
		config.setOutputKeyClass(Text.class);
		config.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(config, new Path(arg[0]));
		FileOutputFormat.setOutputPath(config, new Path(arg[1]));
		//FileInputFormat.addInputPath(config, new Path("/user/omkar/input"));
		//FileOutputFormat.setOutputPath(config, new Path("/user/omkar/result"));
		config.setMapperClass(IndexMapper.class);
		config.setReducerClass(IndexReducer.class);
		config.setNumMapTasks(3);
		config.setNumReduceTasks(3);
		client.setConf(config);

		try {
			JobClient.runJob(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
