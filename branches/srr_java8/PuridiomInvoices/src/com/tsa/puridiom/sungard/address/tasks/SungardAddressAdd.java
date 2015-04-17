package com.tsa.puridiom.sungard.address.tasks;

import com.tsa.puridiom.entity.sungard.Address;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SungardAddressAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			Address address = (Address)incomingRequest.get("sungardAddress");
			if (address == null)
			{
				throw new Exception ("Address [sungardAddress] was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(address);
		
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