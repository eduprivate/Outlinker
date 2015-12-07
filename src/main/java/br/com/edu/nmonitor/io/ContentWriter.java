package br.com.edu.nmonitor.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Set;

public class ContentWriter {
	public void writeContent(Set<URI> urls, File file) throws IOException {
		BufferedWriter output = null;
		try {
		
			String content = urls.toString().replaceAll("[\\{\\}]", "");
			output = new BufferedWriter(new FileWriter(file));
            output.write(content);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) output.close();
        }
		
	}
}
