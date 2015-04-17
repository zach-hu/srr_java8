package com.tsa.puridiom.purchasecard.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class PurchaseCardRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from PurchaseCard as purchasecard where 1=1 ");
		if(incomingRequest.containsKey("PurchaseCard_pcardCode"))
		{			
			String pcardCode = (String) incomingRequest.get("PurchaseCard_pcardCode");
			queryString.append(" AND purchasecard.id.pcardCode = '" + pcardCode + "'");
		}
		if(incomingRequest.containsKey("PurchaseCard_pcardNumber"))
		{			
			String pcardNumber = (String) incomingRequest.get("PurchaseCard_pcardNumber");
			queryString.append(" AND purchasecard.pcardNumber = '" + pcardNumber + "'");
		}
		if(incomingRequest.containsKey("PurchaseCard_pcardExpires"))
		{			
			String pcardExpires = (String) incomingRequest.get("PurchaseCard_pcardExpires");
			queryString.append(" AND purchasecard.pcardExpires = '" + pcardExpires + "'");
		}
		if(incomingRequest.containsKey("PurchaseCard_pcardBank"))
		{			
			String pcardBank = (String) incomingRequest.get("PurchaseCard_pcardBank");
			queryString.append(" AND purchasecard.pcardBank = '" + pcardBank + "'");
		}
		if(incomingRequest.containsKey("PurchaseCard_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("PurchaseCard_dateEntered");
			queryString.append(" AND purchasecard.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("PurchaseCard_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("PurchaseCard_dateExpires");
			queryString.append(" AND purchasecard.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("PurchaseCard_status"))
		{			
			String status = (String) incomingRequest.get("PurchaseCard_status");
			queryString.append(" AND purchasecard.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("PurchaseCard_owner"))
		{			
			String owner = (String) incomingRequest.get("PurchaseCard_owner");
			queryString.append(" AND purchasecard.owner = '" + owner + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}