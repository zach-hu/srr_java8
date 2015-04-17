package com.tsa.puridiom.address.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class AddressUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			Address address = (Address)incomingRequest.get("address");
			if (address == null)
			{
				throw new Exception ("Address was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(address);
		
			result = address;
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