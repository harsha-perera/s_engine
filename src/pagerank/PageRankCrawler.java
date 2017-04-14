package pagerank;

import java.util.HashMap;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


/**
 * @author Harsha Perera
 *
 */
public class PageRankCrawler extends WebCrawler {
	
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|pdf|txt"
			+ "|png|mp3|mp3|zip|gz))$");

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches();
	}

	@Override
	public void visit(Page page) {
		if (!(page.getParseData() instanceof HtmlParseData)) {
			return;
		}

		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		// String text = htmlParseData.getText();
		String html = htmlParseData.getHtml();

		HashMap<String, String> map = new HashMap<>();
		map.put("URL", url);

		// Set<WebURL> links = htmlParseData.getOutgoingUrls();
		//
		// System.out.println("Text length: " + text.length());
		// System.out.println("Html length: " + html.length());
		// System.out.println("Number of outgoing links: " + links.size());		
	}
	
	

	
}
