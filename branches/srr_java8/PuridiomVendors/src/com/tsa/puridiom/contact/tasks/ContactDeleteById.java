package com.tsa.puridiom.contact.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.entity.*;

public class ContactDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			Contact contact = (Contact)incomingRequest.get("contact");
			if(contact == null)
			{
				contact = new Contact();
				ContactSetValuesPK setValues = new ContactSetValuesPK();
				setValues.setValues(incomingRequest, contact);
			}
			
			dbs.delete(contact);
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return null;
	}
}