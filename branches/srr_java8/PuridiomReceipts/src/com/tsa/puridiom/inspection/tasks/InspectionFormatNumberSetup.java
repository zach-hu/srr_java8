package com.tsa.puridiom.inspection.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Jael
 */
public class InspectionFormatNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	inspectionNumber = (String) incomingRequest.get("InspectionHeader_inspectionNumber");
			String	fiscalYear = (String) incomingRequest.get("InspectionHeader_fiscalYear");
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "2011";
			}
			incomingRequest.put("AutoGen_documentType", "GI") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("documentNumber", inspectionNumber);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
