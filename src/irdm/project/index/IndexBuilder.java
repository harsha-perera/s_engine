/**
 * 
 */
package irdm.project.index;

import irdm.project.crawler.Crawler;

import java.io.IOException;

import org.terrier.realtime.memory.MemoryIndex;
import org.terrier.utility.ApplicationSetup;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


/**
 * @author Shruti Sinha
 *
 */
public class IndexBuilder {	
	
	static{
		ApplicationSetup.setProperty("indexer.meta.forward.keys", "title,url,body");
		ApplicationSetup.setProperty("indexer.meta.forward.keylens", "140,120,5000");
		ApplicationSetup.setProperty("indexer.meta.reverse.keys", "url");
		ApplicationSetup.setProperty("metaindex.crop", "true");
		ApplicationSetup.setProperty("ignore.low.idf.terms", "false");
//		ApplicationSetup.setProperty("querying.default.controls", "start,end,decorate:on,summaries:content,emphasis:title;content");
//		ApplicationSetup.setProperty("querying.postfilters.controls", "decorate:org.terrier.querying.Decorate");
//		ApplicationSetup.setProperty("querying.postfilters.order", "org.terrier.querying.Decorate");
	}
	
	private static int numberOfCrawlers = 3;
	
	
	private MemoryIndex memIndex; 
	private String indexPath;
	private String url;
	private int linkdepth;
	
	public IndexBuilder(String indexPath, String url, int linkdepth ){
		if(indexPath != null){
			ApplicationSetup.setProperty("terrier.home", indexPath);
			ApplicationSetup.setProperty("terrier.index.path", indexPath);
			this.indexPath = indexPath;
		}	
		
		this.url = url;
		this.linkdepth = linkdepth;
		
		memIndex = new MemoryIndex();		
	}
	
	public void writeIndex(String prefix) {
		
		try {
			memIndex.write(indexPath, prefix);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void indexWebsite() {		        

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(indexPath);
        config.setMaxDepthOfCrawling(linkdepth);
    
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
		try {
			controller = new CrawlController(config, pageFetcher, robotstxtServer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
     
        controller.addSeed(url);

       
        CustomIndexData customData = new CustomIndexData(url, memIndex);
        controller.setCustomData(customData);        
       
        controller.start(Crawler.class, numberOfCrawlers);   		
	}
	

}
