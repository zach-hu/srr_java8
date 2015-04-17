package com.tsa.puridiom.taxcode.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class TaxCodeAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			TaxCode taxCode = (TaxCode)incomingRequest.get("taxCode");
			if (taxCode == null)
			{
				throw new Exception ("TaxCode was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(taxCode);
		
			result = taxCode;
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