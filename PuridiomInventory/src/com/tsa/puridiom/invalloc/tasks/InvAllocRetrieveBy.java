package com.tsa.puridiom.invalloc.tasks;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvAllocRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvAlloc as invalloc where 1=1 ");
		if(incomingRequest.containsKey("InvAlloc.referenceType"))
		{			
			String referenceType = (String) incomingRequest.get("InvAlloc.referenceType");
			queryString.append(" AND invalloc.id.referenceType = '" + referenceType + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvAlloc.itemNumber");
			queryString.append(" AND invalloc.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.icLoc"))
		{			
			String icLoc = (String) incomingRequest.get("InvAlloc.icLoc");
			queryString.append(" AND invalloc.id.icLoc = '" + icLoc + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("InvAlloc.icHeader");
			queryString.append(" AND invalloc.id.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.icLine"))
		{			
			String icLine = (String) incomingRequest.get("InvAlloc.icLine");
			queryString.append(" AND invalloc.id.icLine = '" + icLine + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.aisle"))
		{			
			String aisle = (String) incomingRequest.get("InvAlloc.aisle");
			queryString.append(" AND invalloc.id.aisle = '" + aisle + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.locrow"))
		{			
			String locrow = (String) incomingRequest.get("InvAlloc.locrow");
			queryString.append(" AND invalloc.id.locrow = '" + locrow + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.tier"))
		{			
			String tier = (String) incomingRequest.get("InvAlloc.tier");
			queryString.append(" AND invalloc.id.tier = '" + tier + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.bin"))
		{			
			String bin = (String) incomingRequest.get("InvAlloc.bin");
			queryString.append(" AND invalloc.id.bin = '" + bin + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.quantity"))
		{			
			String quantity = (String) incomingRequest.get("InvAlloc.quantity");
			queryString.append(" AND invalloc.id.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.icHeaderHistory"))
		{			
			String icHeaderHistory = (String) incomingRequest.get("InvAlloc.icHeaderHistory");
			queryString.append(" AND invalloc.id.icHeaderHistory = '" + icHeaderHistory + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.lastQuantity"))
		{			
			String lastQuantity = (String) incomingRequest.get("InvAlloc.lastQuantity");
			queryString.append(" AND invalloc.id.lastQuantity = '" + lastQuantity + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.itemLocation"))
		{			
			String itemLocation = (String) incomingRequest.get("InvAlloc.itemLocation");
			queryString.append(" AND invalloc.id.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.lotNumber"))
		{			
			String lotNumber = (String) incomingRequest.get("InvAlloc.lotNumber");
			queryString.append(" AND invalloc.id.lotNumber = '" + lotNumber + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.itemDate"))
		{			
			String itemDate = (String) incomingRequest.get("InvAlloc.itemDate");
			queryString.append(" AND invalloc.id.itemDate = '" + itemDate + "'");
		}
		if(incomingRequest.containsKey("InvAlloc.icText"))
		{			
			String icText = (String) incomingRequest.get("InvAlloc.icText");
			queryString.append(" AND invalloc.id.icText = '" + icText + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}