package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class RegisterQualifiedContact extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");

			if (registerUser == null)
			{
				registerUser = new RegisterUser();
			}

			incomingRequest.put("Contact_contactCode", "");
			incomingRequest.put("Contact_contactType", "ALTERNATE");
			incomingRequest.put("Contact_vendorId", registerUser.getVendorId());
			incomingRequest.put("Contact_lastName", registerUser.getLastName());
			incomingRequest.put("Contact_firstName", registerUser.getFirstName());
			incomingRequest.put("Contact_middleInit", registerUser.getMiddleInitial());
			incomingRequest.put("Contact_salutation", "");
			incomingRequest.put("Contact_contactTitle", "");
			incomingRequest.put("Contact_phoneNumber", registerUser.getPhoneNumber());
			incomingRequest.put("Contact_faxNumber", registerUser.getFaxNumber());
			incomingRequest.put("Contact_dateEntered", Dates.today("yyyy-MM-dd", ""));
			incomingRequest.put("Contact_dateExpires", Dates.today("yyyy-MM-dd", ""));
			incomingRequest.put("Contact_status", "02");
			incomingRequest.put("Contact_owner", "PURIDIOM-WEB");
			incomingRequest.put("Contact_emailAddr", registerUser.getEmailAddress());
			incomingRequest.put("Contact_contactPassword", registerUser.getContactPassword());

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