package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
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
		    UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");

		    if (!Utility.isEmpty(userProfile.getMailId())) {
		        String password = BlackBox.getDecrypt(userProfile.getUserPassword());
		        String	message = "\n\nYour new temporary password is:  " + password;
			    message = message + "\n\nUse this temporary password to log in and create a permanent password for your account at " + PropertiesManager.getInstance(userProfile.getOrganizationId()).getProperty("APPLICATION", "URL", "http://my.puridiom.com");
			    message = message + "\n\nShould you need further technical assistance, please email your request to " + PropertiesManager.getInstance(userProfile.getOrganizationId()).getProperty("APPLICATION", "HelpDeskEmail", "support@puridiom.com");

				incomingRequest.put("SendQueue_subject","Puridiom Password Request");
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", PropertiesManager.getInstance(userProfile.getOrganizationId()).getProperty("APPLICATION", "HELPDESKEMAIL","support@puridiom.com"));
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", userProfile.getMailId());
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
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