package com.tsa.puridiom.rfq.notifications.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SetMessageTextToRfqHeaderCancel extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	String messageText = (String)incomingRequest.get("SendQueue_messagetext");
        	RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");

	        if (rfqHeader.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) {
        		messageText += "\n Solicitation: "+ rfqHeader.getRfqNumber() + ", Cancelled";
        	}

        	result = messageText;
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