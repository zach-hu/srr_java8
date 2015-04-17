package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ReceiptLineCreateReturnSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    /* Setup to create a Return for rejected items immediately after receiving */
		    
			String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
			String icPoLine = (String) incomingRequest.get("PoLine_icLineHeader");
			ReceiptHeader originalReceiptHeader = (ReceiptHeader) incomingRequest.get("originalReceiptHeader");
			
			if (HiltonUtility.isEmpty(icPoHeader)) {
				incomingRequest.put("PoHeader_icPoHeader", incomingRequest.get("ReceiptHeader_icPoHeader"));
			}
			if (HiltonUtility.isEmpty(icPoLine)) {
				incomingRequest.put("PoLine_icLineHeader", incomingRequest.get("ReceiptLine_icPoLine"));
			}
			
		    if (originalReceiptHeader != null) {
		    	if (!incomingRequest.containsKey("ReceiptHeader_vendorId")) {
				    incomingRequest.put("ReceiptHeader_vendorId", originalReceiptHeader.getVendorId());
		    	}
		    	if (!incomingRequest.containsKey("ReceiptHeader_vendorName")) {
				    incomingRequest.put("ReceiptHeader_vendorName", originalReceiptHeader.getVendorName());
		    	}
		    	if (!incomingRequest.containsKey("ReceiptHeader_refNumber")) {
				    incomingRequest.put("ReceiptHeader_refNumber", originalReceiptHeader.getRefNumber());
		    	}
		    	if (!incomingRequest.containsKey("ReceiptHeader_refRelease")) {
				    incomingRequest.put("ReceiptHeader_refRelease", originalReceiptHeader.getRefRelease());
		    	}
		    	if (!incomingRequest.containsKey("ReceiptHeader_refType")) {
				    incomingRequest.put("ReceiptHeader_refType", originalReceiptHeader.getRefType());
		    	}
		    	if (!incomingRequest.containsKey("ReceiptHeader_icHeaderHistory")) {
				    incomingRequest.put("ReceiptHeader_icHeaderHistory", String.valueOf(originalReceiptHeader.getIcHeaderHistory()));
		    	}
		    	incomingRequest.put("ReceiptHeader_tempIc", String.valueOf(originalReceiptHeader.getIcRecHeader()));
		    }

		    incomingRequest.put("ReceiptHeader_receiptType", "R");
		    incomingRequest.put("receiptMethod", "Return");
            incomingRequest.put("createAction", "SAVE");
//            incomingRequest.put("receiptAction", "FORWARD");

            this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
