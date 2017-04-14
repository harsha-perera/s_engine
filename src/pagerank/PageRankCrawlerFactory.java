package pagerank;

import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * @author Harsha Perera
 *
 */
public class PageRankCrawlerFactory implements CrawlController.WebCrawlerFactory<PageRankCrawler> {


	private WebGraph webGraph;
	
    public PageRankCrawlerFactory(WebGraph webGraph) {
    	this.webGraph = webGraph;
    }

    @Override
    public PageRankCrawler newInstance() {
        return new PageRankCrawler(this.webGraph);
    }
}