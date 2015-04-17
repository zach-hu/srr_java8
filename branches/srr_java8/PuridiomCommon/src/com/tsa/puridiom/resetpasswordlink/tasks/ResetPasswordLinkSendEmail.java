package com.tsa.puridiom.resetpasswordlink.tasks;

import com.tsa.puridiom.entity.ResetPasswordLink;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.properties.DictionaryManager;

import java.util.Map;

public class ResetPasswordLinkSendEmail extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String userId = (String) incomingRequest.get("UserProfile_userId");
			String mailId = (String) incomingRequest.get("HostUser_mailId");
			String oid = (String) incomingRequest.get("organizationId");
			String link = "";

		    if (!Utility.isEmpty(mailId)) {

		    	String defaultSiteUrl = DictionaryManager.getInstance("host", oid).getProperty("reportsUrl");
				String siteUrl = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "URL", defaultSiteUrl);
				String servlet = "/ResetPasswordRedirectServlet" ;
				ResetPasswordLink  resetPasswordLink = (ResetPasswordLink) incomingRequest.get("resetPasswordLink");

				//Encryptor enc = new Encryptor();
				if (resetPasswordLink == null) {
					throw new Exception ("ResetPasswordLink was not found.");
				} else {
					link = siteUrl + servlet + "/ack=x100&oid=" + oid + "&userId="+ userId  + "&edoc=" + resetPasswordLink.getIcLink();
				}

		        /*
				String	message = "\n\nUse the next link to change your password.";
			    message = message + "\n\n" + link;
			    message = message + "\n\nThis link can be used only once.";
			    message = message + "\n\nThis link will be active just for 5 hours since this email was sent.";
			    */

			    String	message = "";
			    message += "\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";
			    message += "\n\n" + "A request was received to retrieve the password for your Puridiom 4.0 Login ID.";
			    message += "\n\n" + "Please click here to reset your password:";
			    message += "\n" + link;
			    message += "\n\n\n" + "This is an auto-generated email.  If you did not engage in our automated";
			    message += "\n" + "password retrieval system, please disregard this email.  There have been no";
			    message += "\n" + "changes made to your account information and therefore no need for you to";
			    message += "\n" + "change your password with us.";
			    message += "\n\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";
			    message += "\n\n" + "If this automated email did not resolve your ability to log in, please open an IT Help Desk ticket.";
			    message += "\n\n" + "Thank you,";
			    message += "\n" + "Puridiom 4.0 Help Desk";
			    message += "\n\n" + "P.S. This temporary link will expire within 5 hours. If you are unable to";
			    message += "\n" + "log in, please request a new one here: " + siteUrl;


		    	incomingRequest.put("SendQueue_subject", PropertiesManager.getInstance(oid).getProperty("APPLICATION", "RESETPASSWORDSUBJECT", "Your requested Puridiom Xpress Password"));
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", PropertiesManager.getInstance(oid).getProperty("APPLICATION", "HELPDESKEMAIL","support@puridiom.com"));
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", mailId);
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);
				this.status = process.getStatus() ;

				if (this.getStatus() != Status.SUCCEEDED) {
				    throw new Exception("The email record could not be written to the queue.");
				} else {
				    incomingRequest.put("confirmationMsg", "A link to reset your password will be sent to your email address.");
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