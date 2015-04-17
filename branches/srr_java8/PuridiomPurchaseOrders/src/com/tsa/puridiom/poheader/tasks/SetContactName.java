/**
 * 
 * Created on Feb 2, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.SetContactName.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SetContactName extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			incomingRequest.put("Contact_contactCode", poHeader.getVendContactCode());
			incomingRequest.put("Contact_vendorId", poHeader.getVendorId());
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			throw new TsaException(e.toString(), e);
		}
		
		return super.executeTask(object);
	}

}
