/**
 * Created on Jan 6, 2004
 * @author renzo
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.rfqbidhistory.tasks;

import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class RfqBidHistoryCreateSetup extends Task
{
	/**
	 * author EDSAC
	 * it gets the date and userId that made changes
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }
            //obtain the date with the hours and minutes
	        String todayhours = Dates.today("MM-dd-yyyy hh:mm a", userTimeZone);
	        String	today = Dates.today(userDateFormat, userTimeZone);
			incomingRequest.put("RfqBidHistory_lastChgDate", today);
			incomingRequest.put("RfqBidHistory_lastMdfDate", todayhours);
			incomingRequest.put("RfqBidHistory_userId", userId);
            incomingRequest.put("RfqBidHistory_timeZone", userTimeZone);
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