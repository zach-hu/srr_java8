package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqCreateAmendmentSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			//	get Rfq number on original Rfq
		    //	set originalRfqHeader in incomingReqeust from rfqHeader
		    //  set originalRfqHeader in incomingReqeust from rfqHeader
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			List rfqLineList = (List) incomingRequest.get("rfqLineList");
			String	rfqNumber = rfqHeader.getRfqNumber();
			
			incomingRequest.put("newRfqHeader_rfqNumber", rfqNumber) ;
			incomingRequest.put("newRfqLine_rfqNumber", rfqNumber) ;
			incomingRequest.put("newRfqLine_status", DocumentStatus.RFQ_OPENAMENDMENT) ;
			incomingRequest.put("amendmentSaveas", "Y");
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
