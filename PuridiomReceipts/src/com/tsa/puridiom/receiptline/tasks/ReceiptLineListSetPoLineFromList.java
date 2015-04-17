package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineListSetPoLineFromList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	receiptLineList = (List) incomingRequest.get("receiptLineList");
			List	poLineList = (List) incomingRequest.get("poLineList");
			List	newPoLineList = new ArrayList();
				
			for (int i=0; i < receiptLineList.size(); i++) {
				ReceiptLine originalReceiptLine = (ReceiptLine) receiptLineList.get(i);
				PoLine poLine = new PoLine();
				poLine.setIcPoLine(originalReceiptLine.getIcPoLine());

				int ip = poLineList.indexOf(poLine);
				
				poLine = (PoLine) poLineList.get(ip);
				
				// get all receipt adjustments for this poLine and add the original receipt
				List	receipts = originalReceiptLine.getReceiptLineAdjustmentList();
				receipts.add(originalReceiptLine);
				
				poLine.setReceiptLineList(receipts);
				newPoLineList.add(poLine);
			}

			result = newPoLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}