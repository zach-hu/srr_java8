package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RfqVendorRetrieveVendorIdsByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Object result = null;

		try
		{
			String icRfqHeader = (String) incomingRequest.get("RfqVendor_icRfqHeader");
			if (icRfqHeader == null)
			{
				icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			}
			StringBuffer queryString = new StringBuffer("Select rfqvendor.id.vendorId from RfqVendor as rfqvendor where rfqvendor.id.icRfqHeader = '" + icRfqHeader + "'");

			result = dbs.query(queryString.toString());
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}