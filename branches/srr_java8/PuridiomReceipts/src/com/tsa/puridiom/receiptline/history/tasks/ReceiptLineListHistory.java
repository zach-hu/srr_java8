package com.tsa.puridiom.receiptline.history.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptLineListHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map) object;
            List receiptLineList = (List) incomingRequest.get("receiptLineList");
            if (receiptLineList != null)
            {
	            for (Iterator iter = receiptLineList.iterator(); iter.hasNext();)
	            {
	                ReceiptLine receiptLine = (ReceiptLine) iter.next();
	                ReceiptLineHistory historyLine = new ReceiptLineHistory();

	                incomingRequest.put("newHistoryReceiptLine", receiptLine);
	                incomingRequest.put("receiptHeader", incomingRequest.get("receiptHeader"));
	                incomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
	                incomingRequest.put("rejectedBy", incomingRequest.get("rejectedBy"));

	                historyLine.executeTask(incomingRequest);
	                this.setStatus(historyLine.getStatus());
	                if(this.getStatus() != Status.SUCCEEDED)
	                {
	                    throw new TsaException(this.getName() + "Error ocurred writing history for line: " + receiptLine.getIcRecLine() + ", item: " + receiptLine.getReceiptNumber());
	                }
	            }
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}