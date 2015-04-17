package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddressAddList extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List addressList = (List)incomingRequest.get("newAddressList");
			for (Iterator iter = addressList.iterator(); iter.hasNext();)
			{
				Address address = (Address) iter.next();
				if (address == null)
				{
					throw new Exception ("Address was not found.");
				}
				dbs.add(address);
				this.setStatus(dbs.getStatus()) ;
				if(this.getStatus() != Status.SUCCEEDED)
				{
					throw new TsaException("An error ocurred inserting new address!");
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