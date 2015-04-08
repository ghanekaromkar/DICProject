package mypackage;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MapperTest extends MapReduceBase implements
		org.apache.hadoop.mapred.Mapper<LongWritable, Text, Text, Text> {
	private final static Text word = new Text();
	private final static Text location = new Text();
	private static Pattern p=Pattern.compile("[\\w'-]+");;
	private static Matcher m;

	@Override
	public void map(LongWritable key, Text val,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		FileSplit fileSplit = (FileSplit) reporter.getInputSplit();
		String fileName = fileSplit.getPath().getName();
		location.set(fileName);
		String line = val.toString();
		StringTokenizer itr = new StringTokenizer(line.toLowerCase());
		while (itr.hasMoreTokens()) {
			String raw=itr.nextToken();
			 m= p.matcher(raw);
			 if(m.find()){
			word.set(raw.substring(m.start(), m.end()));
			output.collect(word, location);}
		}
	}
}