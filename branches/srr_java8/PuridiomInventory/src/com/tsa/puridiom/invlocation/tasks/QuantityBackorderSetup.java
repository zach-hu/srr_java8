/*
 * Created on Jun 4, 2004
 *
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invlocation.tasks.QuantityBackorderSetup.java
 * 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class QuantityBackorderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			String location = (String)incomingRequest.get("InvLocation_itemLocation");
			incomingRequest.put("RequisitionLine_itemLocation", location);
			String number = (String)incomingRequest.get("InvLocation_itemNumber");
			incomingRequest.put("RequisitionLine_itemNumber", number);
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
