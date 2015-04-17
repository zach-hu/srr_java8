package com.tsa.puridiom.rfq.notifications.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SetMessageTextToRfqLineCancel extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	String messageText = (String)incomingRequest.get("SendQueue_messagetext");
        	List rfqLineList = (List) incomingRequest.get("rfqLineList");

	        for (int i=0; i<rfqLineList.size(); i++) {
        		RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
        		if (rfqLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) {
            		messageText += "\n Solicitation "+ rfqLine.getRfqNumber() +" Item " + rfqLine.getRfqLine() + ": " + rfqLine.getItemNumber() + ", Cancelled";
            	}
        	}

        	result = messageText;
            /*if(requisitionHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Order could not be forwarded for Approval!");
            }
            Log.debug(this, "Writting send_queue record for requisition: " + requisitionHeader.getRequisitionNumber() + ", status " + newStatus);*/
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Requisition Message Text Cancell Email couldn't be processed.", e);
        }
        return result;
    }
}