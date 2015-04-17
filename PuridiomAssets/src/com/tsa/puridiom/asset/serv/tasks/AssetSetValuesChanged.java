package com.tsa.puridiom.asset.serv.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class AssetSetValuesChanged extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			/*
			 * @author EDSAC
			 * get the userId information and dateChange
			 * */
			Map incomingRequest = (Map) object;
			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            String userDateFormat = (String)incomingRequest.get("userDateFormat");

			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

            String	today = Dates.today(userDateFormat, userTimeZone) ;

			incomingRequest.put("Asset_dateChanged", today);
			incomingRequest.put("Asset_lastChgBy", userId);

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