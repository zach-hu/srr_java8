package com.tsa.puridiom.invlocation.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InvLocationDeleteList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List locationsList = (List)incomingRequest.get("invLocation");
			for (Iterator iter = locationsList.iterator(); iter.hasNext();) 
			{
				InvLocation invLocation = (InvLocation) iter.next();
				if (invLocation == null)
				{
					throw new Exception ("InvLocation was not found.");
				}
				dbs.delete(invLocation);
				this.setStatus(dbs.getStatus()) ;
				if(this.getStatus() != Status.SUCCEEDED)
				{
					throw new TsaException("An error ocurred inserting new location!");
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}