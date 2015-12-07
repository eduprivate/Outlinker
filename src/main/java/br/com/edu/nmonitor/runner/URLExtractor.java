package br.com.edu.nmonitor.runner;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.edu.nmonitor.OutlinkExtractor;
import br.com.edu.nmonitor.exceptions.InvalidParameterException;
import br.com.edu.nmonitor.io.ContentWriter;
import br.com.edu.nmonitor.utils.ParameterUtil;
import br.com.edu.nmonitor.utils.ParametersMapUtils;

public class URLExtractor {
	public static void main(String[] args) throws InvalidParameterException, NumberFormatException, URISyntaxException, IOException {
		if(args.length < 3){
			System.out.println("Usage: Indexer [OPTIONS...] \n" +
					"\n" +
					"	-filePath	Path to file (complete).\n" +
					"	-url		Initial url.\n" +
					"	-depth		Depth of crawling.\n"  );
			
			System.exit(-1);
		}
		
		Map<String, List<String>> params = ParametersMapUtils.buildParamMap(args);
		String filePath = ParameterUtil.getParameter("filePath", params, true);
		String url = ParameterUtil.getParameter("url", params, true);
		String depth = ParameterUtil.getParameter("depth", params, true);
		//String alg = ParameterUtil.getParameter("alg", params, true);
		
		OutlinkExtractor extractor = new OutlinkExtractor(new URI(url), Integer.parseInt(depth)); 
		Set<URI> urls = extractor.getOutLinks();

		ContentWriter contentWriter = new ContentWriter();
		File file = new File(filePath);
		contentWriter.writeContent(urls, file);
		
		System.out.println("Finished!");
	}
}
