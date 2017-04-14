/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Vector;

import org.junit.Test;

import pagerank.WebGraph;
import pagerank.PageRankCalculator;


/**
 * @author Harsha Perera
 *
 */
public class PageRankCalculatorTest {
	
    @Test
    public void testBasicPageRank() {
    	WebGraph graph = new WebGraph();
    	Vector<String> outgoing_1 = new Vector<>();
    	outgoing_1.add("2.html");
    	outgoing_1.add("3.html");
    	outgoing_1.add("4.html");
    	graph.addPage("1.html", outgoing_1);
    	
    	Vector<String> outgoing_2 = new Vector<>();
    	outgoing_2.add("3.html");
    	outgoing_2.add("4.html");
    	graph.addPage("2.html", outgoing_2);    	
    	
    	Vector<String> outgoing_3 = new Vector<>();
    	outgoing_3.add("1.html");
    	graph.addPage("3.html", outgoing_3);
    	
    	Vector<String> outgoing_4 = new Vector<>();
    	outgoing_4.add("1.html");
    	outgoing_4.add("3.html");
    	graph.addPage("4.html", outgoing_4);    	
    	
    	PageRankCalculator pageRankCalc = new PageRankCalculator();
    	HashMap<String, Double> pageRank = pageRankCalc.calculatePageRank(graph, 7, 0);
        assertEquals(pageRank.size(), 4);        
        assertEquals(0.38, pageRank.get("1.html").doubleValue(), 0.01);
        assertEquals(0.12, pageRank.get("2.html").doubleValue(), 0.01);
        assertEquals(0.29, pageRank.get("3.html").doubleValue(), 0.01);
        assertEquals(0.19, pageRank.get("4.html").doubleValue(), 0.01);
    }
	

}
