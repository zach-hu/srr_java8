package com.tsa.puridiom.userregistration.tasks;

import com.tsa.puridiom.entity.ResetPasswordLink;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.properties.DictionaryManager;

import java.util.Map;

public class QueueAddUserRegisteredLinkEmail extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String oid = (String)incomingRequest.get("UserProfile_organizationId") ;
            String userId = (String)incomingRequest.get("UserProfile_userId") ;
        	UserProfile userProfile = UserManager.getInstance().getUser(oid, userId);
        	String administrator = UserManager.getInstance().getAdministratorName(oid);
        	String mailId = userProfile.getMailId();
			String link = "";

        	if(Utility.isEmpty(userId))
            {
            	this.setStatus(Status.FAILED);
            	throw new TsaException(this.getName() + " User ID was not found!");
            }

            Log.debug(this, "Writting send_queue record for user registration of User ID: " + userId + ", Organization ID " + oid);

		    if (!Utility.isEmpty(mailId)) {
		    	String defaultSiteUrl = DictionaryManager.getInstance("host", oid).getProperty("reportsUrl");
				String siteUrl = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "URL", defaultSiteUrl);
				String servlet = "/ResetPasswordRedirectServlet" ;
				ResetPasswordLink  resetPasswordLink = (ResetPasswordLink) incomingRequest.get("resetPasswordLink");

				if (resetPasswordLink == null) {
					throw new Exception ("ResetPasswordLink was not found.");
				} else {
					link = siteUrl + servlet + "/ack=x100&oid=" + oid + "&userId="+ userId  + "&edoc=" + resetPasswordLink.getIcLink();
				}
			    String	message = "";
			    message += "\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";
			    message += "\n\n" + "Welcome to Puridiom 4.0!";
			    message += "\n\n" + "Your new Puridiom 4.0 login account has been set up by your administrator, " + administrator + "!";
			    message += "\n\n" + "Please click here to setup your password and activate your account:";
			    message += "\n" + link;
			    message += "\n\n\n" + "This is an auto-generated email.";
			    message += "\n\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";
			    message += "\n\n" + "If this automated email did not allow you to log in, please contact support@puridiom.com.";
			    message += "\n\n" + "Thank you,";
			    message += "\n" + "Puridiom 4.0 Help Desk";
			    message += "\n\n" + "P.S. This temporary link will expire within 5 hours. If you are unable to";
			    message += "\n" + "log in, please request a new one here: " + siteUrl;

		    	incomingRequest.put("SendQueue_subject","Welcome to Puridiom 4.0!");
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
				    incomingRequest.put("confirmationMsg", "A link to setup your password and activate your account will be sent to your email address.");
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