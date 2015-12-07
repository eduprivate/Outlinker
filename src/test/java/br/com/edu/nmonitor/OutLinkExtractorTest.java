package br.com.edu.nmonitor;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class OutLinkExtractorTest {
	
	public static final String HTML_FILES_PATH = "./src/test/resources/";

	@Test
	public void extractOutlinksTest() throws URISyntaxException, IOException {
		// Given
		URI url = new URI("http://g1.com.br/");
		
		// When
		OutlinkExtractor outlinkExtractor = new OutlinkExtractor(url, 0);
		Set<URI> outlinks = outlinkExtractor.getOutLinks();
		
		// Then
		Assert.assertTrue(outlinks.size()>0);
	}
}
