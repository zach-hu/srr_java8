/**
 * 
 * Created on Feb 2, 2004
 * @author renzo
 * project: HiltonVendors
 * com.tsa.puridiom.contact.tasks.GetContactName.java
 * 
 */
package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class GetContactName extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Contact contact = null;
		String name = "";
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String contactCode = (String ) incomingRequest.get("Contact_contactCode");
			String vendorId = (String ) incomingRequest.get("Contact_vendorId");

			String queryString = "from Contact as Contact where Contact.id.contactCode = ? and Contact.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {contactCode, vendorId, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				this.setStatus(dbs.getStatus()) ;
				contact = (Contact)resultList.get(0);
				if(contact == null)
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("Contact " + contactCode + " could not be found!");
				}
				String salute = contact.getSalutation();
				String first = contact.getFirstName();
				String middle = contact.getMiddleInit();
				String last = contact.getLastName();
				
				if(!Utility.isEmpty(salute)){	name = name + salute;	}
				if(!Utility.isEmpty(first)){	name = name + " " + first;	}
				if(!Utility.isEmpty(middle)){	name = name + " " +  middle;	}
				if(!Utility.isEmpty(last)){	name = name + " " +  last;	}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(e.toString());
		}
		return name;
	}

}