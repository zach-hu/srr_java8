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

public class QueueAddInvoiceScheduleRun extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String oid = (String)incomingRequest.get("organizationId");
            String owner = (String) incomingRequest.get("userId");

            String runtime = (String)incomingRequest.get("runtime");
            String rundate = (String) incomingRequest.get("rundate");
            String sendTo = (String) incomingRequest.get("email");
            String schedule = (String) incomingRequest.get("schedule");
            StringBuffer messageText = new StringBuffer("");
            StringBuffer subject = new StringBuffer("");
            String s_schedule = rundate + " at " + runtime;

            subject.append(schedule + " Interface for " + s_schedule);
            messageText.append(subject).append("\n\n");
            messageText.append((String)incomingRequest.get("notes"));

            String args = schedule.toUpperCase() + " " + s_schedule;

            //incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("SendQueue_doctype", "EXT");
            incomingRequest.put("SendQueue_owner", owner );
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
            incomingRequest.put("SendQueue_sendto", sendTo );
            incomingRequest.put("SendQueue_dateadded", rundate );
            incomingRequest.put("SendQueue_timeadded", runtime );
            incomingRequest.put("SendQueue_messagetext", messageText.toString());
            incomingRequest.put("SendQueue_args", args);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}