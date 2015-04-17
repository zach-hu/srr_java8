package com.tsa.puridiom.receipt.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptGetNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	icRecHeaderString = (String) incomingRequest.get("ReceiptHeader_icRecHeader");

			if (incomingRequest.containsKey("ReceiptLine_icRecHeader")) {
				Object obj = incomingRequest.get("ReceiptLine_icRecHeader");
				if (obj instanceof String[]) {
					String	icRecHeaderArray[] = (String[]) obj;
					for (int i=0; i < icRecHeaderArray.length; i++) {
						icRecHeaderArray[i] = icRecHeaderString;
					}
					incomingRequest.put("ReceiptLine_icRecHeader", icRecHeaderArray);
				} else if (obj instanceof String) {
					incomingRequest.put("ReceiptLine_icRecHeader", icRecHeaderString);
				}
			}
			//incomingRequest.put("ReceiptLine_icRecHeader", icRecHeaderString);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
