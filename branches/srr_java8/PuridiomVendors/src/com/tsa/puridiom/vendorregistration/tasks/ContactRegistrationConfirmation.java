package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class ContactRegistrationConfirmation extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String	organizationId = (String) incomingRequest.get("organizationId");
	        String	organizationName = OrganizationManager.getInstance().getOrganizationName(organizationId);
	        String	userId = (String) incomingRequest.get("userId");
	        PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
            
            Contact contact = (Contact) incomingRequest.get("contact");
            Vendor vendor = (Vendor) incomingRequest.get("vendor");
            String	vendorOwner = vendor.getOwner();
            String ownerName = "";
            String	ownerEmailAddress = "";
            String	vendorRegistrationUrl = PropertiesManager.getInstance(organizationId).getProperty("VENDOR-REGISTRATION", "URL", "http://my.puridiom.com/vendor-registration");
            
	        if (!Utility.isEmpty(vendorOwner) && !vendorOwner.equalsIgnoreCase("PURIDIOM-BB")) {
	            UserProfile user = UserManager.getInstance().getUser(organizationId, vendor.getOwner());
	            
	            ownerName = user.getDisplayName();
	            ownerEmailAddress = user.getMailId();
	        }
	        if (Utility.isEmpty(ownerName) || Utility.isEmpty(ownerEmailAddress)) {
	            ownerName = "the Puridiom Help Desk";
	            ownerEmailAddress = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "HelpDeskEmail", "support@puridiom.com");
	        }
	        
	        String	message = "\n\n" + vendor.getVendorName() + " has recently registered and been accepted as a qualified supplier.  "; 
	        message = message + organizationName + " looks forward to potential opportunities to do business with your company.";
	        message = message + "\n\nIn order for " + organizationName + " to effectively communicate with your organization and know";
	        message = message + " the types of materials and services your company provides, it is important that you continue to maintain";
	        message = message + " and update your profile information when changes occur.";
	        message = message + "\n\nTo update your profile or company information, return to our Supplier Registration page at " + vendorRegistrationUrl + "."; 
	        message = message + "\n\nYour registered Login Id / E-mail address is:  " + contact.getEmailAddr(); 
	        message = message + "\nYour registered password is: " + BlackBox.getDecrypt(contact.getContactPassword());
	        message = message + "\n\nIf you have questions, please contact " + ownerName + " at " + ownerEmailAddress + ".";
	        message = message + "\n\nThank you for your interest in partnering with " + organizationName + ".";
	        message = message + "\n\n\n";
            
            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", ownerEmailAddress);
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", contact.getEmailAddr());
            incomingRequest.put("SendQueue_doctype", "");
            incomingRequest.put("SendQueue_docic", "");
            incomingRequest.put("SendQueue_subject", organizationName + " Supplier Registration Confirmation");
            incomingRequest.put("SendQueue_messagetext", message);

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