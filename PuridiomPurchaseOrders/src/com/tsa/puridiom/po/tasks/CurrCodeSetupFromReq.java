/**
 * 
 * Created on Feb 2, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.CurrCodeSetupFromRfq.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class CurrCodeSetupFromReq extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		Vendor vendor = (Vendor)incomingRequest.get("vendor");
		if(vendor == null)
		{
		//	this.setStatus(Status.FAILED);
		//	throw new TsaException("Vendor Entity could not be found!");
		}
		else
		{
			this.setStatus(Status.SUCCEEDED);
			ret = vendor.getCurrencyCode();
		}
		
		return ret;
	}

}
