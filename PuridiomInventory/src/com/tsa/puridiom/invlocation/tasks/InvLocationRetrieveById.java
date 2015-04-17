package com.tsa.puridiom.invlocation.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String ) incomingRequest.get("InvLocation_itemNumber");
			String itemLocation = (String ) incomingRequest.get("InvLocation_itemLocation");

			String queryString = "from InvLocation as i where i.id.itemNumber = ? AND i.id.itemLocation = ? ";
			List resultList = dbs.query(queryString, new Object[] {itemNumber, itemLocation} , new Type[] {Hibernate.STRING, Hibernate.STRING} ) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}