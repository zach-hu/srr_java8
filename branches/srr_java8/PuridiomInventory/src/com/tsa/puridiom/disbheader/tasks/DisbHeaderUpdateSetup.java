/*
 * Created on July 13, 2004
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.*;

import com.tsa.puridiom.entity.DisbHeader;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator 
 */
public class DisbHeaderUpdateSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;

		incomingRequest.put("DisbLine_icDsbHeader",(String)incomingRequest.get("DisbHeader_icDsbHeader")) ;
		incomingRequest.put("Account_icHeader",(String)incomingRequest.get("DisbHeader_icDsbHeader"));
		incomingRequest.put("Account_icLine", "0");
		incomingRequest.put("Default_referenceType", "DBH");
		
		Object itemLocationObj = incomingRequest.get("InvLocation_itemLocation");
		String itemLocation;
		
		if (itemLocationObj instanceof String[])
		{
			String itemLocations[] = (String[]) itemLocationObj;
			itemLocation = itemLocations[0];
		}
		else
		{
			itemLocation = (String)incomingRequest.get("InvLocation_itemLocation");
		}
		
		if (itemLocation != null && itemLocation.length() > 0)
		{
			DisbHeader disbHeader = (DisbHeader) incomingRequest.get("disbHeader");
			if (disbHeader != null)
			{
				disbHeader.setItemLocation(itemLocation);
			}
		}
		
		return null ;
	}
}
