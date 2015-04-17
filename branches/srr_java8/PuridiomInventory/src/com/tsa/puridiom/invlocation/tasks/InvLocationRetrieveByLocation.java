package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationRetrieveByLocation extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemLocation = (String ) incomingRequest.get("InvLocation_itemLocation");

			String queryString = "from InvLocation as i where i.id.itemLocation = ? ";
			result = dbs.query(queryString, new Object[] {itemLocation} , new Type[] {Hibernate.STRING} ) ;
			
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName());
		}
		return result;
	}

}