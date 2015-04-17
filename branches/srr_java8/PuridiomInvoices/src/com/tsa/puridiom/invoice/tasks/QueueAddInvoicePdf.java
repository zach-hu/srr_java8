package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class QueueAddInvoicePdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String oid = (String)incomingRequest.get("organizationId");
                        
            String email = InvoiceEmailUtility.getToEmail(incomingRequest);
            String linkToPO = (String) incomingRequest.get("linkToPO");
            StringBuffer subject = new StringBuffer("");
            subject.append(PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom") + " ");
            String messageText = "";
        	InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");

            if(Utility.isEmpty(email))
            {
            	Log.debug(this, "Sendto was empty");
            	//email = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
            	incomingRequest.put("SendQueue_sendto", invoiceHeader.getOrderByEmail());
            	messageText = "Your request to email a copy of the PO to the supplier could not be completed " +
    			"because there is no email on file for the supplier/contact:\r\n\n" +
    			"Supplier: " + (String) incomingRequest.get("PoHeader_vendorName") + "\r\n" +
    			"Contact: " + HiltonUtility.getVendContactDetails("name", (String)incomingRequest.get("Pdf_PoHeader_vendorId"), "", oid);
               	subject.append("Error-" +"Pdf No To Email -" + (String)incomingRequest.get("InvoiceHeader_invoiceNumber")) ;
            }
            else
            {
            	incomingRequest.put("SendQueue_sendto", email);
            	incomingRequest.put("SendQueue_action", "XP");
            	messageText = (String)incomingRequest.get("notes");
                subject.append("Invoice " + (String)incomingRequest.get("InvoiceHeader_invoiceNumber")) ;
                
            }
            if (Utility.ckNull(linkToPO).equals("Y")) {
		    	String defaultSiteUrl = DictionaryManager.getInstance("host", oid).getProperty("reportsUrl");
				String siteUrl = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "URL", defaultSiteUrl);
				String link = siteUrl + "/?ack=x100&etype=PO&edoc=" + invoiceHeader.getIcPoHeader();

				messageText += "\r\n\r\n<a href='" + link + "'>Click here to view the Order associated with this Invoice.</a>"; 
            }
            incomingRequest.put("SendQueue_subject", subject.toString() );
            incomingRequest.put("SendQueue_sendfrom", (String)UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId());
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_doctype", "IVC");
            incomingRequest.put("SendQueue_messagetext", messageText);
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("InvoiceHeader_icIvcHeader"));
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