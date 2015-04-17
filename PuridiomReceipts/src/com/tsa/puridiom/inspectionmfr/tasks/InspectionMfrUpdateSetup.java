package com.tsa.puridiom.inspectionmfr.tasks;

import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class InspectionMfrUpdateSetup extends Task{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        String userId = (String)incomingRequest.get("userId") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        this.setStatus(Status.SUCCEEDED);

        if (Utility.isEmpty((String)incomingRequest.get("InspectionMfr_dateEntered"))) {
        	incomingRequest.put("InspectionMfr_dateEntered",Dates.today("", userTimeZone));
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionMfr_lastChange"))) {
        	incomingRequest.put("InspectionMfr_lastChange",Dates.today("", userTimeZone));
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionMfr_lastChangeBy"))) {
        	incomingRequest.put("InspectionMfr_lastChangeBy", userId);
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionMfr_owner"))) {
        	incomingRequest.put("InspectionMfr_owner", userId);
        }

        return null;

	}
}
