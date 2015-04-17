package com.tsa.puridiom.vendor.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.VendorBuyerRel;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * 
 * @author Alexander
 *
 */
public class VendorBuyerRelUpdate extends Task
{
	
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			VendorBuyerRel vendorBuyerRel = (VendorBuyerRel)incomingRequest.get("vendorBuyerRel");
			if (vendorBuyerRel == null)
			{
				throw new Exception ("VendorBuyerRel was not found.");
			}
		
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			dbs.update(vendorBuyerRel);
		
			result = vendorBuyerRel;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
