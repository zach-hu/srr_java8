package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineListRetrieveAdjustments extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	receiptLineList = (List) incomingRequest.get("receiptLineList");
			ReceiptLineRetrieveAdjustments task = new ReceiptLineRetrieveAdjustments();
				
			for (int i=0; i < receiptLineList.size(); i++) {
				ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
		   updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("ReceiptLine_receiptNumber", receiptLine.getReceiptNumber());
				
				List adjustments = (List) task.executeTask(updateParameters);
				
				if (task.getStatus() < Status.SUCCEEDED) {
					throw new Exception("ReceiptLineRetrieveAdjustments failed.");
				}
				
				receiptLine.setReceiptLineAdjustmentList(adjustments);
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