package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.BrowseManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class BrowseCleanupByBrowseId extends Task {

	public Object  executeTask (Object object) throws Exception {
	    try {
			Map incomingRequest = (Map)object;
			String	browseId = (String) incomingRequest.get("browseId") ;

			BrowseManager.getInstance().destroyBrowse(browseId);
			
			this.status = Status.SUCCEEDED;
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return null;
	    }
	}
}