package com.tsa.puridiom.itemcrossref.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.sql.Date;
import java.util.Map;

public class ItemCrossRefSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ItemCrossRef itemCrossRef = (ItemCrossRef) incomingRequest.get("itemCrossRef");
			if (itemCrossRef == null)
			{
				itemCrossRef = new ItemCrossRef();
			}

			if (incomingRequest.containsKey("ItemCrossRef_altItemNumber"))
			{
				String altItemNumber = (String) incomingRequest.get("ItemCrossRef_altItemNumber");
				itemCrossRef.setAltItemNumber(altItemNumber);
			}
			if (incomingRequest.containsKey("ItemCrossRef_description"))
			{
				String description = (String) incomingRequest.get("ItemCrossRef_description");
				itemCrossRef.setDescription(description);
			}
			if (incomingRequest.containsKey("ItemCrossRef_itemNumber"))
			{
				String itemNumber = (String) incomingRequest.get("ItemCrossRef_itemNumber");
				itemCrossRef.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("ItemCrossRef_source"))
			{
				String source = (String) incomingRequest.get("ItemCrossRef_source");
				itemCrossRef.setSource(source);
			}
			if (incomingRequest.containsKey("ItemCrossRef_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("ItemCrossRef_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				itemCrossRef.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("ItemCrossRef_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("ItemCrossRef_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				itemCrossRef.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("ItemCrossRef_status"))
			{
				String status = (String) incomingRequest.get("ItemCrossRef_status");
				itemCrossRef.setStatus(status);
			}
			if (incomingRequest.containsKey("ItemCrossRef_owner"))
			{
				String owner = (String) incomingRequest.get("ItemCrossRef_owner");
				itemCrossRef.setOwner(owner);
			}

			result = itemCrossRef;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}