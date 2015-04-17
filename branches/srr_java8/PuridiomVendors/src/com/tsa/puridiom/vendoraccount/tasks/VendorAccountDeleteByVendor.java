package com.tsa.puridiom.vendoraccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorAccountDeleteByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String vendorId = (String) incomingRequest.get("VendorAccount_vendorId");
        String queryString = "from VendorAccount as v where v.id.vendorId = '" + vendorId + "'";

        dbs.delete(queryString);

		this.setStatus(dbs.getStatus());
		return null;
	}

}