import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.fetcher.*;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lemurproject.indri.IndexEnvironment;

/**
 * 
 */

/**
 * @author Shruti Sinha
 *
 */
public class TestCrawler {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		 	String crawlStorageFolder = "C:/WebSearchEngine/crawlData_3";
		 	String indexStorageFolder = "C:/WebSearchEngine/indexdata";
		 	String seedURL = "http://mediagamma.com/";
		 	
	        int numberOfCrawlers = 3;

	        try {
				CrawlConfig config = new CrawlConfig();
				config.setCrawlStorageFolder(crawlStorageFolder);
				
				PageFetcher pageFetcher = new PageFetcher(config);
				RobotstxtServer robotstxtServer = new RobotstxtServer(new RobotstxtConfig(), pageFetcher);
				CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

				controller.addSeed(seedURL);	 

				IndexEnvironment env = IndriIndexer.createIndexEnvironment(indexStorageFolder);
				Crawler.setIndexEnvironment(env);
				controller.start(Crawler.class, numberOfCrawlers);
				//controller.shutdown();
				env.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}

	}

}
