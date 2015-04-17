/*
 * Created on Dec 26, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.contactContactsDeleteAddress.java
 */
 
package com.tsa.puridiom.contact.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.address.tasks.AddressDeleteById;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.ContactPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ContactsDeleteAddress extends Task
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
			List contacts = (List)incomingRequest.get("contacts");
			for (Iterator iter = contacts.iterator(); iter.hasNext();)
			{
				Contact contact = (Contact) iter.next();
				ContactPK pk = contact.getComp_id();
				incomingRequest.put("Address_addressCode", pk.getContactCode());
				
				AddressDeleteById delAddress = new AddressDeleteById();
				delAddress.executeTask(incomingRequest);
				this.setStatus(delAddress.getStatus());
				if(this.getStatus() != Status.SUCCEEDED)
				{
					break;
				}
				incomingRequest.put("contact", contact);
				ContactDeleteById delContact = new ContactDeleteById();
				
			}
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
