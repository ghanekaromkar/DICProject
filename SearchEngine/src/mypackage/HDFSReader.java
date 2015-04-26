package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSReader {

	public static String getText(String fileName) throws IOException {
		Configuration conf=new Configuration();
		conf.addResource(new Path("/home/oghanek/hadoop-2.6.0/etc/hadoop/core-site.xml"));
		FileSystem fs= FileSystem.get(conf);
		fs.setConf(conf);
		Path pt=new Path("/user/oghanek/input/"+fileName);
        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
        String returnText=fileName;
        String line=br.readLine();
		while (line != null){
			returnText=returnText+"\n"+line;
                line=br.readLine();
        }
		return returnText;
	}

}
