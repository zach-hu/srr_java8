package com.tsa.puridiom.purchasecard.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PurchaseCardUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			PurchaseCard purchaseCard = (PurchaseCard)incomingRequest.get("purchaseCard");
			if (purchaseCard == null)
			{
				throw new Exception ("PurchaseCard was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(purchaseCard);
		
			result = purchaseCard;
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