package com.tsa.puridiom.contact.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ContactVendorUpdateList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List contactVendorList = (List) incomingRequest.get("contactVendorList");
			for(int x=0;x< contactVendorList.size();x++){
				Contact contact = (Contact)contactVendorList.get(x);
				dbs.update(contact);
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
