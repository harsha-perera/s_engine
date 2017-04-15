package irdm.project.run;

import irdm.project.index.IndexBuilder;

/**
 * 
 */

/**
 * @author Shruti Sinha
 *
 */
public class RunIndexing {
	
	public static String index_path = "C:/TerrierSearchEngine/data";
	public static String url = "http://dynamicsimulationltd.com";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IndexBuilder indexBuilder = new IndexBuilder(index_path, url, 10);
		indexBuilder.indexWebsite();
		indexBuilder.writeIndex("data");
	}

}
