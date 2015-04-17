package com.tsa.puridiom.sungard.vendorcontacts.tasks;

import com.tsa.puridiom.entity.sungard.VendorContacts;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SungardVendorContactsAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			VendorContacts vendorContacts = (VendorContacts)incomingRequest.get("sungardVendorContacts");
			if (vendorContacts == null)
			{
				throw new Exception ("VendorContacts [sungardVendorContacts] was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(vendorContacts);
		
			result = vendorContacts;
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