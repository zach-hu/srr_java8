package com.tsa.puridiom.inscategorylevel.tasks;

import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class InsCategoryLevelSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			String userId = (String) incomingRequest.get("userId");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");
			if (Utility.isEmpty(userDateFormat)) {
				userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
			}
			String today = Dates.today(userDateFormat, userTimeZone);

			incomingRequest.put("InsCategoryLevel_icIcl", ukg.getUniqueKey().toString());
			if (!incomingRequest.containsKey("InsCategoryLevel_iclLevel")) {
				incomingRequest.put("InsCategoryLevel_iclLevel", "0");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclDescription")) {
				incomingRequest.put("InsCategoryLevel_iclDescription", "");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclRequired1")) {
				incomingRequest.put("InsCategoryLevel_iclRequired1", "N");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclMinimum1")) {
				incomingRequest.put("InsCategoryLevel_iclMinimum1", "0");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclRequired2")) {
				incomingRequest.put("InsCategoryLevel_iclRequired2", "N");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclMinimum2")) {
				incomingRequest.put("InsCategoryLevel_iclMinimum2", "0");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclRequired3")) {
				incomingRequest.put("InsCategoryLevel_iclRequired3", "N");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclMinimum3")) {
				incomingRequest.put("InsCategoryLevel_iclMinimum3", "0");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclRequired4")) {
				incomingRequest.put("InsCategoryLevel_iclRequired4", "N");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclMinimum4")) {
				incomingRequest.put("InsCategoryLevel_iclMinimum4", "0");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclRequired5")) {
				incomingRequest.put("InsCategoryLevel_iclRequired5", "N");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclMinimum5")) {
				incomingRequest.put("InsCategoryLevel_iclMinimum5", "0");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclRequired6")) {
				incomingRequest.put("InsCategoryLevel_iclRequired6", "N");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_iclMinimum6")) {
				incomingRequest.put("InsCategoryLevel_iclMinimum6", "0");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_status")) {
				incomingRequest.put("InsCategoryLevel_status", "02");
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_owner")) {
				incomingRequest.put("InsCategoryLevel_owner", userId);
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_dateEntered")) {
				incomingRequest.put("InsCategoryLevel_dateEntered", today);
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_dateExpires")) {
				incomingRequest.put("InsCategoryLevel_dateExpires", today);
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_lastChgBy")) {
				incomingRequest.put("InsCategoryLevel_lastChgBy", userId);
			}
			if (!incomingRequest.containsKey("InsCategoryLevel_lastChgDate")) {
				incomingRequest.put("InsCategoryLevel_lastChgDate", today);
			}
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
