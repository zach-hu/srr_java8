package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineSetReceiptNumberFromHeader extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	receiptLineList = (List) incomingRequest.get("receiptLineList");
			String	receiptNumber = (String) incomingRequest.get("ReceiptHeader_receiptNumber");
			
			for (int i=0; i < receiptLineList.size(); i++) {
				ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
				receiptLine.setReceiptNumber(receiptNumber);
				receiptLineList.set(i, receiptLine);
			}
			
			result = receiptLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}