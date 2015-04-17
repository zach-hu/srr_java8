/**
 * Created on Mar 31, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoRevisionSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoRevisionSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
			if(Utility.isEmpty(poNumber))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Po Number can not be empty!");
			}
			//incomingRequest.put("newPoHeader_poNumber", poNumber);
			String poType = (String)incomingRequest.get("PoHeader_poType");
			incomingRequest.put("releaseType", poType);
			
			incomingRequest.put("poNumberValidated", "Y");
			incomingRequest.put("createRevision", "Y");
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}
