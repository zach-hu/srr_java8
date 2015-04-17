package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ReceiptTransferRetrieveSetup extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			String icReqHeader = null;

			if (incomingRequest.containsKey("ReceiptHeader_icReqHeader")) {
				icReqHeader = (String) incomingRequest.get("ReceiptHeader_icReqHeader");
			} else if (incomingRequest.containsKey("requisitionHeader")) {
				RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
				if (requisitionHeader != null) {
					icReqHeader = requisitionHeader.getIcReqHeader().toString();
				}
			} else if (incomingRequest.containsKey("RequisitionHeader_icReqHeader")) {
				icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			}

			if (icReqHeader == null) {
				throw new Exception ("RequisitionHeader icReqHeader was not found.");
			}

			incomingRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
			incomingRequest.put("RequisitionLine_icReqHeader", icReqHeader);

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}