package com.tsa.puridiom.inspectionline.tasks;

import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class InspectionLineUpdateSetup extends Task{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        String userId = (String)incomingRequest.get("userId") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        this.setStatus(Status.SUCCEEDED);

        if(Utility.isEmpty((String)incomingRequest.get("InspectionLine_lockFlag"))) {
        	incomingRequest.put("InspectionLine_lockFlag", "N");
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionLine_dateEntered"))) {
        	incomingRequest.put("InspectionLine_dateEntered",Dates.today("", userTimeZone));
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionLine_lastChange"))) {
        	incomingRequest.put("InspectionLine_lastChange",Dates.today("", userTimeZone));
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionLine_lastChangeBy"))) {
        	incomingRequest.put("InspectionLine_lastChangeBy", userId);
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionLine_owner"))) {
        	incomingRequest.put("InspectionLine_owner", userId);
        }
        if (Utility.isEmpty((String)incomingRequest.get("InspectionLine_status"))) {
        	incomingRequest.put("InspectionLine_status", DocumentStatus.INSP_PENDING);
        }

        return null;

	}
}
