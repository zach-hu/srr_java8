package com.tsa.puridiom.autogen.tasks;

import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.autogen.AutoGenManager;
import com.tsa.puridiom.commodity.CommodityManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * Creation date: August 27, 2010
 * @author: Kelli
 */
public class AutoGenRefreshCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			String	organizationId = (String) incomingRequest.get("organizationId");

			AutoGenManager.getInstance().refresh(organizationId);
			
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
