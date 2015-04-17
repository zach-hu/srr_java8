package com.tsa.puridiom.po.notifications.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SetMessageTextToPoHeaderCancel extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	String messageText = (String)incomingRequest.get("SendQueue_messagetext");
        	PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

	        if (poHeader.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) {
        		messageText += "\n Order: "+ poHeader.getPoNumber() + ", Cancelled";
        	}

        	result = messageText;
        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Purchase Order Message Text Cancell Email couldn't be processed.", e);
        }
        return result;
    }
}