package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class XrefComboCreateSetup extends Task
{

	public Object executeTask (Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
		        String userDateFormat = (String) incomingRequest.get("userDateFormat");

			if (Utility.isEmpty(userDateFormat)) {
				userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
			}

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			String	today = Dates.today(userDateFormat, userTimeZone) ;

			incomingRequest.put("XrefCombo_icXref", ukg.getUniqueKey().toString());
			incomingRequest.put("XrefCombo_owner", userId);
			incomingRequest.put("XrefCombo_dateEntered", today);
			incomingRequest.put("XrefCombo_dateExpires", today);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}