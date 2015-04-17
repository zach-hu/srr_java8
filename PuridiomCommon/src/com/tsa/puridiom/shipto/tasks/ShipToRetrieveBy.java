package com.tsa.puridiom.shipto.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ShipToRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ShipTo as shipto where 1=1 ");
		if(incomingRequest.containsKey("ShipTo_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("ShipTo_icHeader");
			queryString.append(" AND shipto.id.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("ShipTo_icLine"))
		{			
			String icLine = (String) incomingRequest.get("ShipTo_icLine");
			queryString.append(" AND shipto.id.icLine = '" + icLine + "'");
		}
		if(incomingRequest.containsKey("ShipTo_shipToCode"))
		{			
			String shipToCode = (String) incomingRequest.get("ShipTo_shipToCode");
			queryString.append(" AND shipto.id.shipToCode = '" + shipToCode + "'");
		}
		if(incomingRequest.containsKey("ShipTo_quantity"))
		{			
			String quantity = (String) incomingRequest.get("ShipTo_quantity");
			queryString.append(" AND shipto.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("ShipTo_attention"))
		{			
			String attention = (String) incomingRequest.get("ShipTo_attention");
			queryString.append(" AND shipto.attention = '" + attention + "'");
		}
		if(incomingRequest.containsKey("ShipTo_shipDate"))
		{			
			String shipDate = (String) incomingRequest.get("ShipTo_shipDate");
			queryString.append(" AND shipto.shipDate = '" + shipDate + "'");
		}
		if(incomingRequest.containsKey("ShipTo_shipToPriority"))
		{			
			String shipToPriority = (String) incomingRequest.get("ShipTo_shipToPriority");
			queryString.append(" AND shipto.id.shipToPriority = '" + shipToPriority + "'");
		}
		if(incomingRequest.containsKey("ShipTo_codeType"))
		{			
			String codeType = (String) incomingRequest.get("ShipTo_codeType");
			queryString.append(" AND shipto.codeType = '" + codeType + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}