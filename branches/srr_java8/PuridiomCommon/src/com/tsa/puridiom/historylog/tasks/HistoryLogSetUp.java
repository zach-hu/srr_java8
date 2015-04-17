package com.tsa.puridiom.historylog.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class HistoryLogSetUp extends Task
{
    public void setUp(Map incomingRequest)
	{
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
        String userTimeZone = (String) incomingRequest.get("userTimeZone");

		incomingRequest.put("HistoryLog_icHistory",	ukg.getUniqueKey().toString());
		incomingRequest.put("HistoryLog_logDate", Dates.today("", userTimeZone));
		incomingRequest.put("HistoryLog_logTime",  Dates.getTimeString(Dates.today("MM/dd/yyyy HH:mm:ss", userTimeZone)));
		incomingRequest.put("HistoryLog_userid", incomingRequest.get("userId"));
        incomingRequest.put("HistoryLog_timeZone", userTimeZone);
	}

	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			this.setUp(incomingRequest);
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