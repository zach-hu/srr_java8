package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class RegisterUserSetValuesFromVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RegisterUser user = (RegisterUser) incomingRequest.get("registerUser");
			Vendor vendor = (Vendor) incomingRequest.get("vendor");
			
			if (user == null)
			{
				String	organizationId = (String) incomingRequest.get("organizationId");
				
				user = new RegisterUser();
				user.setOrganizationId(organizationId);
			}
			if (vendor != null)
			{
			    String	vendorName = vendor.getVendorName();
			    String	termsAccepted = vendor.getTermsAccepted();
			    
				user.setVendorName(vendorName);
				user.setTermsAccepted(termsAccepted);
			}

			result = user;
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