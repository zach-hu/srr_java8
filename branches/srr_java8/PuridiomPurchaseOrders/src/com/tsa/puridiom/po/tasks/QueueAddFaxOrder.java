package com.tsa.puridiom.po.tasks;

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
import com.tsagate.foundation.utility.Utility;

public class QueueAddFaxOrder extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String oid = (String)incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(oid) ;
            String propertyFax = propertiesManager.getProperty("efax","@efax","@efaxsend.com");
            String sendFrom = propertiesManager.getProperty("efax","faxmail","ramosj@tsagate.com");
            String sendTo = propertiesManager.getProperty("efax","adminEmail","cathy.studnicki@hoyavision.com");
            String cadena= " ";
            String owner = (String) incomingRequest.get("userId");
            String buyermail = (String) UserManager.getInstance().getUser(oid, owner).getMailId();

            PoFaxUtility poFaxUtility = new PoFaxUtility();
            String fax = poFaxUtility.getToFax(incomingRequest);
            StringBuffer subject = new StringBuffer("");
            String messageText = "";

            if(Utility.isEmpty(fax))
            {
            	String propertyNumber = propertiesManager.getProperty("efax","faxnumber","717-691-5690");
                // This code is executed if there is no email for the Supplier or the contact
            	fax = propertyNumber.replaceAll("\\W|[a-zA-Z]", "");

                fax = "1" + fax + propertyFax;
            	Log.debug(this, "Sendto was empty, now: \nSendFrom: " + sendFrom + "\nSendTo: " + fax);
            	incomingRequest.put("SendQueue_action", "FX");
            	messageText = "Your request to email a copy of the PO to the supplier could not be completed " +
            			"because there is no email on file for the supplier/contact:\r\n\n" +
            			"Supplier: " + (String) incomingRequest.get("PoHeader_vendorName") + "\r\n" +
            			" Contact: " + HiltonUtility.getVendContactDetails("name", (String)incomingRequest.get("Pdf_PoHeader_vendorId"), "", oid);
            	subject.append("Error-" +"Pdf No To Fax -" + (String)incomingRequest.get("PoHeader_displayNumber")) ;
            }
            else
            {
            	fax = fax.trim();
            	fax = fax.replaceAll("\\W|[a-zA-Z]", "");
            	if(fax.length() < 10)
            	{
            		Log.debug(this, "Sendto was empty, now: \nSendFrom: " + sendFrom + "\nSendTo: " + fax);
                	fax = sendTo;
                	if(!HiltonUtility.isEmpty(buyermail)){
                		fax += "; " + buyermail;
                	}
                	incomingRequest.put("SendQueue_action", "FX");
                	messageText = "Your request to email a copy of the PO to the supplier could not be completed " +
                			"because the number to this fax is less than 10 numbers\r\n\n" +
                			"Supplier: " + (String) incomingRequest.get("PoHeader_vendorName") + "\r\n" +
                			"Contact: " + HiltonUtility.getVendContactDetails("name", (String)incomingRequest.get("Pdf_PoHeader_vendorId"), "", oid);
                	subject.append("Error-" +"Pdf No To Fax -" + (String)incomingRequest.get("PoHeader_displayNumber")) ;
                }
            	else
            	{
	                fax = fax + propertyFax;
	            	subject.append(PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom") + " ");
	            	incomingRequest.put("SendQueue_action", SendQueue.E_FAX_ACTION);
	            	messageText = (String)incomingRequest.get("notes");
	                subject.append((String)incomingRequest.get("PoHeader_displayNumber")) ;
            	}
	        }

            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", fax);
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
            Log.error(this, e.getMessage());
            throw e;
        }
        return result;
    }
}