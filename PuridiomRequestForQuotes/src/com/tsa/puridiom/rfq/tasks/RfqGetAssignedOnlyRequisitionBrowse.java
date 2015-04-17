package com.tsa.puridiom.rfq.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqGetAssignedOnlyRequisitionBrowse extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		result  = "rfq-req-assigned-only";
	
		this.setStatus(Status.SUCCEEDED);
		
		return result;
	}

}