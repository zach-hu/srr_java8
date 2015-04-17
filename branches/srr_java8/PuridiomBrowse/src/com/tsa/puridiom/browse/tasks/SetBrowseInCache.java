/*
 * Created May 2005
 */
package com.tsa.puridiom.browse.tasks;

import com.tsa.puridiom.browse.Browse;
import com.tsa.puridiom.browse.BrowseManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author Kelli
 */
public class SetBrowseInCache extends Task {
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		
		try {
			Browse browse = (Browse) incomingRequest.get("browse");
			String organizationId = (String) incomingRequest.get("organizationId");
			String sessionId = (String) incomingRequest.get("sessionId");
			
			if (browse != null) {
			    BrowseManager.getInstance().setBrowseInCache(browse, organizationId, sessionId);
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return null;
		}
	}

}
