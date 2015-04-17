/*
 * Created on March 21, 2007
 *
 * @author  KC
 * project: HiltonVendors
 *
 */
package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ContactSetValuesFromOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			Contact contact = (Contact) incomingRequest.get("contact");

			if (poHeader != null && contact != null)
			{
				String contactName = poHeader.getContactName();
				if (contactName.length() > 0)
				{
					int index = contactName.indexOf(" ");
					if (contactName.length() > index)
					{
						String firstName = contactName.substring(0, index);
						String lastName = contactName.substring(index, contactName.length());
						contact.setFirstName(firstName);
						contact.setLastName(lastName);
					}
				}
				contact.setPhoneNumber(poHeader.getContactPhone());
				contact.setMobileNumber(poHeader.getContactMobilePhone());

				poHeader.setVendorId(contact.getComp_id().getVendorId());
				poHeader.setContactAddr("DEFAULT");

				incomingRequest.put("contact", contact);
				incomingRequest.put("poHeader", poHeader);
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}