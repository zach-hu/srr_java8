package com.tsa.puridiom.requisition.notifications.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SetMessageTextToRequisitionCancel extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        	List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
        	String messageText = new String();

        	if (requisitionHeader.getStatus()==DocumentStatus.CANCELLED) {
        		messageText += "\n Requisition: " + requisitionHeader.getRequisitionNumber() + ", Cancelled";
	        	for (int i=0; i<requisitionLineList.size(); i++) {
	        		RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
	        		if (requisitionLine.getStatus()==DocumentStatus.CANCELLED) {
	            		messageText += "\n Item " + requisitionLine.getLineNumber() + ": " + requisitionLine.getItemNumber() + ", Cancelled";
	            	}
	        	}
	        	messageText += "\n";
        	}

        	result = messageText;
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Requisition Message Text Cancell Email couldn't be processed.", e);
        }
        return result;
    }
}