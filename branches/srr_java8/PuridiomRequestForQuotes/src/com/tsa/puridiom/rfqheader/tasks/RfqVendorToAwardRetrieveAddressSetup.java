/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqVendorToAwardRetrieveAddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String vendorToAward = (String) incomingRequest.get("awardedVendor") ;
			incomingRequest.put("Address_addressType", vendorToAward);
			incomingRequest.put("Address_addressCode", "DEFAULT") ;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return result ;
	}
}
