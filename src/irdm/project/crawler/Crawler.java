/**
 * 
 */
package irdm.project.crawler;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.terrier.indexing.TaggedDocument;
import org.terrier.indexing.tokenisation.Tokeniser;
import org.terrier.realtime.memory.MemoryIndex;
import org.terrier.utility.ApplicationSetup;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import irdm.project.index.CustomIndexData;

/**
 * @author Shruti Sinha
 *
 */
public class Crawler extends WebCrawler{
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    
    static{
    	
    }

    boolean configured = false;
    Object configurelock = new Object();

    Tokeniser tokeniser;
    String doctags = "WebCrawlerTags";

    String hostname;
    MemoryIndex index;

    public void init() {
    	synchronized (configurelock) {
    		ApplicationSetup.setProperty("TaggedDocument.abstracts", "title,body");
    		ApplicationSetup.setProperty("TaggedDocument.abstracts.tags", "title,ELSE");
    		ApplicationSetup.setProperty("TaggedDocument.abstracts.lengths", "140,5000");
    		ApplicationSetup.setProperty("TaggedDocument.abstracts.tags.casesensitive", "false");
    		
    		tokeniser = Tokeniser.getTokeniser();  		
    		CustomIndexData data = (CustomIndexData)this.getMyController().getCustomData();
    		hostname = data.getHosturl();
    		index = data.getMemIndex();
    		configured=true;
    	}
    }


    @Override
    public boolean shouldVisit(Page page,WebURL url) {
    	if (!configured) init();
    	String href = url.getURL().toLowerCase();  
    	   	

    	this.getMyController().getCustomData();
    	return !FILTERS.matcher(href).matches() && href.contains(hostname);
    }


    @Override
    public void visit(Page page) {       
    	if (!configured) init();
    	String url = page.getWebURL().getURL();
    	System.out.println("URL: " + url);

    	if (page.getParseData() instanceof HtmlParseData) {
    		HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
    		String html = htmlParseData.getHtml();

    		Map<String,String> docProperties = new HashMap<String,String>();    		
    		docProperties.put("url", url);
    		docProperties.put("encoding", "UTF-8");

    		ByteArrayInputStream inputStream = null;
    		try {
    			inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
    		} catch (UnsupportedEncodingException e1) {    			
    			throw new AssertionError(e1);
    		}

    		//TaggedDocument tg = new TaggedDocument(inputStream,docProperties,tokeniser,doctags,exacttags,fieldtags);
    		TaggedDocument tg = new TaggedDocument(inputStream,docProperties,tokeniser);
    		try {
    			index.indexDocument(tg);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
}
