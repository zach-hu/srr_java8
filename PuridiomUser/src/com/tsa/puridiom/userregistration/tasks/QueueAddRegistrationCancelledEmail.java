package com.tsa.puridiom.userregistration.tasks;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.properties.DictionaryManager;

import java.util.Map;

public class QueueAddRegistrationCancelledEmail extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String oid = (String) incomingRequest.get("UserProfile_organizationId");
			String userId = (String) incomingRequest.get("UserProfile_userId");
			String packageName = (String) incomingRequest.get("packageName");

		    if (!Utility.isEmpty(oid)) {
		    	String organizationName = OrganizationManager.getInstance().getOrganizationName(oid);
		    	String defaultSiteUrl = DictionaryManager.getInstance("host", oid).getProperty("reportsUrl");
				String siteUrl = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "URL", defaultSiteUrl);
				UserProfile userProfile = UserManager.getInstance().getUser(oid, userId);

				int ind = siteUrl.indexOf("/puridiom");
				if (ind > 0) {
					siteUrl = siteUrl.substring(0, ind);
				}

				String	message = "";
			    message += "\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";
			    message += "\n\n" + "Puridiom 4.0 Website: " + siteUrl;
			    message += "\n\n" + userProfile.getDisplayName() + " has cancelled the registration process for " + organizationName + ".";
			    message += "\n" + "User Email Address: " + userProfile.getMailId();
			    message += "\n" + "User Phone Number: " + userProfile.getPhoneNumber();
			    message += "\n" + "Package Cancelled: " + packageName;
			    message += "\n" + "Date Cancelled: " + Dates.today("MM-dd-yyyy");
			    message += "\n\n\n" + "This is an auto-generated email.";
			    message += "\n\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";

		    	incomingRequest.put("SendQueue_subject","Puridiom 4.0 Registration Cancellation");
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", PropertiesManager.getInstance(oid).getProperty("APPLICATION", "HELPDESKEMAIL","support@puridiom.com"));
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", "puridiom@puridiom.com");
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);
				incomingRequest.put("organizationId", oid);
				incomingRequest.put("userId", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);
				this.status = process.getStatus() ;

				if (this.getStatus() != Status.SUCCEEDED) {
				    Log.error(this, "Send Queue record could not be written for support notification.");
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