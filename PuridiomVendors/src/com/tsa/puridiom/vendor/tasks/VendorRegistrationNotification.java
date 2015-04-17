package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorRegistrationNotification extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            Vendor vendor = (Vendor) incomingRequest.get("vendor");
            Contact contact = (Contact) incomingRequest.get("contact");
            String	organizationId = (String) incomingRequest.get("organizationId");
            String	adminEmail = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "ADMINEMAILADDR", "support@tsagate.com");
            String	sendTo = UserManager.getInstance().getUser(organizationId, vendor.getOwner()).getMailId();
            
            if (HiltonUtility.isEmpty(sendTo)) {
                sendTo = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "VENDORADMINEMAIL", "");
            }
            if (HiltonUtility.isEmpty(sendTo)) {
                return null;
            }

            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", adminEmail);
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", sendTo);
            incomingRequest.put("SendQueue_doctype", "");
            incomingRequest.put("SendQueue_docic", "");
            incomingRequest.put("SendQueue_subject", OrganizationManager.getInstance().getOrganizationName(organizationId) + " Bid Board Registration Notification" );
            incomingRequest.put("SendQueue_messagetext", contact.getDisplayName() + " [" + contact.getEmailAddr() + "] has registered as a contact for the qualified vendor: " + vendor.getVendorName() + " [" + vendor.getVendorId() + "] on " + HiltonUtility.getFormattedDate(contact.getDateEntered(), organizationId) +".");

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