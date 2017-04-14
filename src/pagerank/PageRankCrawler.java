package pagerank;

import java.util.Set;
import java.util.Vector;
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
	
	private WebGraph webGraph;
	
	

	/**
	 * @param webGraph
	 */
	public PageRankCrawler(WebGraph webGraph) {
		super();
		this.webGraph = webGraph;
	}

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

		Set<WebURL> links = htmlParseData.getOutgoingUrls();
		Vector<String> outgoingLinks = new Vector<>(links.size());
		for (WebURL webUrl : links) {
			outgoingLinks.add(webUrl.getURL());
		}
		this.webGraph.addPage(url, outgoingLinks);		
	}
	
	

	
}
