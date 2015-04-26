package mypackage;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SearchReducer extends MapReduceBase implements
org.apache.hadoop.mapred.Reducer<Text,Text,Text,Text>{

	@Override
	public void reduce(Text arg0, Iterator<Text> arg1,
			OutputCollector<Text, Text> arg2, Reporter arg3) throws IOException {
		if(arg1.hasNext()){
			arg2.collect(arg0,arg1.next());
		}
		
	}

}