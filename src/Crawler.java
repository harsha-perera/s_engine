import java.util.HashMap;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lemurproject.indri.IndexEnvironment;

/**
 * 
 */

/**
 * @author Shruti Sinha
 *
 */
public class Crawler extends WebCrawler {
	private static IndexEnvironment env;
	
	
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|pdf|txt"
			+ "|png|mp3|mp3|zip|gz))$");
	
	public static void setIndexEnvironment(IndexEnvironment env){
		Crawler.env = env;
	}

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches()
				&& (href.startsWith("http://mediagamma.com")||href.startsWith("mediagamma.com"));
	}


	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
//			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			
			
			HashMap<String, String> map = new HashMap<>();
			map.put("URL", url);
			try {
				env.addString(html, "html", map); // add to index
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			Set<WebURL> links = htmlParseData.getOutgoingUrls();
//
//			System.out.println("Text length: " + text.length());
//			System.out.println("Html length: " + html.length());
//			System.out.println("Number of outgoing links: " + links.size());
		}
	}
}
