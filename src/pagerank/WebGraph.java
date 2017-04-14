/**
 * 
 */
package pagerank;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 * @author Harsha Perera
 *
 * Data structure to hold link data of pages whilst a web crawl is underway.
 */
public class WebGraph {
	// TODO: Update to make this class thread safe
	HashMap<String, Vector<String>> outgoingUrls;
	HashMap<String, Vector<String>> incomingUrls;
	Vector<String> urlList; 

	
	public WebGraph() {
		super();
		this.outgoingUrls = new HashMap<>();
		this.incomingUrls = new HashMap<>();
		this.urlList = new Vector<>();
	}


	public void addPage(String url, Vector<String> outgoingLinks){
		outgoingUrls.put(url, outgoingLinks);
		for (String targetUrl : outgoingLinks) {
			if(!outgoingUrls.containsKey(targetUrl)){
				outgoingUrls.put(targetUrl, new Vector<>());
			}
			Vector<String> incomingUrlsForTarget = incomingUrls.get(targetUrl);
			if(incomingUrlsForTarget == null){
				incomingUrlsForTarget = new Vector<>();
				incomingUrls.put(targetUrl, incomingUrlsForTarget);
			}
			incomingUrlsForTarget.add(url);
		}
	}
	
	public int getTotalPageCount(){
		return outgoingUrls.size();
	}
	
    public Set<String> getAllUrls(){
		return outgoingUrls.keySet();		
	}
	
    public Vector<String> getIncomingLinks(String url){
		return incomingUrls.get(url);
	}
    
    public Vector<String> getOutgoingLinks(String url){
		return outgoingUrls.get(url);
	}
	
}
