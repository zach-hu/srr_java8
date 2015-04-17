package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ReceiptOrderRetrieveSetup extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			String icPoHeader = null;
			boolean isRequisition = false;
			if (incomingRequest.containsKey("ReceiptHeader_icPoHeader")) {
				icPoHeader = (String) incomingRequest.get("ReceiptHeader_icPoHeader");
			} else if (incomingRequest.containsKey("receiptHeader")) {
				ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
				if (receiptHeader != null) {
					icPoHeader = receiptHeader.getIcPoHeader().toString();
					if(receiptHeader.getReceiptType().equals("T")){
						isRequisition = true;
					}
				}
			}

			if (icPoHeader == null) {
				throw new Exception ("ReceiptHeader icPoHeader was not found.");
			}
			if(!isRequisition){
				incomingRequest.put("PoHeader_icPoHeader", icPoHeader);
				incomingRequest.put("PoLine_icPoHeader", icPoHeader);
			}else{
				incomingRequest.put("RequisitionHeader_icReqHeader", icPoHeader);
			}


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