package com.tsa.puridiom.taxcode.tasks;

import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class TaxCodeUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			TaxCode taxCode = (TaxCode) incomingRequest.get("taxCode");
			if (taxCode == null)
			{
				throw new Exception ("TaxCode was not found.");
			}
		
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			dbs.update(taxCode);
		
			result = taxCode;
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