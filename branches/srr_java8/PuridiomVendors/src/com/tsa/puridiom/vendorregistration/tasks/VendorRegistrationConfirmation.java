package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.vendorregistration.VendorRegistrationProcessLoader;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class VendorRegistrationConfirmation extends Task
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
		        String	vendorRegistrationUrl = PropertiesManager.getInstance(organizationId).getProperty("VENDOR-REGISTRATION", "URL", "http://my.puridiom.com/vendor-registration");
		        String	helpDeskEmail = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "HelpDeskEmail", "support@puridiom.com");

		        String  message = "\n\nYour company has recently registered as a potential supplier to " + organizationName + ".";

		        message = message + "\n\nYour registered Login Id / E-mail address is:  " + vendorRegister.getComp_id().getContactEmailAddr();
		        message = message + "\nYour registered password is: " + BlackBox.getDecrypt(vendorRegister.getContactPassword());
		        message = message + "\nYour registered company is: " + vendorRegister.getVendorName();
		        message = message + "\n\nIn order to do business with " + organizationName + ", you must complete the Pre-Qualification information and submit it for final review.";
		        message = message + "  Once accepted, you will be added to " + organizationName + "'s Suppler Database and will receive a Qualification Acceptance email notification." ;
		        message = message + "\n\nTo complete your Pre-Qualification information, please return to our Supplier Portal at " + vendorRegistrationUrl + ".";
		        message = message + "\n\nIf you have questions regarding your registration, please contact the Puridiom Help Desk at " + helpDeskEmail + ".";
		        message = message + "\n\nThank you for your interest in partnering with " + organizationName + ".";
		        message = message + "\n\n\n";

				incomingRequest.put("SendQueue_subject", organizationName + " Supplier Registration Confirmation");
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", helpDeskEmail);
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", vendorRegister.getComp_id().getContactEmailAddr());
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);

				PuridiomProcessLoader processLoader = new VendorRegistrationProcessLoader(organizationId);
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