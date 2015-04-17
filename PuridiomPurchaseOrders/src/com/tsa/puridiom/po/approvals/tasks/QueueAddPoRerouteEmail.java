package com.tsa.puridiom.po.approvals.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddPoRerouteEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String newStatus = (String)incomingRequest.get("newStatus");
            String forwardedTo = (String) incomingRequest.get("routTo");
            String poNumber =  (String) incomingRequest.get("PoHeader_poNumber");
            String userTimeZone =  (String) incomingRequest.get("userTimeZone");
            PoHeader poh = (PoHeader) incomingRequest.get("poHeader");
            if (poNumber == null)
            {
            	poNumber = poh.getPoNumber();
                if (poNumber == null)
                {
                    this.setStatus(Status.FAILED);
                    throw new TsaException(this.getName() + " Order was not found!");
                }
            }

            if (newStatus.equals(DocumentStatus.PO_APPROVING) && !Utility.isEmpty(forwardedTo))
            {
                incomingRequest.put("SendQueue_doctype", "PO");
                incomingRequest.put("SendQueue_docic", (String) incomingRequest.get("PoHeader_icPoHeader"));

                String owner = (String) incomingRequest.get("userId");

                StringBuffer subject = new StringBuffer("");
                subject.append(UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getDisplayName());
                subject.append(" has forwarded Order ");
                subject.append(poNumber);
                subject.append(" for your approval.");

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), forwardedTo).getMailId() );

                incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
                String ApprovalLog_approverNotes = (String)incomingRequest.get("ApprovalLog_approverNotes");
                StringBuffer rerouteMessage = new StringBuffer();
                rerouteMessage.append(subject + "\n");
                rerouteMessage.append("on " + Dates.today("", userTimeZone) + " " + userTimeZone);
                rerouteMessage.append("\n");

                if (ApprovalLog_approverNotes != null)
                {
                	rerouteMessage.append("Approver Notes:\n");
                	rerouteMessage.append(ApprovalLog_approverNotes);
                    incomingRequest.put("SendQueue_messagetext", rerouteMessage.toString());
                }
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus();
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Order: " + poNumber );
            }
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}