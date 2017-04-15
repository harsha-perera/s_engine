/**
 * 
 */
package irdm.project.index;

import org.terrier.realtime.memory.MemoryIndex;

/**
 * @author Shruti Sinha
 *
 */
public class CustomIndexData {
	private String hosturl;
	private MemoryIndex memIndex;
	
	public CustomIndexData(String hosturl, MemoryIndex memIndex){
		this.hosturl = hosturl;
		this.memIndex = memIndex;
	}
	
	
	public String getHosturl() {
		return hosturl;
	}

	public MemoryIndex getMemIndex() {
		return memIndex;
	}
}
