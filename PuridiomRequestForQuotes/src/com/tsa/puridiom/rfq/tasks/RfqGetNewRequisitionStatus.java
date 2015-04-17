package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.common.documents.DocumentStatus;
import java.util.Map;

public class RfqGetNewRequisitionStatus extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String	reqType = reqHeader.getRequisitionType();
			String	rfqStatus = (String) incomingRequest.get("RfqHeader_status");
			String	newStatus = DocumentStatus.RFQ_INPROGRESS;
			
/**	commented out on 08/12/04 because we changed the RFQ_INPROGRESS status to say
 **	"Pricing" regardless of the requisition type
 
			if (rfqStatus.equals(DocumentStatus.RFQ_INPROGRESS)) {
				if (reqType.equals("N")) {
					newStatus = DocumentStatus.REQ_PRICING;
				}
				else {
					newStatus = DocumentStatus.RFQ_INPROGRESS;
				}
			}
**/
			
			result  = newStatus;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return result;
	}

}