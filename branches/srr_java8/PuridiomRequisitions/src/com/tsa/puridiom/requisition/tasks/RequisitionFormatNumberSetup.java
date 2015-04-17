package com.tsa.puridiom.requisition.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionFormatNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	requisitionNumber = (String) incomingRequest.get("RequisitionHeader_requisitionNumber");
			String	fiscalYear = (String) incomingRequest.get("RequisitionHeader_fiscalYear");
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "REQ") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("documentNumber", requisitionNumber);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
