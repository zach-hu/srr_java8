package com.tsa.puridiom.receiptline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptLineListUpdate extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			
			for (int i=0; i < receiptLineList.size(); i++) 
			{
				ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);

				ReceiptLineUpdate update = new ReceiptLineUpdate();
				
				incomingRequest.put("receiptLine", receiptLine);
				
				update.executeTask(incomingRequest);
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}