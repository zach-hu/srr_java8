package com.tsa.puridiom.vendorregister.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorRegisterSetupFromContactValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{	    	
			if (incomingRequest.containsKey("Contact_contactType"))
			{
			    incomingRequest.put("VendorRegister_contactType", incomingRequest.get("Contact_contactType"));
			}
			if (incomingRequest.containsKey("Contact_lastName"))
			{
			    incomingRequest.put("VendorRegister_contactLastName", incomingRequest.get("Contact_lastName"));
			}
			if (incomingRequest.containsKey("Contact_firstName"))
			{
			    incomingRequest.put("VendorRegister_contactFirstName", incomingRequest.get("Contact_firstName"));
			}
			if (incomingRequest.containsKey("Contact_middleInit"))
			{
			    incomingRequest.put("VendorRegister_contactMidInit", incomingRequest.get("Contact_middleInit"));
			}
			if (incomingRequest.containsKey("Contact_salutation"))
			{
			    incomingRequest.put("VendorRegister_contactSalutation",incomingRequest.get("Contact_salutation"));
			}
			if (incomingRequest.containsKey("Contact_contactTitle"))
			{
			    incomingRequest.put("VendorRegister_contactTitle", incomingRequest.get("Contact_contactTitle"));
			}
			if (incomingRequest.containsKey("Contact_phoneNumber"))
			{
			    incomingRequest.put("VendorRegister_contactPhoneNo", incomingRequest.get("Contact_phoneNumber"));
			}
			if (incomingRequest.containsKey("Contact_faxNumber"))
			{
			    incomingRequest.put("VendorRegister_contactFaxNumber", incomingRequest.get("Contact_faxNumber"));
			}
			if (incomingRequest.containsKey("Contact_emailAddr"))
			{
			    incomingRequest.put("VendorRegister_contactEmailAddr", incomingRequest.get("Contact_emailAddr"));
			}
			if (incomingRequest.containsKey("Contact_contactPassword"))
			{
			    incomingRequest.put("VendorRegister_contactPassword", incomingRequest.get("Contact_contactPassword"));
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