package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class VendorContactSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

		    // Contact_contactType should already be set
		    // Contact_contactCode will be set in the contact-add process

			incomingRequest.put("Contact_lastName", vendorRegister.getContactLastName());
			incomingRequest.put("Contact_firstName", vendorRegister.getContactFirstName());
			incomingRequest.put("Contact_middleInit", vendorRegister.getContactMidInit());
			incomingRequest.put("Contact_salutation", vendorRegister.getContactSalutation());
			incomingRequest.put("Contact_contactTitle", vendorRegister.getContactTitle());
			incomingRequest.put("Contact_phoneNumber", vendorRegister.getContactPhoneNo());
			incomingRequest.put("Contact_phoneFormat", "");
			incomingRequest.put("Contact_faxNumber", vendorRegister.getContactFaxNumber());
			incomingRequest.put("Contact_faxFormat", "");
			incomingRequest.put("Contact_dateEntered", Dates.today("", ""));
			incomingRequest.put("Contact_dateExpires", Dates.today("", ""));
			incomingRequest.put("Contact_status", "02");
			incomingRequest.put("Contact_owner", "PURIDIOM-BB");
			incomingRequest.put("Contact_emailAddr", vendorRegister.getComp_id().getContactEmailAddr());
			incomingRequest.put("Contact_info1", vendorRegister.getContactInfo1());
			incomingRequest.put("Contact_info2", vendorRegister.getContactInfo2());
			incomingRequest.put("Contact_info3", vendorRegister.getContactInfo3());
			incomingRequest.put("Contact_info4", vendorRegister.getContactInfo4());
			incomingRequest.put("Contact_contactPassword", vendorRegister.getContactPassword());
			incomingRequest.put("Contact_passChanged", String.valueOf(Dates.today("", "")));

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