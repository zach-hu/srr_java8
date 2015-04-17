package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			//	this is the Rfq number that was generated or passed in through the request
			String	rfqNumber = (String) incomingRequest.get("RfqHeader_rfqNumber");
			RfqHeader	rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			
			incomingRequest.put("newRfqHeader_rfqNumber", rfqNumber) ;
			incomingRequest.put("newRfqLine_rfqNumber", rfqNumber) ;
			incomingRequest.put("originalRfqHeader", rfqHeader);
			incomingRequest.put("originalRfqLineList", rfqLineList);
			
			incomingRequest.remove("rfqHeader");
			incomingRequest.remove("rfqLineList");
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
