package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqHeaderSetToOpenAmendment extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		try {
			incomingRequest.put("RfqHeader_status", DocumentStatus.RFQ_OPENAMENDMENT);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}

}