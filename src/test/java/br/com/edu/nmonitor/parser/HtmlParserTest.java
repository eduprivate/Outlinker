package br.com.edu.nmonitor.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import br.com.edu.nmonitor.parser.HtmlParser;
import br.com.edu.nmonitor.utils.FileUtils;

public class HtmlParserTest {

	public static final String HTML_FILES_PATH = "./src/test/resources/";

	@Test
	public void parseOutlinksTest() throws URISyntaxException, IOException {
		// Given
		Integer expectedOutlinksSize = 6;
		URI url = new URI("http://g1.com.br");
		String source = FileUtils.readSource(HTML_FILES_PATH + "outlinks.html");
		Document doc = Jsoup.parse(source, url.toString());

		// When
		HtmlParser parser = new HtmlParser(url);
		Set<URI> outlinks = parser.getOutLinks(doc);
		
		// Then
		Assert.assertEquals(expectedOutlinksSize, new Integer(outlinks.size()));
	}

}
