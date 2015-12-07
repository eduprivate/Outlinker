package br.com.edu.nmonitor;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.edu.nmonitor.parser.HtmlParser;

public class OutlinkExtractor {

	private HtmlParser htmlParser;
	private URI baseUrl;
	private Integer depth;

	public OutlinkExtractor(URI url, Integer depth) {
		this.depth = depth;
		this.baseUrl = url;
		htmlParser = new HtmlParser(baseUrl);

	}

	public Set<URI> getOutLinks() {
		Map<URI, Boolean> urls = new HashMap<URI, Boolean>();
		Set<URI> extractedUrls = new HashSet<URI>();
		urls.put(baseUrl, false);
		int count = 0;

		do {
			System.out.println("Depth " + count + " of " + depth );
			for (URI key : urls.keySet()) {
				if (urls.get(key)==false) {
					try {
						Document doc = Jsoup.parse(key.toURL(), 30000);
						extractedUrls = htmlParser.getOutLinks(doc);
						urls.put(key, true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}

			for (URI uri : extractedUrls) {
				if (!urls.containsKey(uri)) {
					urls.put(uri, false);
				}
			}
			count++;
		} while (count <= depth);

		Set<URI> list = new HashSet<URI>(urls.keySet());
		return list;
	}

}
