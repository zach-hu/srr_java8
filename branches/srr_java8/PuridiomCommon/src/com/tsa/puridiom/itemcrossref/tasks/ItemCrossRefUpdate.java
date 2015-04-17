package com.tsa.puridiom.itemcrossref.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ItemCrossRefUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			ItemCrossRef itemCrossRef = (ItemCrossRef)incomingRequest.get("itemCrossRef");
			if (itemCrossRef == null)
			{
				throw new Exception ("ItemCrossRef was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(itemCrossRef);
		
			result = itemCrossRef;
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