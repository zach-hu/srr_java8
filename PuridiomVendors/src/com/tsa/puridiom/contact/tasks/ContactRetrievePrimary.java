/*
 * Created on Dec 23, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.contactContactRetrievePrimary.java
 */

package com.tsa.puridiom.contact.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ContactRetrievePrimary extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("Contact_vendorId");
			String	oid = (String) incomingRequest.get("organizationId");
			if(oid.equalsIgnoreCase("HOY08P"))
			{
				String queryString = "from Contact as Contact where Contact.id.vendorId = ? ";
				List resultList = dbs.query(queryString, new Object[] {vendorId } , new Type[] { Hibernate.STRING }) ;

				if (resultList != null && resultList.size() > 0)
				{
					ret = resultList;
				}
			}
			else
			{
				String queryString = "from Contact as Contact where Contact.id.contactType = ? and Contact.id.vendorId = ? ";
				List resultList = dbs.query(queryString, new Object[] {"DEFAULT", vendorId } , new Type[] { Hibernate.STRING, Hibernate.STRING }) ;

				if (resultList != null && resultList.size() > 0)
				{
					ret = resultList.get(0);
				}
			}
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
