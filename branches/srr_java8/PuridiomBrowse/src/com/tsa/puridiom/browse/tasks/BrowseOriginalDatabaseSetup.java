package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class BrowseOriginalDatabaseSetup extends Task {
    
	public Object  executeTask (Object object) throws Exception {
	    try {
			Map incomingRequest = (Map)object;
			String	originalOrganizationId = (String) incomingRequest.get("originalOrganizationId") ;
			BrowseObject	browseObject = (BrowseObject) incomingRequest.get("browseObject") ;
			
			if (!Utility.isEmpty(originalOrganizationId)) {
			    incomingRequest.put("organizationId", originalOrganizationId);
			}
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return null ;
	    }
	}
}