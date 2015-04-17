package com.tsa.puridiom.rfq.approvals.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddRfqApproverEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String newStatus = (String)incomingRequest.get("newStatus") ;
            String nextUser = (String)incomingRequest.get("forwardedTo") ;
            RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");

            if(rfqHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Solicitation could not be forwarded for Approval!");
            }
            Log.debug(this, "Writting send_queue record for requisition: " + rfqHeader.getRfqNumber() + ", status " + newStatus);

            if(newStatus.equals(DocumentStatus.RFQ_APPROVING) && !Utility.isEmpty(nextUser))
            {
                incomingRequest.put("SendQueue_doctype", "RFQ");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RfqHeader_icRfqHeader"));
                String owner = (String) incomingRequest.get("userId") ;
                StringBuffer subject = new StringBuffer("");
                subject.append(rfqHeader.getDisplayRfqNumber()) ;
                subject.append(" submitted for approval") ;

                incomingRequest.put("SendQueue_subject", subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );

                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), nextUser).getMailId() );

                incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Req: " + rfqHeader.getRfqNumber() );
            }
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Rfq Approval Email couldn't be processed.", e);
        }
        return result;
    }
}