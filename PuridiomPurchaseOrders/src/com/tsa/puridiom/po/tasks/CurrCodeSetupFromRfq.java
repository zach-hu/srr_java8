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

import com.tsa.puridiom.entity.RfqVendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class CurrCodeSetupFromRfq extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		RfqVendor rfqVendor = (RfqVendor)incomingRequest.get("rfqVendor");
		if(rfqVendor == null)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Vendor Entity could not be found!");
		}
		else
		{
			this.setStatus(Status.SUCCEEDED);
			ret = rfqVendor.getVendCurrency();
		}
		
		return ret;
	}

}
