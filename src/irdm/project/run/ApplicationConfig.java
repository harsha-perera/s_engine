/**
 * 
 */
package irdm.project.run;

import java.io.File;

/**
 * @author Harsha Perera
 *
 */
public class ApplicationConfig {

	public static String HomePath = "C:/Users/Harsha/workspace/IRDMSearchEngine";
	public static String IndexPath = HomePath + File.separator +  "IndexData";
	public static String CrawlPath = HomePath + File.separator +  "CrawlData";
	
	public static int CrawlMaxDepth = 10;
	public static String SeedUrl = "http://mediagamma.com";
	public static int NumberOfCrawlers = 3;
	
	public static boolean UsePageRank = true;
	// Maximum iterations to use in the power method when calculating the page rank
	public static int PageRankMaxIterations = 10;
	public static double PageRankTeleportProbability = 0.15;
	public static double PageRankWeighting = 0.5;
	public static String PageRankScoreFilePath = IndexPath + File.separator + "PageRankScores.dat";

}
