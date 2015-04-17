package com.tsa.puridiom.po.subcontractor.tasks;

import com.tsa.puridiom.entity.SubContractor;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SubContractorUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			SubContractor subContractor = (SubContractor) incomingRequest.get("subContractor");
			if (subContractor == null)
			{
				throw new Exception ("SubContractor was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.update(subContractor);

			result = subContractor;
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