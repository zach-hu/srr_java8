package com.tsa.puridiom.po.notifications.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.documents.DocumentStatus;

public class SetMessageErrorPoSave extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;
        String organizationId = (String)incomingRequest.get("organizationId");

        try
        {
            String reqNumber = (String)incomingRequest.get("reqNumber");
            String reqStatus = (String)incomingRequest.get("reqStatus");

        	String messageText = "";

            messageText = "\n Order could not save because Requisition " + reqNumber + " have status " + DocumentStatus.toString(reqStatus, organizationId);

            incomingRequest.put("errorMessage",messageText);
        	result = messageText;

        	this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.debug(this, "Error SetMessageErrorPoSave: \r\n" + e.getMessage());
        	this.setStatus(Status.FAILED);
        	e.printStackTrace();
        	throw e;
        }
        return result;
    }
}