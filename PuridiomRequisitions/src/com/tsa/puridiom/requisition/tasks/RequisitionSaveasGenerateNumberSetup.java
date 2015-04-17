package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionSaveasGenerateNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			RequisitionHeader	originalRequisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String	fiscalYear = (String) incomingRequest.get("RequisitionHeader_fiscalYear");
			
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = originalRequisitionHeader.getFiscalYear();
			}
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "1994";
			}
			incomingRequest.put("RequisitionHeader_fiscalYear", fiscalYear);
			incomingRequest.put("AutoGen_documentType", "REQ") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
