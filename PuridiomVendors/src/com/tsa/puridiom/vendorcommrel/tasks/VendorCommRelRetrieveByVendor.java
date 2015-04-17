package com.tsa.puridiom.vendorcommrel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import java.util.List;
import java.util.Map;

public class VendorCommRelRetrieveByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;

		try {

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from VendorCommRel as vendorcommrel where 1=1 ");

			String vendorId = (String) incomingRequest.get("VendorCommRel_vendorId");
			if (Utility.isEmpty(vendorId)) {
			    vendorId = (String) incomingRequest.get("Vendor_vendorId");
			}

			queryString.append(" AND vendorcommrel.id.vendorId = '" + vendorId + "'");

			result = dbs.query(queryString.toString()) ;

			this.setStatus(Status.SUCCEEDED);
			return result ;

		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, " Vendor Id cannot be empty for VendorCommRelRetrieveByVendor " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}