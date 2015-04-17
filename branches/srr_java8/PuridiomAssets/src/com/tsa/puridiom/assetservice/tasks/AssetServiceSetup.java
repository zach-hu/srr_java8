package com.tsa.puridiom.assetservice.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class AssetServiceSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

            String  today = Dates.today(userDateFormat, userTimeZone) ;

			incomingRequest.put("AssetService_tagNumber", "");
			incomingRequest.put("AssetService_sequenceNo", "0");
			incomingRequest.put("AssetService_serviceCallDate", today);
			incomingRequest.put("AssetService_callInitiatedBy", "");
			incomingRequest.put("AssetService_dateInitiated", today);
			incomingRequest.put("AssetService_responseDate", today);
			incomingRequest.put("AssetService_completionDate", today);
			incomingRequest.put("AssetService_serviceAction", "");
			incomingRequest.put("AssetService_serviceCost", "0");
			incomingRequest.put("AssetService_lastChgBy", "");
			incomingRequest.put("AssetService_dateChanged", today);

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