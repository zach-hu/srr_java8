package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqForwardSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			String	icRfqHeaderString = String.valueOf(rfqHeader.getIcRfqHeader());
			String	icReqHeaderString = String.valueOf(rfqHeader.getIcReqHeader());

			incomingRequest.put("RfqLine_icRfqHeader", icRfqHeaderString);
			incomingRequest.put("RfqBid_icRfqHeader", icRfqHeaderString);
			incomingRequest.put("RfqVendor_icRfqHeader", icRfqHeaderString);
			incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeaderString);
			incomingRequest.put("RequisitionLine_icReqHeader", icReqHeaderString);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}