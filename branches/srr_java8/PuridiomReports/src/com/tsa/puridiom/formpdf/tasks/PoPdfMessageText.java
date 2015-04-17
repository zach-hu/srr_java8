/*
 * Created on May 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailUtils;
import com.tsa.puridiom.emails.tasks.EmailSentTo;
import com.tsa.puridiom.emails.tasks.PoSentToSupplier;
import com.tsa.puridiom.encryptor.Encryptor;
import com.tsa.puridiom.entity.ApprovalLink;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 */
public class PoPdfMessageText extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
            Log.debug(this, "starting to obtain Email Body.");
            String notes = (String) incomingRequest.get("notes");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            String oid = (String)incomingRequest.get("organizationId");
            String pathToTemplate = (String)incomingRequest.get("pathToTemplate");
            String vendorId = (String) incomingRequest.get("vendorAcknowledgment");
            String reportsUrl = DictionaryManager.getInstance("host", oid).getProperty("reportsUrl");
            String applicationUrl = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "URL", reportsUrl);
            UserProfile buyerProfile = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode());
            PoSentToSupplier message = new PoSentToSupplier(oid, pathToTemplate);

            if (!HiltonUtility.isEmpty(vendorId))
			{
				String actionURL = this.getActionURL(poHeader.getVendorId() + "//" + poHeader.getVendContactCode(), oid, poHeader.getPoNumber(), poHeader.getIcPoHeader());

				message.setEmailLink(applicationUrl + actionURL);
			}

            ret = message.getMessage(poHeader);
            ret = this.addHtmlNotes(ret.toString(), notes);
        	Log.debug(this, "the body is: " + ret);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.error(this, "Error executing PoPdfMessageText \r\n" + e.getMessage());

        	this.setStatus(Status.FAILED);
            throw e;
        }

        return ret;
    }

    private String addHtmlNotes(String message, String notes)
    {
    	if(notes == null) notes = "";
    	if(!HiltonUtility.isEmpty(notes))
    	{
    		notes = notes.replaceAll("\r\n", "<br>");
    		notes = "<p>" + notes + "</p>";
    	}
    	
    	if (!HiltonUtility.isEmpty(message))
    	{
    		message = EmailUtils.replace(EmailSentTo.NOTES, message, notes);
    	} 
    	else 
    	{
    		message = notes;
    	}
    	
    	return message;
    }

    private String getActionURL(String userId, String organizationId, String poNumber, BigDecimal icPoHeader) throws Exception
	{
    	Encryptor enc = new Encryptor();
    	StringBuffer ret = new StringBuffer();
    	String servlet = "/Services";
    	String serviceType = "posupack";
    	String documentType = "PO";
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
		PuridiomProcess process = processLoader.loadProcess("approvallink-add.xml");
		Map incomingRequest = new HashMap();
		ApprovalLink approvalLink;

		incomingRequest.put("organizationId", organizationId);
		incomingRequest.put("ApprovalLink_userId", userId);
		incomingRequest.put("ApprovalLink_organizationId", organizationId);
		incomingRequest.put("ApprovalLink_documentNumber", poNumber);
		incomingRequest.put("ApprovalLink_icHeader", icPoHeader.toString());
		incomingRequest.put("ApprovalLink_doctype", documentType);
		incomingRequest.put("ApprovalLink_action", serviceType);

		process.executeProcess(incomingRequest);

		approvalLink = (ApprovalLink) incomingRequest.get("approvalLink");

		/* Build acknowledgment Link */
		ret.append(servlet);
		ret.append("/zat=" + enc.of_set(organizationId));
		ret.append("&std=" + enc.of_set(approvalLink.getIcApprovalLink().toString()));
		ret.append("&xnot=" + enc.of_set(poNumber));
		ret.append("&ack=" + serviceType);
		ret.append("&xpe=" + enc.of_set(documentType));

		return ret.toString();
	}
}
