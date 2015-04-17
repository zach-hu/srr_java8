package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Jael
 */
public class PoSetPoNumberSuffix extends Task
{


	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String PO_SUFFIX = "";
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			String poSuffix = poNumber.substring(poNumber.length()-1);
			if(poSuffix.equals("B") || poSuffix.equals("G")){
				poNumber = poNumber.substring(0, poNumber.length()-1);
			}
			
			if (poHeader != null)
			{
				if(poHeader.getUdf3Code().equalsIgnoreCase("RGD")){
					PO_SUFFIX = "G";
				} else if(poHeader.getUdf3Code().equalsIgnoreCase("SNM")){
					PO_SUFFIX = "B";
				}
			}

			if(!HiltonUtility.isEmpty(poNumber)){
				// Assign the suffix to the Number
				if (!poNumber.equalsIgnoreCase("N/A")) {
					poNumber = poNumber + PO_SUFFIX;
				}
			}
			else
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
