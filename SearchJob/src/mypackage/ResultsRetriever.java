package mypackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.StringTokenizer;



public class ResultsRetriever {

	public static void blabla(String args[]) throws IOException{
		//SearchJob.search(args);
		/*FileSystem fs = FileSystem.get(new Configuration());
		FileStatus[] status = fs.listStatus(new Path("/home/omkar/result/"));
		for (int i=0;i<status.length;i++){*/
		FileReader in = new FileReader("/home/omkar/result/part-00000");
	    BufferedReader br = new BufferedReader(in);
            String line;
            line=br.readLine();
            HashSet<String> prev=new HashSet<String>();
            while (line != null){
            	String[] keyValue=line.split("\t");
                    prev=intersect(prev,keyValue[1]);
                    line=br.readLine();
            }
            for(String str: prev){
            	System.out.println(str);
            }
   // }
	}

	private static HashSet<String> intersect(HashSet<String> prev, String line) {
		StringTokenizer st= new StringTokenizer(line,"|");
		HashSet<String> current = new HashSet<String>();
		while(st.hasMoreTokens()){
			String str= st.nextToken().trim();
			if(prev.contains(str))
				current.add(str);
		}
		return current;
	}
}
