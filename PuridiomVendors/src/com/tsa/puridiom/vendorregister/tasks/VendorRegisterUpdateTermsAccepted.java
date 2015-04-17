package com.tsa.puridiom.vendorregister.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class VendorRegisterUpdateTermsAccepted extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorRegister vendorRegister = (VendorRegister)incomingRequest.get("vendorRegister");
			if (vendorRegister == null)
			{
				throw new Exception ("VendorRegister was not found.");
			}

			vendorRegister.setTermsAccepted(Dates.today("yyyy-MM-dd", ""));

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(vendorRegister);

			result = vendorRegister;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}