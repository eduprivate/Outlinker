package br.com.edu.nmonitor.parser;

import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.edu.nmonitor.utils.URLUtils;


public class HtmlParser {
	
	public String urlBaseRegex;

	public HtmlParser(URI url) {
		this.urlBaseRegex = generateFilter(url);
	}

	public Set<URI> getOutLinks(Document doc) {

		Elements links = doc.getElementsByAttribute("href");
		Set<URI> urls = new HashSet<URI>();

		for ( Element link : links ) {
			try {
				String textLink = link.absUrl("href");
				if(textLink.trim().isEmpty())
					continue;
				URL url = new URL(textLink);
				URI uri = URLUtils.urlToURI(url);
				
				if (isValidToFilter(uri)){
					urls.add(uri);
				}

			} catch (Throwable e) {
				System.out.println("Erro ao extrair outlink ["+link.absUrl("href")+"]");
			}
		}

		return urls;
		
	}
	
	private boolean isValidToFilter(URI uri){
		Pattern pattern = Pattern.compile(urlBaseRegex);
		Matcher matcher = pattern.matcher(uri.toString());
		return matcher.matches();
	}
	
	private String generateFilter(URI url) {
		return "^https?://([^\\/]*\\.)?"+url.getHost()+"(:[0-9]*/.*$|/.*$|$)";
	}

}
