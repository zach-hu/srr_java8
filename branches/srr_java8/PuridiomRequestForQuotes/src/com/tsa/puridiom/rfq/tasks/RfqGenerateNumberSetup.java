package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqGenerateNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			RfqHeader	rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			String	fiscalYear = (String) incomingRequest.get("RfqHeader_fiscalYear");
			
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = rfqHeader.getFiscalYear();
			}
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "RFQ") ;
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
