package com.tsa.puridiom.userlog.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.UserLog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class UserLogSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
		String dateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy");

		try
		{
			UserLog userLog = (UserLog) incomingRequest.get("userLog");
			if (userLog == null)
			{
				userLog = new UserLog();
			}

			if (incomingRequest.containsKey("UserLog_icUsrLog"))
			{
				String icUsrLogString = (String) incomingRequest.get("UserLog_icUsrLog");
				if (Utility.isEmpty(icUsrLogString))
				{
					icUsrLogString = "0";
				}
				BigDecimal icUsrLog = new BigDecimal(icUsrLogString);
				userLog.setIcUsrLog(icUsrLog);
			}
			if (incomingRequest.containsKey("UserLog_userId"))
			{
				String userId = (String) incomingRequest.get("UserLog_userId");
				userLog.setUserId(userId);
			}
			if (incomingRequest.containsKey("UserLog_mailId"))
			{
				String mailId = (String) incomingRequest.get("UserLog_mailId");
				userLog.setMailId(Utility.ckNull(mailId).toLowerCase());
			}
			if (incomingRequest.containsKey("UserLog_passCode"))
			{
				String passCode = (String) incomingRequest.get("UserLog_passCode");
				userLog.setPassCode(passCode);
			}
			if (incomingRequest.containsKey("UserLog_dateLog"))
			{
				String dateLogString = (String) incomingRequest.get("UserLog_dateLog");
				Date dateLogDate = Dates.getSqlDate(dateLogString, dateFormat);
				userLog.setDateLog(dateLogDate);
			}
			if (incomingRequest.containsKey("UserLog_timeLog"))
			{
				String timeLog = (String) incomingRequest.get("UserLog_timeLog");
				userLog.setTimeLog(timeLog);
			}
			if (incomingRequest.containsKey("UserLog_ipLog"))
			{
				String ipLog = (String) incomingRequest.get("UserLog_ipLog");
				userLog.setIpLog(ipLog);
			}
			if (incomingRequest.containsKey("UserLog_status"))
			{
				String status = (String) incomingRequest.get("UserLog_status");
				userLog.setStatus(status);
			}
			result = userLog;
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
