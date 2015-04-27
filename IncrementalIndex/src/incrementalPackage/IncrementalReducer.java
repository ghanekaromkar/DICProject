package incrementalPackage;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class IncrementalReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		String concatenatedList = "";
		while (values.hasNext()) {
			concatenatedList = concatenatedList+"|" + values.next().toString().trim();
		}
		if (concatenatedList.indexOf('|') == 0)
			concatenatedList = concatenatedList.substring(1);
		output.collect(key, new Text(concatenatedList));

	}

}
