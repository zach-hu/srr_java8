package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class InvItemRetrieveByLocation extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String ) incomingRequest.get("InvItem_itemNumber");
			String itemLocation = (String ) incomingRequest.get("InvLocation_itemLocation");

			String queryString = "from InvItem as InvItem, InvLocation as InvLocation" +
					" where InvItem.itemNumber = InvLocation.id.itemNumber" + 
					" and InvItem.itemNumber = ? " +
					" and InvLocation.id.itemLocation = ? ";
					
			List resultList = dbs.query(queryString, new Object[] {itemNumber, itemLocation} , 
				new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				Object resultObj[] = (Object[]) resultList.get(0);
				//set InvLocation entity in incomingRequest
				incomingRequest.put("invLocation", resultObj[1]);
				//return InvItem entity
				result = resultObj[0];
			}
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