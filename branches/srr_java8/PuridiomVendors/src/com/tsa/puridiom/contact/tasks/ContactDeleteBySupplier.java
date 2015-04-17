/*
 * Created on Dec 30, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.contactContactDeleteBySupplier.java
 */
 
package com.tsa.puridiom.contact.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.ContactPK;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ContactDeleteBySupplier extends Task
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
				incomingRequest.put("Contact_vendorId", pk.getVendorId());
				incomingRequest.put("Contact_contactType", pk.getContactType());
				incomingRequest.put("Contact_contactCode", pk.getContactCode());
				incomingRequest.put("contact", contact);
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("contact-deletebyid.xml");
				process.executeProcess(incomingRequest);
				
				this.setStatus(process.getStatus());
				if(this.getStatus() != Status.SUCCEEDED)
				{
					break;
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
