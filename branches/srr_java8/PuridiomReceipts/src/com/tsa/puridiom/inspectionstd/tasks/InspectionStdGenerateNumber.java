package com.tsa.puridiom.inspectionstd.tasks;

import com.tsagate.foundation.processengine.*;

import java.util.*;

/**
 * @author Kelli
 */
public class InspectionStdGenerateNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			incomingRequest.put("AutoGen_documentType", "CRT") ;
			incomingRequest.put("AutoGen_genYear", "XXXX") ;

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("inspectionstd-generate-form-number.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() < Status.SUCCEEDED)
				throw new Exception("Auto Number process for Standard Inspection Code failed.");

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
