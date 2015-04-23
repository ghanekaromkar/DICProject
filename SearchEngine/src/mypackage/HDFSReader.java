package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSReader {

	public static String getText(String fileName) throws IOException {
        Path pt=new Path("/home/omkar/input/"+fileName);
        FileSystem fs = FileSystem.get(new Configuration());
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
