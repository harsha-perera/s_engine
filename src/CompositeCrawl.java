import java.util.HashMap;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lemurproject.indri.IndexEnvironment;
import pagerank.PageRankCalculator;
import pagerank.PageRankCrawlerFactory;
import pagerank.WebGraph;

/**
 * 
 */

/**
 * @author Harsha Perera
 *
 */
public class CompositeCrawl {

	public static void main(String[] args) {
	 	String crawlStorageFolder = "C:/Users/Harsha/workspace/IndriSearchEngine/CrawlData";
	 	String indexStorageFolder = "C:/Users/Harsha/workspace/IndriSearchEngine/IndexData";
	 	String seedURL = "http://mediagamma.com/";
	 	
        int numberOfCrawlers = 3;

        try {
			CrawlConfig config = new CrawlConfig();
			config.setCrawlStorageFolder(crawlStorageFolder);
			
			PageFetcher pageFetcher = new PageFetcher(config);
			RobotstxtServer robotstxtServer = new RobotstxtServer(new RobotstxtConfig(), pageFetcher);
			CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

			controller.addSeed(seedURL);	 

			// TODO: Need to create a composite crawler class as well, so that we can create all the indexes in one run.
			
			//IndexEnvironment env = IndriIndexer.createIndexEnvironment(indexStorageFolder);
			//Crawler.setIndexEnvironment(env);
			WebGraph g = new WebGraph();
			controller.start(new PageRankCrawlerFactory(g), numberOfCrawlers);
			controller.shutdown();			
			//env.close();
			PageRankCalculator calc = new PageRankCalculator();
			HashMap<String, Double> pageRank = calc.calculatePageRank(g, 10);
			// TODO : Need to the save the page rank values somewhere ...
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

}
	
}
