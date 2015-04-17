package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RegisterUserSetValuesFromVendorRegister extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RegisterUser user = (RegisterUser) incomingRequest.get("registerUser");
			VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

			if (user == null)
			{
				String	organizationId = (String) incomingRequest.get("organizationId");

				user = new RegisterUser();
				user.setOrganizationId(organizationId);
			}
			if (vendorRegister != null)
			{
				user.setVendorId(vendorRegister.getComp_id().getVendorId());
				user.setVendorName(vendorRegister.getVendorName());
				user.setContactCode("");
				user.setContactType(vendorRegister.getContactType());
				user.setAddressCode("");
				user.setEmailAddress(vendorRegister.getComp_id().getContactEmailAddr());
				user.setFirstName(vendorRegister.getContactFirstName());
				user.setLastName(vendorRegister.getContactLastName());
				user.setMiddleInitial(vendorRegister.getContactMidInit());
				user.setQualified(false);
				user.setContactPassword(vendorRegister.getContactPassword());
				//user.setPassChanged(vendorRegister.getPassChanged());
				user.setStatus("02");
				user.setTermsAccepted(vendorRegister.getTermsAccepted());
                String tempPassword = (String) incomingRequest.get("SupplierPortalUser_tempPassword");
                if (tempPassword != null && tempPassword.equals("true")) {
                    user.setTempPassword(true);
                }
			}

			result = user;
			this.status = Status.SUCCEEDED;
			System.out.println("USer is " + user);
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
