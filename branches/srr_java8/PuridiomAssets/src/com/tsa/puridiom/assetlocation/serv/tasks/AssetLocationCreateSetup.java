/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.assetlocation.serv.tasks;

import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class AssetLocationCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
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

            String	today = Dates.today(userDateFormat, userTimeZone) ;

			incomingRequest.put("AssetLocation_tagNumber", tagNumber);
			incomingRequest.put("AssetLocation_lastChgBy", userId);
			incomingRequest.put("AssetLocation_dateChanged", today);

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
