package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.commodity.CommodityManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * Creation date: March 4, 2005
 * @author: Kelli
 */
public class CommoditySetInCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try 
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			Commodity commodity = (Commodity) incomingRequest.get("commodity");
			
			if (commodity != null)
			{
			    CommodityManager.getInstance().setCommodityInCache(organizationId, commodity);
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
			return result;
		}
	}

}
