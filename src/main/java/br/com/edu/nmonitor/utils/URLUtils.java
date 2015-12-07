package br.com.edu.nmonitor.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLUtils {

	public URLUtils() {
	}
	
	public static String percentEncodeRfc3986(final String string){
		try{
			return URLEncoder.encode(string, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~").replace(" ", "%20");
		}
		catch (UnsupportedEncodingException e){
			return string;
		}
	}

	public static URI urlToURI(URL url) throws UnsupportedEncodingException, URISyntaxException {
		
		String protocol = url.getProtocol();
		Integer port = url.getPort();
		String host = url.getHost();
		String path = URLDecoder.decode(url.getPath(),"UTF-8");
		
		String queryString = url.getQuery();
		
		String newPath = "";
		
		String[] tokens = path.split("/");
		for(String tok: tokens){
			if(!tok.trim().isEmpty())
				newPath = newPath+"/"+URLUtils.percentEncodeRfc3986(tok);
		}
		
		String location = protocol+"://"+host;

		if(port != -1 && port != 80){
			location = location+":"+port;
		}
		
		location = location+newPath;
		if(queryString != null && !queryString.isEmpty()){
			location = location +"?"+queryString.replace(" ", "%20");
		}
		
		return new URI(location);
	}
}
