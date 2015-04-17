package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Jose
 */
public class PoSetPoNumberPrefix extends Task
{


	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		String	organizationId = (String) incomingRequest.get("organizationId") ;


		try
		{
			String poType = (String) incomingRequest.get("PoHeader_poType");
			String PO_PREFIX = PropertiesManager.getInstance(organizationId).getProperty("PO TYPES", poType.toUpperCase() + "_PREFIX", "");
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");

			if (!HiltonUtility.isEmpty(poNumber))
			{
				// Assign the prefix to the Number
				poNumber = PO_PREFIX + poNumber;
			} else
			{
				Log.error(this, "PoNumber is empty ... Prefix could not be set");
				throw new Exception("PO Number is empty!");
			}

			// Set New prefixed PO Number
			incomingRequest.put("PoHeader_poNumber", poNumber);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return result;
	}

}
