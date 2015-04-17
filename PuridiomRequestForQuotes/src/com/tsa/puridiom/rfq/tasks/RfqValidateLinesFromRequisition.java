package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqValidateLinesFromRequisition extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List reqLineList = (List) incomingRequest.get("requisitionLineList");
			
			for (int i = reqLineList.size() - 1; i >= 0 ; i--) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				String	status = reqLine.getStatus();
				
				if (!(status.equals(DocumentStatus.REQ_APPROVING) || status.equals(DocumentStatus.REQ_APPROVED) || 
					status.equals(DocumentStatus.REQ_FORWARDED))) {
						reqLineList.remove(i);
				}
			}
			
			result = reqLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return result ;
	}

}