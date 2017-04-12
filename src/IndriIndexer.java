import lemurproject.indri.IndexEnvironment;

/**
 * 
 */

/**
 * @author Shruti Sinha
 *
 */
public class IndriIndexer {
	
	private static String [] stopWordList =  {"a", "an", "and", "are", "as", "at", "be", 
            "by","for", "from", "has", "he", "in", "is",
            "it", "its", "of", "on", "that", "the", "to",
            "was", "were", "will", "with"};
	
	public static IndexEnvironment createIndexEnvironment(String path) throws Exception{
		IndexEnvironment envI = new IndexEnvironment();
		envI.setStoreDocs(true);

		// create an Indri repository
		envI.setMemory(256000000);

		envI.setStemmer("krovetz"); // or porter : stemming algo
		envI.setStopwords(stopWordList);

		envI.setIndexedFields( new String[] {"article", "header", "p", "title", "link"}); 
		//envI.open(myIndex);
		envI.create( path );
		
		return envI;
	}
}
