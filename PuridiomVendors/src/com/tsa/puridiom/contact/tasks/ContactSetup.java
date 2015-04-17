package com.tsa.puridiom.contact.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class ContactSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("Contact_contactCode", "");
			incomingRequest.put("Contact_contactType", "");
			incomingRequest.put("Contact_vendorId", "");
			incomingRequest.put("Contact_lastName", "");
			incomingRequest.put("Contact_firstName", "");
			incomingRequest.put("Contact_middleInit", "");
			incomingRequest.put("Contact_salutation", "");
			incomingRequest.put("Contact_contactTitle", "");
			incomingRequest.put("Contact_phoneNumber", "");
			incomingRequest.put("Contact_phoneFormat", "");
			incomingRequest.put("Contact_faxNumber", "");
			incomingRequest.put("Contact_faxFormat", "");
			incomingRequest.put("Contact_addressCode", "");
			incomingRequest.put("Contact_dateEntered", Dates.today("", ""));
			incomingRequest.put("Contact_dateExpires", Dates.today("", ""));
			incomingRequest.put("Contact_status", "");
			incomingRequest.put("Contact_owner", "");
			incomingRequest.put("Contact_emailAddr", "");
			incomingRequest.put("Contact_info1", "");
			incomingRequest.put("Contact_info2", "");
			incomingRequest.put("Contact_info3", "");
			incomingRequest.put("Contact_info4", "");
			incomingRequest.put("Contact_contactPassword", "");
			incomingRequest.put("Contact_passChanged", "");

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