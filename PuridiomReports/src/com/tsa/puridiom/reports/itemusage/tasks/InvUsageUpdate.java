package com.tsa.puridiom.reports.itemusage.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.Map;

public class InvUsageUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvUsage invUsage = (InvUsage)incomingRequest.get("invUsage");
			BigDecimal qty = (BigDecimal)incomingRequest.get("InvUsage_quantity");
			BigDecimal newQty = qty.add(invUsage.getQuantity());
			invUsage.setQuantity(newQty);
			
			if (invUsage == null)
			{
				throw new Exception ("InvUsage was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(invUsage);
		
			result = invUsage;
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