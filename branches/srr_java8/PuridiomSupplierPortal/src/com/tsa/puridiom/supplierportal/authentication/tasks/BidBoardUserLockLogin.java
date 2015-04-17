/*
 * Created on April 18, 2011
 */
package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.common.utility.*;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.*;

/**
 * @author matthewd
 */
public class BidBoardUserLockLogin extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		UserProfile userProfile = null;

		try
		{
			BidBoardUser user = (BidBoardUser) incomingRequest.get("bidboardUser");

			if (user != null && !HiltonUtility.isEmpty(user.getVendorId()))
			{
				user.setLockLogin("Y");
			}

			incomingRequest.put("Contact_vendorId", user.getVendorId());
			incomingRequest.put("Contact_contactCode", user.getContactCode());
			incomingRequest.put("Contact_contactType", user.getContactType());
			incomingRequest.put("Contact_lockLogin", "Y");

			incomingRequest.put("VendorRegister_vendorId", user.getVendorId());
			incomingRequest.put("VendorRegister_lockLogin", "Y");

			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return userProfile;
	}
}
