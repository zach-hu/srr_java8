package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsa.puridiom.browse.BrowseUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrowseRetrieveById extends Task {

	public Object  executeTask (Object object) throws Exception {
	    Object result = null;
	    try {
			Map incomingRequest = (Map)object;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			String	browseId = (String) incomingRequest.get("browseId") ;
			Browse browse = BrowseManager.getInstance().getBrowse(browseId);

			result = browse;
	    } catch (Exception e) {
	        this.status = Status.FAILED;
	        throw e;
	    } finally {
	        return result ;
	    }
	}
}