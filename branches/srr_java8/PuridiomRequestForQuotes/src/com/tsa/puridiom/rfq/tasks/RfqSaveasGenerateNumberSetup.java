package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqSaveasGenerateNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			RfqHeader	originalRfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			String	fiscalYear = (String) incomingRequest.get("RfqHeader_fiscalYear");
			String rfqType = HiltonUtility.ckNull(originalRfqHeader.getRfqType());
			
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = originalRfqHeader.getFiscalYear();
			}
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "RFQ") ;
			incomingRequest.put("AutoGen_Type", rfqType);
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
