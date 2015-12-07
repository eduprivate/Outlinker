package br.com.edu.nmonitor.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	public static String readSource(String filePath) throws IOException{
		
		StringBuffer buff = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
		
		String line = null;
		
		while( (line = reader.readLine()) != null){
			buff.append(line+"\n");
		}
		reader.close();
		return buff.toString();
	}
	
}