package com.tsa.puridiom.invbinlocation.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvBinLocationRetrieveByItem extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String inventoryFlag = "";
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
		StringBuffer queryString = new StringBuffer("from InvBinLocation as invbinlocation where 1=1 ");
		boolean	permanentOnly = Utility.ckNull((String) incomingRequest.get("permanentOnly")).equals("true");

		if (receiptLine != null)
		{
			inventoryFlag = receiptLine.getInventoryFlag();
		}

		if(incomingRequest.containsKey("InvBinLocation_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvBinLocation_itemNumber");
			queryString.append(" AND invbinlocation.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_itemLocation"))
		{			
			String itemLocation = (String) incomingRequest.get("InvBinLocation_itemLocation");
			queryString.append(" AND invbinlocation.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_tempIc") && !permanentOnly)
		{
		    String tempIc = (String) incomingRequest.get("InvBinLocation_tempIc");
		    queryString.append(" AND invbinlocation.tempIc = '" + tempIc + "'");
			//queryString.append(" AND (invbinlocation.status is null OR invbinlocation.status <> '00' OR invbinlocation.tempIc = '" + tempIc + "')");
		}
		else if(!permanentOnly)
		{
		    // Default to not include temporary records, unless specified otherwise
			if (!inventoryFlag.equalsIgnoreCase("K")){
				queryString.append(" AND (invbinlocation.status is null OR invbinlocation.status <> '00')");
			}
		}
		
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}