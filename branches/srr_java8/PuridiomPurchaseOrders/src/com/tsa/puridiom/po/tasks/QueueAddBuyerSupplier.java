package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.SendPoFlag;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class QueueAddBuyerSupplier extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        String subject = "";

        try
        {
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            String oid = (String)incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
            String propertyFax = propertiesManager.getProperty("efax","@efax","@efaxsend.com");
            String sendFromBuyer = propertiesManager.getProperty("efax","faxmail","ramosj@tsagate.com");
            String propertyNumber = propertiesManager.getProperty("efax","faxnumber","717-691-5690");
            String sendTo = propertiesManager.getProperty("efax","adminEmail","cathy.studnicki@hoyavision.com");
            String faxEnabled = propertiesManager.getProperty("FAX", "ENABLED", "N");
            String actionFlag = poHeader.getEdiOrder();
            String message = "";
            String owner = "";

            if(Utility.isEmpty(actionFlag)) {       actionFlag = SendPoFlag.PRINT_PO;    }

            if(actionFlag.equalsIgnoreCase(SendPoFlag.XML_PO))
            {	// send order as a cxml order

				incomingRequest.put("SendQueue_action", "XM");

                incomingRequest.put("SendQueue_sendto", "");
                owner = (String) incomingRequest.get("userId") ;
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(oid, owner).getMailId());
                incomingRequest.put("SendQueue_sendfromtype", "U");
                incomingRequest.put("SendQueue_sendtotype", "U");
            }
            else if(faxEnabled.equalsIgnoreCase("Y") && actionFlag.equalsIgnoreCase(SendPoFlag.FAX_PO))
            {
	            if(actionFlag.equalsIgnoreCase(SendPoFlag.FAX_PO))
	            {
	            	owner = (String) incomingRequest.get("userId");
	                String buyermail = (String) UserManager.getInstance().getUser(oid, owner).getMailId();
	                
	                String sendFrom = propertiesManager.getProperty("efax", "faxmail", "efax.ttco@puridiom.com");
	                
	            	String vendorFax = this.getVendorFax(poHeader.getVendorId(), poHeader.getVendContactCode(), incomingRequest);
	
	            	StringBuffer faxs = new StringBuffer();
	
	            	if(!Utility.isEmpty(vendorFax))
	                {
	            		if (faxs.length() > 0) {
	            			faxs.append(";");
	            		}
	            		vendorFax = vendorFax.replaceAll("\\W|[a-zA-Z]", "");
	
	            		if(vendorFax.length() < 10)
	                	{
	                		Log.debug(this, "Sendto was empty, now: \nSendFrom: " + sendFrom + "\nSendTo: " + faxs);
	                    	faxs.append(sendTo);
	                    	if(!HiltonUtility.isEmpty(buyermail)){
	                    		faxs.append("; " + buyermail);
	                    	}
	                    	incomingRequest.put("SendQueue_action", "FX");
	                    	message = "Your request to email a copy of the PO to the supplier could not be completed " +
	                    			"because the number to this fax is less than 10 numbers\r\n\n" +
	                    			"Supplier: " + poHeader.getVendorName() + "\r\n" +
	                    			" Contact: " + HiltonUtility.getVendContactDetails("name", poHeader.getVendorId(), "", oid);
	                    	subject = "Error-" +"Pdf No To Fax -" + poHeader.getPoNumber();
	                    }
	                	else
	                	{
	                		if(vendorFax.length()>10){
	                        	if(!vendorFax.startsWith("1"))
	                            {
	                        		vendorFax = "1" + vendorFax;
	                            }
	                        }
	                        else{
	                        	vendorFax = "1" + vendorFax;
	                        }
	                        vendorFax += propertyFax;
	                        faxs.append(vendorFax);
	                        message = (String)incomingRequest.get("notes");
	    	                subject = (String)incomingRequest.get("PoHeader_displayNumber");
	                	}
	                }
	            	else {
	            		Log.debug(this, "Vendor Fax is empty ... (actionFlag: " + SendPoFlag.FAX_PO + ")");
	            		incomingRequest.put("SendQueue_action", "FX");
	            		subject = getNotificationEmailSubject(poHeader);
	            		message = this.getNotificationEmailMessage(poHeader, oid);
	            		vendorFax = propertyNumber.replaceAll("\\W|[a-zA-Z]", "");
	            		vendorFax = "1" + vendorFax + propertyFax;
	            		faxs.append(vendorFax);
	            	}
	            	incomingRequest.put("SendQueue_action", "FX");
	                incomingRequest.put("SendQueue_sendfromtype", "E");
	                incomingRequest.put("SendQueue_sendtotype", "E");
	                incomingRequest.put("SendQueue_sendfrom", sendFrom);
	            	incomingRequest.put("SendQueue_sendto",faxs.toString());
	            	incomingRequest.put("SendQueue_messagetext", message);
	            }
            }
            else
            {
            	String sendFrom = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode()).getMailId();
            	StringBuffer emails = new StringBuffer();

                if(actionFlag.equalsIgnoreCase(SendPoFlag.PRINT_PO))
                {
                	this.setStatus(Status.SUCCEEDED);
                	return result;
                }
                else if(actionFlag.equalsIgnoreCase(SendPoFlag.EMAIL_PO))
                {//send to supplier
                	String vendorEmail = this.getVendorEmail(poHeader.getVendorId(), poHeader.getVendContactCode(), incomingRequest);
                	if(!Utility.isEmpty(vendorEmail))
                    {
                		if (emails.length() > 0) {		emails.append(";");		}

                		emails.append(vendorEmail);
                        incomingRequest.put("SendQueue_vendorId", poHeader.getVendorId());
                    }
                	else {
                		Log.debug(this, "Vendor Email is empty ... (actionFlag: " + SendPoFlag.EMAIL_PO + ")");
                		incomingRequest.put("SendQueue_action", "EN");
                		subject = getNotificationEmailSubject(poHeader);
                		message = this.getNotificationEmailMessage(poHeader, oid);
                		emails.append(sendFrom);
                	}
                }
                else
                {
                	this.setStatus(Status.SUCCEEDED);
                	return result;
                }

                incomingRequest.put("SendQueue_action", "XP");
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendfrom", sendFrom);
            	incomingRequest.put("SendQueue_sendto", emails.toString());

            	if(!HiltonUtility.isEmpty(message))
    			{
            		incomingRequest.put("SendQueue_messagetext", message);
    			}
            }

            incomingRequest.put("SendQueue_doctype", "PO");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("PoHeader_icPoHeader"));

            if(HiltonUtility.isEmpty(subject)) {
            	subject = PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom");
            	subject+= " ";
            	subject+= poHeader.getDisplayPoNumber();
            }
            incomingRequest.put("SendQueue_subject", subject);


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

	private String getNotificationEmailSubject(PoHeader poHeader) throws Exception
	{
		String subject = "";
		subject = "Error - " +"Pdf No To Email - " + poHeader.getPoNumber();
		return subject;
	}

	private String getNotificationEmailMessage(PoHeader poHeader, String oid) throws Exception
	{
		String messageText = "";
		messageText = "Your request to email a copy of the PO to the supplier could not be completed " +
				"because there is no email on file for the supplier/contact:\r\n\n" +
				"Supplier: " + poHeader.getVendorName() + "\r\n" +
				"Contact: " + HiltonUtility.getVendContactDetails("name", poHeader.getVendorId(), "", oid);
		return messageText;
	}

    public String getVendorEmail(String vendorId, String contactCode, Map incomingRequest)
    {
    	incomingRequest.put("emailTo", "N");
    	incomingRequest.put("Pdf_PoHeader_vendorId", vendorId);
    	incomingRequest.put("PoHeader_vendContactCode", contactCode);
    	PoEmailUtility poEmailUtility = new PoEmailUtility();
    	return poEmailUtility.getToEmail(incomingRequest);
    }

    public String getVendorFax(String vendorId, String contactCode, Map incomingRequest)
    {
    	incomingRequest.put("faxTo", "N");
    	incomingRequest.put("Pdf_PoHeader_contactId", vendorId);
    	incomingRequest.put("PoHeader_vendContactCode", contactCode);
    	PoFaxUtility poFaxUtility = new PoFaxUtility();
    	return poFaxUtility.getToFax(incomingRequest);
    }
}