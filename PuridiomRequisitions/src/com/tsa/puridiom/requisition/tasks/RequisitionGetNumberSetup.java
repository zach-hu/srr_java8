package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionGetNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			String	fiscalYear = (String) incomingRequest.get("RequisitionHeader_fiscalYear");
			String reqType = HiltonUtility.ckNull((String) incomingRequest.get("RequisitionHeader_requisitionType"));
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "REQ") ;
			incomingRequest.put("AutoGen_Type", reqType);
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("RequisitionLine_icReqHeader", icReqHeaderString);
			
			// Status must be In Progress in order to get a new number - set for history
			if (!incomingRequest.containsKey("RequisitionHeader_status")) {
			    incomingRequest.put("RequisitionHeader_status",DocumentStatus.REQ_INPROGRESS);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
