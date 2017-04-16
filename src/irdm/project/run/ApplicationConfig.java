/**
 * 
 */
package irdm.project.run;

/**
 * @author Harsha Perera
 *
 */
public class ApplicationConfig {

	public static String HomePath = "C:/Users/Harsha/workspace/IRDMSearchEngine";
	public static String IndexPath = HomePath + "/IndexData";
	public static String CrawlPath = HomePath + "/CrawlData";
	
	public static int CrawlMaxDepth = 10;
	public static String SeedUrl = "http://mediagamma.com";
	public static int NumberOfCrawlers = 3;
	// Maximum iterations to use in the power method when calculating the page rank
	public static int PageRankMaxIterations = 10;
	public static double PageRankTeleportProbability = 0.15;

}
