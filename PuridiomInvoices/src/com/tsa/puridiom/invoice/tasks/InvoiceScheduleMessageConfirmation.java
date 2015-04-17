package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvoiceScheduleMessageConfirmation extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {

            String schedule = (String)incomingRequest.get("schedule");
            String runDate = (String)incomingRequest.get("runDate");
            String runTime = (String)incomingRequest.get("runTime");

            //String message = schedule + " Interface Manual Run scheduled for " + runDate + " "+ runTime;
            String message = "Please confirm scheduled run:";	
            
            incomingRequest.put("schedule", schedule);
            incomingRequest.put("message", message);

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