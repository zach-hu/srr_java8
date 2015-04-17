package com.tsa.puridiom.receipt.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptFormatNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	receiptNumber = (String) incomingRequest.get("ReceiptHeader_receiptNumber");
			incomingRequest.put("AutoGen_documentType", "REC") ;
			incomingRequest.put("AutoGen_genYear", "") ;
			incomingRequest.put("documentNumber", receiptNumber);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
