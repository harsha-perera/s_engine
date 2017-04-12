import lemurproject.indri.QueryEnvironment;
import lemurproject.indri.ScoredExtentResult;

/**
 * 
 */

/**
 * @author Shruti Sinha
 *
 */
public class TestQuery {

	/** Maximum number of search results per query. */ 
	 private static final int MAX_RESULTS_PERQUERY = 500; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String query = "product";
		
		String indexStorageFolder = "C:/WebSearchEngine/indexdata"; 
		
		try { 
			   // create query environment 
			QueryEnvironment env = new QueryEnvironment(); 

			env.addIndex(indexStorageFolder); 


			// run an Indri query, returning up to MAX_RESULTS_PERQUERY results 
			ScoredExtentResult[] results = env.runQuery(query, MAX_RESULTS_PERQUERY); 


			String[] docs = env.documentMetadata(results, "URL"); 
			
			for(int i = 0; i < results.length; i++)
				System.out.println( docs[i] + ", Score:" + results[i].score);

			// close query environment 
			env.close(); 			

		} catch (Exception e) { 
			e.printStackTrace();			   
			System.exit(1); 
		}
	}

}
