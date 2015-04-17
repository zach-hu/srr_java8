package com.tsa.puridiom.inspectionstd.tasks;
import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class InspectionStdUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		String userId = (String)incomingRequest.get("userId") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        this.setStatus(Status.SUCCEEDED);

        if (Utility.isEmpty("InspectionStd_dateEntered"))
        {
        	incomingRequest.put("InspectionStd_dateEntered", Dates.today("", userTimeZone));
        }

        if (Utility.isEmpty("InspectionStd_owner"))
        {
        	incomingRequest.put("InspectionStd_owner", userId);
        }

        if (incomingRequest.get("InspectionStd_lastChange") == null)
        {
        	incomingRequest.put("InspectionStd_lastChange", Dates.today("", userTimeZone));
        }

        if (incomingRequest.get("InspectionStd_lastChangeBy") == null)
        {
        	incomingRequest.put("InspectionStd_lastChangeBy", userId);

        }

        return null;
	}
}
