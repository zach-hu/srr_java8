package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class BrowseForeignDatabaseSetup extends Task {
    
	public Object  executeTask (Object object) throws Exception {
	    try {
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			BrowseObject	browseObject = (BrowseObject) incomingRequest.get("browseObject") ;
			
			incomingRequest.put("originalOrganizationId", organizationId);
			if (!Utility.isEmpty(browseObject.getForeignDatabase())) {
			    incomingRequest.put("organizationId", browseObject.getForeignDatabase()) ;
			}
			
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return null ;
	    }
	}
}