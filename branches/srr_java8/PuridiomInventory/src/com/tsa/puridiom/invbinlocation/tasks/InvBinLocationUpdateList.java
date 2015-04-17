/*
 * Created on Nov 17, 2003 
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class InvBinLocationUpdateList extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List binLocations = (List)incomingRequest.get("invBinLocations");
		
		for (int i = 0; i < binLocations.size(); i++)
		{
			InvBinLocation binLocation = (InvBinLocation)binLocations.get(i);
			incomingRequest.put("invBinLocation", binLocation);
			InvBinLocationUpdate update = new InvBinLocationUpdate();
			update.executeTask(incomingRequest);
			if(update.getStatus() != Status.SUCCEEDED)
			{
				i = binLocations.size();
			}
			this.setStatus(update.getStatus());
		}
		
		return binLocations;
	}

}
