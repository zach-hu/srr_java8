package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.vendorregistration.VendorRegistrationProcessLoader;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class PasswordHelpSend extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");

		    if (registerUser != null && !Utility.isEmpty(registerUser.getEmailAddress())) {
		        String	organizationId = registerUser.getOrganizationId();
		        String	organizationName = OrganizationManager.getInstance().getOrganizationName(organizationId);
		        String password = BlackBox.getDecrypt((String) incomingRequest.get("RegisterUser_userPassword"));
		        String	message = "\n\nYour new temporary password is:  " + password;
			    message = message + "\n\nUse this temporary password to log in and create a permanent password for your account at " + PropertiesManager.getInstance(organizationId).getProperty("VENDOR-REGISTRATION", "URL", "http://my.puridiom.com/vendor-registration");
			    message = message + "\n\nShould you need further technical assistance, please email your request to " + PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "HelpDeskEmail", "support@puridiom.com");

				incomingRequest.put("SendQueue_subject", organizationName + " Vendor Registration Password Request");
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "HelpDeskEmail","support@puridiom.com"));
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", registerUser.getEmailAddress());
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);

				PuridiomProcessLoader processLoader = new VendorRegistrationProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);
				this.status = process.getStatus() ;

				if (this.getStatus() != Status.SUCCEEDED) {
				    throw new Exception("The email record could not be written to the queue.");
				} else {
				    incomingRequest.put("confirmationMsg", "Your password will be sent to your email address.");
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