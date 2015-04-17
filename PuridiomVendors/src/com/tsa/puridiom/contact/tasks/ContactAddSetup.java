/*
 * Created on Dec 23, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.contactContactAddSetup.java
 */

package com.tsa.puridiom.contact.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Contact;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;


public class ContactAddSetup extends Task
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
			Contact contact = new Contact();

			incomingRequest.put("contact", contact);

			incomingRequest.put("Contact_passChanged", Dates.today("yyyy-MM-dd", ""));

			if (!incomingRequest.containsKey("Contact_addressCode")) {
				String	contactCode = (String) incomingRequest.get("Contact_contactCode");
                String userTimeZone = (String) incomingRequest.get("userTimeZone");

				if (Utility.isEmpty(contactCode)) {
					contactCode = "DEFAULT";
				}
				incomingRequest.put("Contact_addressCode", contactCode);
				incomingRequest.put("Contact_dateEntered", Dates.today("yyyy-MM-dd", userTimeZone));
			}
            if (incomingRequest.containsKey("Contact_owner")) {
                incomingRequest.put("Contact_owner", (String) incomingRequest.get("userId"));
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
