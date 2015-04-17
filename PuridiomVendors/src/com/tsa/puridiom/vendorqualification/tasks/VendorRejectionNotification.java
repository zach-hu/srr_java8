package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.vendorregistration.VendorRegistrationProcessLoader;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class VendorRejectionNotification extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

		    if (vendorRegister != null && !Utility.isEmpty(vendorRegister.getComp_id().getContactEmailAddr())) {
		        String	organizationId = (String) incomingRequest.get("organizationId");
		        String	organizationName = OrganizationManager.getInstance().getOrganizationName(organizationId);
		        String	userId = (String) incomingRequest.get("userId");
		        //String	vendorRegistrationUrl = PropertiesManager.getInstance(organizationId).getProperty("VENDOR-REGISTRATION", "URL", "http://my.puridiom.com/vendor-registration");
		        UserProfile user = UserManager.getInstance().getUser(organizationId, userId);

		        String	message = "\n\n" + organizationName + " is sending this notification to inform you that ";
		        message = message + "\n" + vendorRegister.getVendorName() + " has been rejected as a qualified supplier.  ";
		        message = message + "\n\nThe reason given for this rejection is: ";
		        message = message + "\n" + vendorRegister.getRejectionNotes();
/*
		        message = message + "\n\nTo update your profile or company information, return to our Supplier Registration page at " + vendorRegistrationUrl + ".";
		        message = message + "\n\nYour registered Login Id / E-mail address is:  " + vendorRegister.getComp_id().getContactEmailAddr();
		        message = message + "\nYour registered password is: " + BlackBox.getDecrypt(vendorRegister.getContactPassword());
*/
		        message = message + "\n\nIf you have questions, please email " + user.getDisplayName() + " at " + user.getMailId() + ".";
		        message = message + "\n\nThank you for your interest in partnering with " + organizationName + ".";
		        message = message + "\n\n\n";

				incomingRequest.put("SendQueue_subject", organizationName + " Supplier Qualification Rejection");
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", user.getMailId());
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", vendorRegister.getComp_id().getContactEmailAddr());
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);

				//PuridiomProcessLoader processLoader = new VendorRegistrationProcessLoader(organizationId);
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);
				this.status = process.getStatus() ;

				if (this.getStatus() != Status.SUCCEEDED) {
				    throw new Exception("The email record could not be written to the queue.");
				}
			}
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}