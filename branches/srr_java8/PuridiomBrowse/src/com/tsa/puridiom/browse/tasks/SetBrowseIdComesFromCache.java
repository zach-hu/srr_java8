package com.tsa.puridiom.browse.tasks;

import java.util.Map;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class SetBrowseIdComesFromCache extends Task {

	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;

			String	browseIdComesFrom = (String) incomingRequest.get("browseIdComesFrom") ;
			incomingRequest.put("browseId",browseIdComesFrom) ;
			this.status = Status.SUCCEEDED;
			result = browseIdComesFrom;
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}