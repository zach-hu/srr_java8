package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BidBoardUserSetValuesFromVendorRegister extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BidBoardUser user = (BidBoardUser) incomingRequest.get("bidboardUser");
			VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

			if (user == null)
			{
				String	organizationId = (String) incomingRequest.get("organizationId");

				user = new BidBoardUser();
				user.setOrganization(organizationId);
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
				user.setUserPassword(vendorRegister.getContactPassword());
				user.setPassChanged(vendorRegister.getPassChanged());
				user.setStatus("02");
				user.setLockLogin(vendorRegister.getLockLogin());
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