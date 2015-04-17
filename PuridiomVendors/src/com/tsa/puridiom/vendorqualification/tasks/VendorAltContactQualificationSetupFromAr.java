/*
 * Created on March 25, 2004
 * @author Kelli
 * com.tsa.puridiom.vendorqualification.tasks.VendorAltContactQualificationSetup.java
 */

package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class VendorAltContactQualificationSetupFromAr extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			VendorRegister	vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");
			String	vendorId = (String) incomingRequest.get("Vendor_vendorId");

			incomingRequest.put("Address_addressType", vendorId);
			incomingRequest.put("Contact_vendorId", vendorId);
			incomingRequest.put("Contact_contactType", "ALTERNATE");
			incomingRequest.put("Contact_emailAddr", vendorRegister.getArEmailAddr());
			incomingRequest.put("Contact_lastName", vendorRegister.getArLastName());
			incomingRequest.put("Contact_firstName", vendorRegister.getArFirstName());
			incomingRequest.put("Contact_middleInit", vendorRegister.getArMidInit());
			incomingRequest.put("Contact_salutation", vendorRegister.getArSalutation());
			incomingRequest.put("Contact_contactTitle", vendorRegister.getArTitle());
			incomingRequest.put("Contact_phoneNumber", vendorRegister.getArPhoneNo());
			incomingRequest.put("Contact_phoneFormat", "");
			incomingRequest.put("Contact_faxNumber", vendorRegister.getArFaxNumber());
			incomingRequest.put("Contact_faxFormat", "");
			incomingRequest.put("Contact_dateEntered", Dates.today("", ""));
			incomingRequest.put("Contact_dateExpires", Dates.today("", ""));
			incomingRequest.put("Contact_status", "02");
			incomingRequest.put("Contact_owner", "PURIDIOM-BB");
			incomingRequest.put("Contact_info1", vendorRegister.getArInfo1());
			incomingRequest.put("Contact_info2", vendorRegister.getArInfo2());
			incomingRequest.put("Contact_info3", vendorRegister.getArInfo3());
			incomingRequest.put("Contact_info4", vendorRegister.getArInfo4());
			incomingRequest.put("Contact_contactPassword", "");
			incomingRequest.put("Contact_passChanged", "");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
