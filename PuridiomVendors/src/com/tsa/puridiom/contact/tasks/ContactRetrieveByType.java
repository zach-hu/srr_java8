package com.tsa.puridiom.contact.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class ContactRetrieveByType extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String contactType = (String ) incomingRequest.get("Contact_contactType");
			String vendorId = (String ) incomingRequest.get("Contact_vendorId");

			String queryString = "from Contact as Contact where Contact.id.contactType = ? and Contact.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {contactType, vendorId, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
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