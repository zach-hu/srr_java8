/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class RfqHeaderRetrieveBuyerAddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RfqHeader rfh = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfh == null)
			{
				this.setStatus(Status.FAILED);
				throw new Exception("The RfqHeader entity was not found.");
			}

			String buyerShipTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), rfh.getBuyer()).getShipToCode();

			if(!HiltonUtility.isEmpty(buyerShipTo))
			{
				Log.debug(this, "buyerShipTo: " + buyerShipTo);

				incomingRequest.put("Address_addressCode", buyerShipTo);
			}
			incomingRequest.put("Address_addressType", "ADDR");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Vendor Id cound not be found! " + e.getMessage(), e);
		}

		return result ;
	}
}
