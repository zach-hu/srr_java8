package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class ReceiptLineSetInventoryStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
		
		 try
	        {
	            if (receiptHeader != null) {

	            	List receiptLineList = (List) incomingRequest.get("receiptLineList") ;

	            	for (int i=0; i < receiptLineList.size(); i++)
	            	{
	            		ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
	            		if(receiptHeader.getReceiptStatus().equalsIgnoreCase(DocumentStatus.RCV_RECEIVED) || receiptHeader.getReceiptStatus().equalsIgnoreCase(DocumentStatus.RCV_PARTIALLYRECEIVED))
	            		{
	            			receiptLine.setInventoryStatus(DocumentStatus.RCV_PARTIALLYRECEIVED) ;
	            		}
	            	}
	            }
	            this.setStatus(Status.SUCCEEDED);
	        }
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}