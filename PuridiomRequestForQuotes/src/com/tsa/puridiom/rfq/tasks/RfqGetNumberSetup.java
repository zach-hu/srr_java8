package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqGetNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			String	fiscalYear = (String) incomingRequest.get("RfqHeader_fiscalYear");
			String	rfqType = HiltonUtility.ckNull((String) incomingRequest.get("RfqHeader_rfqType"));
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "RFQ") ;
			incomingRequest.put("AutoGen_Type", rfqType);
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("RfqLine_icRfqHeader", icRfqHeaderString);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
