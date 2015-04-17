package com.tsa.puridiom.invmanufacturer.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvManufacturerRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvManufacturer as invmanufacturer where 1=1 ");
		if(incomingRequest.containsKey("InvManufacturer_icManufacturer"))
		{			
			String icManufacturer = (String) incomingRequest.get("InvManufacturer_icManufacturer");
			queryString.append(" AND invmanufacturer.id.icManufacturer = '" + icManufacturer + "'");
		}
		if(incomingRequest.containsKey("InvManufacturer_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvManufacturer_itemNumber");
			queryString.append(" AND invmanufacturer.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvManufacturer_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("InvManufacturer_vendorId");
			queryString.append(" AND invmanufacturer.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("InvManufacturer_partNumber"))
		{			
			String partNumber = (String) incomingRequest.get("InvManufacturer_partNumber");
			queryString.append(" AND invmanufacturer.partNumber = '" + partNumber + "'");
		}
		if(incomingRequest.containsKey("InvManufacturer_notes"))
		{			
			String notes = (String) incomingRequest.get("InvManufacturer_notes");
			queryString.append(" AND invmanufacturer.notes = '" + notes + "'");
		}
		if(incomingRequest.containsKey("InvManufacturer_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("InvManufacturer_dateEntered");
			queryString.append(" AND invmanufacturer.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvManufacturer_owner"))
		{			
			String owner = (String) incomingRequest.get("InvManufacturer_owner");
			queryString.append(" AND invmanufacturer.owner = '" + owner + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}