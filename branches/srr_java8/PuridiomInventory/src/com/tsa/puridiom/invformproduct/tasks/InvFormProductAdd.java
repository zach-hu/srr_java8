package com.tsa.puridiom.invformproduct.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class InvFormProductAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvFormProduct invFormProduct = (InvFormProduct)incomingRequest.get("invFormProduct");
			if (invFormProduct == null)
			{
				throw new Exception ("InvFormProduct was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(invFormProduct);
		
			result = invFormProduct;
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