package com.tsa.puridiom.contact.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ContactVendorList extends Task {

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("Contact_vendorId");
			
			
			String queryString = "from Contact as Contact where Contact.id.contactType = ? and Contact.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {"DEFAULT", vendorId } , new Type[] { Hibernate.STRING, Hibernate.STRING }) ;

			ret = resultList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}
