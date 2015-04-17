/*
 * Created on October 04, 2004
 */
package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.RfqVendor;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqVendorSetBidList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqVendor rfqVendor = (RfqVendor) incomingRequest.get("rfqVendor");
			if (rfqVendor == null)
			{
				throw new Exception ("The RfqVendor entity was not found.");
			}
			
			rfqVendor.setRfqBidList((List) incomingRequest.get("rfqBidList"));
			
			result = rfqVendor;
			this.setStatus(Status.SUCCEEDED) ;
		}
        catch (Exception e)
        {
        	this.setStatus(Status.FAILED);
        	throw e;
        }
		return result;
	}
}
