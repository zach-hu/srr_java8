package com.tsa.puridiom.inspection.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Jael
 */
@SuppressWarnings(value = { "unchecked" })
public class InspectionGetNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	fiscalYear = (String) incomingRequest.get("InspectionHeader_fiscalYear");
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "2011";
			}
			incomingRequest.put("AutoGen_documentType", "GI") ;
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
