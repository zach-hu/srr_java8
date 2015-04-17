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

public class QueueAddOrder extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String oid = (String)incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance("organizationId") ;
            String propertyFax = propertiesManager.getProperty("efax","@efax","@efax.com");
            String sendFrom = propertiesManager.getProperty("efax","faxmail","ramosj@tsagate.com");
            String cadena= " ";

            PoFaxUtility poFaxUtility = new PoFaxUtility();
            String fax = poFaxUtility.getToFax(incomingRequest);
            StringBuffer subject = new StringBuffer("");
            String messageText = "";

            if(Utility.isEmpty(fax))
            {
            	String propertyNumber = propertiesManager.getProperty("efax","faxnumber","717-691-5690");
                // This code is executed if there is no email for the Supplier or the contact
                propertyNumber = propertyNumber.trim();
                for (int x=0; x < propertyNumber.length(); x++) {
                	if (propertyNumber.charAt(x) != '(' && propertyNumber.charAt(x) != ')' && propertyNumber.charAt(x) != '-' && propertyNumber.charAt(x) != ' ' && propertyNumber.charAt(x) != ';' && propertyNumber.charAt(x) != '.')
                		cadena += propertyNumber.charAt(x);
                }

            	fax = cadena + propertyFax;
            	Log.debug(this, "Sendto was empty, now: \nSendFrom: " + sendFrom + "\nSendTo: " + fax);
            	incomingRequest.put("SendQueue_action", "FX");
            	messageText = "Your request to email a copy of the PO to the supplier could not be completed " +
            			"because there is no email on file for the supplier/contact:\r\n\n" +
            			"Supplier: " + (String) incomingRequest.get("PoHeader_vendorName") + "\r\n" +
            			"Contact: " + HiltonUtility.getVendContactDetails("name", (String)incomingRequest.get("Pdf_PoHeader_vendorId"), "", oid);
            	incomingRequest.put("SendQueue_messagetext", messageText);
                subject.append("Error-" +"Pdf No To Fax -" + (String)incomingRequest.get("PoHeader_displayNumber")) ;
            }
            else
            {
            	fax = fax.trim();
                for (int x=0; x < fax.length(); x++) {
                	if (fax.charAt(x) != '(' && fax.charAt(x) != ')' && fax.charAt(x) != '-' && fax.charAt(x) != ' ' && fax.charAt(x) != ';' && fax.charAt(x) != '.')
                        cadena += fax.charAt(x);
                }
                fax = cadena + propertyFax;
            	subject.append(PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom") + " ");
            	incomingRequest.put("SendQueue_action", "FX");
            	messageText = (String)incomingRequest.get("notes");
                subject.append((String)incomingRequest.get("PoHeader_displayNumber")) ;
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
            throw e;
        }
        return result;
    }
}