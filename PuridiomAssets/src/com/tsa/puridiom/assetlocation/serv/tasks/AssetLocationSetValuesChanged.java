package com.tsa.puridiom.assetlocation.serv.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class AssetLocationSetValuesChanged extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            String tagNumber = (String)incomingRequest.get("AssetLocation_tagNumber");

            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

            String  today = Dates.today(userDateFormat, userTimeZone) ;

			incomingRequest.put("AssetLocation_dateChanged", today);
			incomingRequest.put("AssetLocation_lastChgBy", userId);

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