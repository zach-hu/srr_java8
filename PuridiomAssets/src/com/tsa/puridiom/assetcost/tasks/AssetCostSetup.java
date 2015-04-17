package com.tsa.puridiom.assetcost.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class AssetCostSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

			incomingRequest.put("AssetCost_tagNumber", "");
			incomingRequest.put("AssetCost_sequenceNo", "0");
			incomingRequest.put("AssetCost_amount", "0");
			incomingRequest.put("AssetCost_extendLifeFlag", "");
			incomingRequest.put("AssetCost_dateEntered", Dates.today(userDateFormat, userTimeZone));
			incomingRequest.put("AssetCost_dateReceived", Dates.today(userDateFormat, userTimeZone));
			incomingRequest.put("AssetCost_poNumber", "");
			incomingRequest.put("AssetCost_poVendor", "");
			incomingRequest.put("AssetCost_description", "");
			incomingRequest.put("AssetCost_dateChanged", "");
			incomingRequest.put("AssetCost_lastChgBy", "");

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