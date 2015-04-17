package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.supplierportal.RegisterUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorRegisterSetValuesFromRegister extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorRegisterPK comp_id = new VendorRegisterPK();
			VendorRegister vendorRegister = new VendorRegister();
			RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");

			if (vendorRegister == null)
			{
				vendorRegister = new VendorRegister();
			}
			if (registerUser == null)
			{
				registerUser = new RegisterUser();
			}

			comp_id.setContactEmailAddr(registerUser.getEmailAddress());
			comp_id.setVendorId(registerUser.getVendorId());
			
			vendorRegister.setComp_id(comp_id);
			vendorRegister.setContactFaxNumber(registerUser.getFaxNumber());
			vendorRegister.setContactFirstName(registerUser.getFirstName());
			vendorRegister.setContactLastName(registerUser.getLastName());
			vendorRegister.setContactMidInit(registerUser.getMiddleInitial());
			vendorRegister.setContactPassword(registerUser.getContactPassword());
			vendorRegister.setContactType("MAIN");
			vendorRegister.setVendorEin(registerUser.getVendorEin());
			vendorRegister.setVendorFaxNumber(registerUser.getFaxNumber());
			vendorRegister.setVendorName(registerUser.getVendorName());

			vendorRegister.setComp_id(comp_id);

			result = vendorRegister;
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