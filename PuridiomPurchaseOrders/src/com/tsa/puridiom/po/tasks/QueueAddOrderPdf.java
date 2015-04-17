package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class QueueAddOrderPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String oid = (String)incomingRequest.get("organizationId");

            PoEmailUtility poEmailUtility = new PoEmailUtility();
            String email = poEmailUtility.getToEmail(incomingRequest);
            String sendFrom = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            StringBuffer subject = new StringBuffer("");
            String messageText = "";

            if(Utility.isEmpty(email))
            {
            	// This code is executed if there is no email for the Supplier or the contact
            	email = sendFrom;
            	Log.debug(this, "Sendto was empty, now: \nSendFrom: " + sendFrom + "\nSendTo: " + email);
            	incomingRequest.put("SendQueue_action", "EN");
            	messageText = "Your request to email a copy of the PO to the supplier could not be completed " +
            			"because there is no email on file for the supplier/contact:\r\n\n" +
            			"Supplier: " + (String) incomingRequest.get("PoHeader_vendorName") + "\r\n" +
            			"Contact: " + HiltonUtility.getVendContactDetails("name", (String)incomingRequest.get("Pdf_PoHeader_vendorId"), "", oid);
            	incomingRequest.put("SendQueue_messagetext", messageText);
                subject.append("Error-" +"Pdf No To Email -" + (String)incomingRequest.get("PoHeader_displayNumber")) ;
            }
            else
            {
            	subject.append(PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom") + " ");
            	incomingRequest.put("SendQueue_action", "XP");
            	messageText = (String)incomingRequest.get("notes");
                subject.append((String)incomingRequest.get("PoHeader_displayNumber")) ;
            }


            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", email);
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", sendFrom);
            incomingRequest.put("SendQueue_doctype", "PO");
            incomingRequest.put("SendQueue_messagetext", messageText);
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("PoHeader_icPoHeader"));
            String TCs = (String)incomingRequest.get("TCs");
            if(TCs == null) {   TCs = "N";  }
            if(TCs.equalsIgnoreCase("Y"))
            {
                incomingRequest.put("SendQueue_args", "TCs=" + TCs + "@@");
            }
            else
            {
                incomingRequest.put("SendQueue_args", "");
            }

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