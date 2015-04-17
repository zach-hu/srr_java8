/*
 * Created on March 21, 2007
 * @author KC
 * com.tsa.puridiom.tasks.address.AddressRetrieveFromOrderSetup.java
 */

package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class AddressRetrieveFromOrderSetup extends Task
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
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			if (poHeader != null)
			{
				String reqNumber = poHeader.getRequisitionNumber();
				incomingRequest.put("Address_addressCode", reqNumber);
				incomingRequest.put("Address_addressType", "VENDOR");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
