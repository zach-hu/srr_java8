package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RegisterUserSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
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

			if (incomingRequest.containsKey("RegisterUser_alternate"))
			{
				String alternate = (String) incomingRequest.get("RegisterUser_alternate");
				boolean bvalue = false;
				try {
					bvalue = Boolean.valueOf(alternate).booleanValue();
				} catch (Exception e) {
					//set to false
				} 
				registerUser.setAlternate(bvalue);
			}
			if (incomingRequest.containsKey("RegisterUser_authenticated"))
			{
				String authenticated = (String) incomingRequest.get("RegisterUser_authenticated");
				boolean bvalue = false;
				try {
					bvalue = Boolean.valueOf(authenticated).booleanValue();
				} catch (Exception e) {
					//set to false
				} 
				registerUser.setAuthenticated(bvalue);
			}
			if (incomingRequest.containsKey("RegisterUser_contactCode"))
			{
				String contactCode = (String ) incomingRequest.get("RegisterUser_contactCode");
				registerUser.setContactCode(contactCode);
			}
			if (incomingRequest.containsKey("RegisterUser_contactPassword"))
			{
				String contactPassword = (String ) incomingRequest.get("RegisterUser_contactPassword");
				registerUser.setContactPassword(contactPassword);
			}
			if (incomingRequest.containsKey("RegisterUser_vendorEin"))
			{
				String einNumber = (String) incomingRequest.get("RegisterUser_vendorEin");
				registerUser.setVendorEin(einNumber);
			}
			if (incomingRequest.containsKey("RegisterUser_einValid"))
			{
				String einValid = (String) incomingRequest.get("RegisterUser_einValid");
				boolean bvalue = false;
				try {
					bvalue = Boolean.valueOf(einValid).booleanValue();
				} catch (Exception e) {
					//set to false
				} 
				registerUser.setEinValid(bvalue);
			}
			if (incomingRequest.containsKey("RegisterUser_emailAddress"))
			{
				String emailAddress = (String) incomingRequest.get("RegisterUser_emailAddress");
				registerUser.setEmailAddress(emailAddress);
			}
			if (incomingRequest.containsKey("RegisterUser_emailAddressDuplicated"))
			{
				String emailAddressDuplicated = (String) incomingRequest.get("RegisterUser_emailAddressDuplicated");
				boolean bvalue = false;
				try {
					bvalue = Boolean.valueOf(emailAddressDuplicated).booleanValue();
				} catch (Exception e) {
					//set to false
				} 
				registerUser.setEmailAddressDuplicated(bvalue);
			}
			if (incomingRequest.containsKey("RegisterUser_faxNumber"))
			{
				String faxNumber = (String) incomingRequest.get("RegisterUser_faxNumber");
				registerUser.setFaxNumber(faxNumber);
			}
			if (incomingRequest.containsKey("RegisterUser_firstName"))
			{
				String firstName = (String) incomingRequest.get("RegisterUser_firstName");
				registerUser.setFirstName(firstName);
			}
			if (incomingRequest.containsKey("RegisterUser_lastName"))
			{
				String lastName = (String) incomingRequest.get("RegisterUser_lastName");
				registerUser.setLastName(lastName);
			}
			if (incomingRequest.containsKey("RegisterUser_middleInitial"))
			{
				String middleInitial = (String) incomingRequest.get("RegisterUser_middleInitial");
				registerUser.setMiddleInitial(middleInitial);
			}
			if (incomingRequest.containsKey("RegisterUser_organizationId"))
			{
				String organizationId = (String) incomingRequest.get("RegisterUser_organizationId");
				registerUser.setOrganizationId(organizationId);
			}
			if (incomingRequest.containsKey("RegisterUser_phoneNumber"))
			{
				String phoneNumber = (String) incomingRequest.get("RegisterUser_phoneNumber");
				registerUser.setPhoneNumber(phoneNumber);
			}
			if (incomingRequest.containsKey("RegisterUser_qualified"))
			{
				String qualified = (String) incomingRequest.get("RegisterUser_qualified");
				boolean bvalue = false;
				try {
					bvalue = Boolean.valueOf(qualified).booleanValue();
				} catch (Exception e) {
					//set to false
				} 
				registerUser.setQualified(bvalue);
			}
			if (incomingRequest.containsKey("RegisterUser_registered"))
			{
				String registered = (String) incomingRequest.get("RegisterUser_registered");
				boolean bvalue = false;
				try {
					bvalue = Boolean.valueOf(registered).booleanValue();
				} catch (Exception e) {
					//set to false
				} 
				registerUser.setRegistered(bvalue);
			}
			if (incomingRequest.containsKey("RegisterUser_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("RegisterUser_vendorId");
				registerUser.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("RegisterUser_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("RegisterUser_vendorName");
				registerUser.setVendorName(vendorName);
			}
			
			result = registerUser;
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