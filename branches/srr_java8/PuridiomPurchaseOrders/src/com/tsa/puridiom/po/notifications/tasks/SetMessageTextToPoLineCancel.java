package com.tsa.puridiom.po.notifications.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SetMessageTextToPoLineCancel extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	String messageText = (String)incomingRequest.get("SendQueue_messagetext");
        	List poLineList = (List) incomingRequest.get("poLineList");

	        for (int i=0; i<poLineList.size(); i++) {
        		PoLine poLine = (PoLine) poLineList.get(i);
        		if (poLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) {
            		messageText += "\n Order "+ poLine.getPoNumber() +" Item " + poLine.getLineNumber() + ": " + poLine.getItemNumber() + ", Cancelled";
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
            throw new TsaException("Purchase Order Line Message Text Cancell Email couldn't be processed.", e);
        }
        return result;
    }
}