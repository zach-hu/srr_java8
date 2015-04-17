package com.tsa.puridiom.userlog.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class UserLogSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		String organizationId = (String) incomingRequest.get("organizationId");
		String dateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy");

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			String userId = (String)incomingRequest.get("userId");
			UserProfile userProfile = (UserProfile)incomingRequest.get("userProfile");
			if (userProfile != null)
			{
				userId = userProfile.getUserId();
			}
			if (HiltonUtility.isEmpty(userId))
			{
				userId = "UNKNOWN";
				incomingRequest.put("UserLog_status", DocumentStatus.USERLOG_INVALID_USERID);
			}
			incomingRequest.put("UserLog_icUsrLog", ukg.getUniqueKey().toString());
			incomingRequest.put("UserLog_userId", userId);
			incomingRequest.put("UserLog_mailId", incomingRequest.get("mailId"));
			incomingRequest.put("UserLog_passCode", incomingRequest.get("password"));
			incomingRequest.put("UserLog_dateLog", Dates.today(dateFormat));
			incomingRequest.put("UserLog_timeLog", Dates.getTimeString(Dates.today(dateFormat + " HH:mm:ss")));
			incomingRequest.put("UserLog_ipLog", incomingRequest.get("ipAddress"));

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
