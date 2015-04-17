package com.tsa.puridiom.rfq.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddRfqPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String oid = (String)incomingRequest.get("organizationId");

            incomingRequest.put("SendQueue_action", SendQueue.PRINT_PDF_ACTION);
            String email = this.getToEmail(incomingRequest);
            StringBuffer subject = new StringBuffer("");
            StringBuffer messageText = new StringBuffer("");
            messageText.append((String)incomingRequest.get("notes"));
            subject.append(PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom") + " ");
            if(Utility.isEmpty(email))
            {
                //email = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "bsca.admin@puridiom.com");
            	email = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            	subject.append("RFQ " + (String)incomingRequest.get("RfqHeader_rfqNumber")) ;

            }
            else
            {
                subject.append("RFQ " + (String)incomingRequest.get("RfqHeader_rfqNumber")) ;
            }
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendto", email);
            String sendFrom = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            incomingRequest.put("SendQueue_sendfrom", sendFrom);
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_args", incomingRequest.get("PdfRfq_vendorId"));
            String printtype = (String)incomingRequest.get("printtype");
            if(HiltonUtility.isEmpty(printtype))
            {
            	printtype = "RFQ";
            }
            incomingRequest.put("SendQueue_doctype", printtype.toUpperCase());
            incomingRequest.put("SendQueue_messagetext", messageText.toString());
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RfqHeader_icRfqHeader"));

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw new TsaException("QueueAddReqPdf failed! " + e.getMessage(), e );
        }
        return result;
    }

    public String getToEmail(Map incomingRequest)
    {
        String emailTo = (String)incomingRequest.get("emailTo");
        if (Utility.isEmpty(emailTo)) { emailTo = "N"; }
        String email = "";
        if(emailTo.equalsIgnoreCase("N"))
        {
            email = "";
        }
        else
        {
            email = (String)incomingRequest.get("email");
        }

        if(Utility.isEmpty(email))
        {
        	Log.warn(this, "No email was specified for request: " + incomingRequest.get("RfqHeader_rfqNumber"));
            email = "";
        }

        return email;

    }
}