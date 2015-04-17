package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RegisterUserVendorRegisterAltSetup extends Task
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

			incomingRequest.put("VendorRegister_contactEmailAddr", registerUser.getEmailAddress());
			incomingRequest.put("VendorRegister_vendorId", registerUser.getVendorId());
			incomingRequest.put("VendorRegister_contactPhoneNo", registerUser.getPhoneNumber());
			incomingRequest.put("VendorRegister_contactFaxNumber", registerUser.getFaxNumber());
			incomingRequest.put("VendorRegister_contactFirstName", registerUser.getFirstName());
			incomingRequest.put("VendorRegister_contactLastName", registerUser.getLastName());
			incomingRequest.put("VendorRegister_contactMidInit", registerUser.getMiddleInitial());
			incomingRequest.put("VendorRegister_contactPassword", registerUser.getContactPassword());
			incomingRequest.put("VendorRegister_contactType", "ALTERNATE");
			incomingRequest.put("VendorRegister_vendorEin", registerUser.getVendorEin());
			incomingRequest.put("VendorRegister_vendorFaxNumber", registerUser.getFaxNumber());
			incomingRequest.put("VendorRegister_vendorName", registerUser.getVendorName());
			incomingRequest.put("VendorRegister_altEmailAddr", "");
			
			incomingRequest.remove("vendorRegister");
						
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