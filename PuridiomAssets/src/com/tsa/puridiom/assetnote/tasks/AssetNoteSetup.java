package com.tsa.puridiom.assetnote.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class AssetNoteSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

            String  today = Dates.today(userDateFormat, userTimeZone) ;

			incomingRequest.put("AssetNote_tagNumber", "");
			incomingRequest.put("AssetNote_sequenceNo", "0");
			incomingRequest.put("AssetNote_dateEntered", today);
			incomingRequest.put("AssetNote_dateChanged", today);
			incomingRequest.put("AssetNote_userId", "");
			incomingRequest.put("AssetNote_lastChgBy", "");
			incomingRequest.put("AssetNote_stdText", "");

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