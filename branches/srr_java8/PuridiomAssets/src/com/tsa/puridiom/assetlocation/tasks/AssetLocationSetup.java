package com.tsa.puridiom.assetlocation.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class AssetLocationSetup extends Task
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

			incomingRequest.put("AssetLocation_tagNumber", "");
			incomingRequest.put("AssetLocation_sequenceNo", "0");
			incomingRequest.put("AssetLocation_locationType", "");
			incomingRequest.put("AssetLocation_division", "");
			incomingRequest.put("AssetLocation_department", "");
			incomingRequest.put("AssetLocation_facility", "");
			incomingRequest.put("AssetLocation_building", "");
			incomingRequest.put("AssetLocation_room", "");
			incomingRequest.put("AssetLocation_userId", "");
			incomingRequest.put("AssetLocation_dateAssigned", today);
			incomingRequest.put("AssetLocation_dateChanged", today);
			incomingRequest.put("AssetLocation_lastChgBy", "");
			incomingRequest.put("AssetLocation_telephone", "");

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