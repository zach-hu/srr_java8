package com.tsa.puridiom.vendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.List;
import java.util.Map;

public class VendorRetrieveByEin extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
	    try
	    {
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorEin = (String) incomingRequest.get("Vendor_vendorEin");

			//vendorEin = Utility.ckNull(vendorName).toUpperCase().trim();

			List result = dbs.query("from Vendor as vendor where vendor.vendorEin ='" + vendorEin + "'") ;
			this.setStatus(dbs.getStatus()) ;
			return result ;
	    }
	    catch (Exception e)
	    {
	        throw e;
	    }
	}
}