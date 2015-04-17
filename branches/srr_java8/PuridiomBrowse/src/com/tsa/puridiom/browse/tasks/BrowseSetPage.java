package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class BrowseSetPage extends Task {
    
	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			String	browseId = (String) incomingRequest.get("browseId") ;
			Browse browse = BrowseManager.getInstance().getBrowse(browseId);
			int	newPage = Integer.valueOf((String) incomingRequest.get("newPage")).intValue();
			
			if (newPage > browse.getPageCount()) {
			    incomingRequest.put("errorMsg", "Invalid Page [" + newPage + "]");
			    newPage = 1;
			}

			browse.setCurrentPage(newPage);
			
			result = browse;
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}