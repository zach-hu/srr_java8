package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineUpdateEntityList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	receiptLineList = (List) incomingRequest.get("receiptLineList");
			ReceiptLineUpdateById receiptLineUpdateTask = new ReceiptLineUpdateById();
				
			for (int i=0; i < receiptLineList.size(); i++) {
				ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("receiptLine", receiptLine);
				
				receiptLineUpdateTask.executeTask(updateParameters);
				
				if (receiptLineUpdateTask.getStatus() < Status.SUCCEEDED) {
					throw new Exception("ReceiptLineUpdateById failed.");
				}
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