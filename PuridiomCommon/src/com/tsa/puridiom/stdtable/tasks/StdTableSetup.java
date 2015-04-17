package com.tsa.puridiom.stdtable.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;
import com.tsagate.foundation.utility.*;

public class StdTableSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
            String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String today = Dates.today("", userTimeZone);

            if (!incomingRequest.containsKey("StdTable_dateEntered")) {
                incomingRequest.put("StdTable_dateEntered", today);
            }
            if (!incomingRequest.containsKey("StdTable_dateExpires")) {
                incomingRequest.put("StdTable_dateExpires", today);
            }
            if (!incomingRequest.containsKey("StdTable_status")) {
                incomingRequest.put("StdTable_status", "02");
            }
            if (!incomingRequest.containsKey("StdTable_owner")) {
                incomingRequest.put("StdTable_owner", userId);
            }
            if (!incomingRequest.containsKey("StdTable_dateChanged")) {
                incomingRequest.put("StdTable_dateChanged", today);
            }

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