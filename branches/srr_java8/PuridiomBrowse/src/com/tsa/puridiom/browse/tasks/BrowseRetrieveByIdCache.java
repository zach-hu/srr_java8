package com.tsa.puridiom.browse.tasks;

import java.util.Map;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class BrowseRetrieveByIdCache extends Task {

	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			String	browseId = (String) incomingRequest.get("browseId") ;
			Browse browse = BrowseManager.getInstance().getBrowse(browseId);
			this.status = Status.SUCCEEDED;
			result = browse.getBrowseObject();
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}