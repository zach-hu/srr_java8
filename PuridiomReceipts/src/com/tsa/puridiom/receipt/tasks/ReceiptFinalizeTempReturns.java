package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.receipt.exception.ReceiptRejectedItemException;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.math.BigDecimal;
import java.util.*;

public class ReceiptFinalizeTempReturns extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			ReceiptHeader originalReceiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");

			Map updateParameters = this.getDefaultUpdateParameters(incomingRequest);
			
			updateParameters.put("originalReceiptHeader", incomingRequest.get("receiptHeader"));
			updateParameters.put("receiptMethod", incomingRequest.get("receiptMethod")) ;
			
		    PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			processLoader.setApplicationName(this.getApplicationName());
			PuridiomProcess process = processLoader.loadProcess("receipt-finalize-temp-returns.xml");
	                    
			process.executeProcess(updateParameters);
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}