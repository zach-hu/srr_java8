package com.tsa.puridiom.userregistration.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Organization;
import com.tsa.puridiom.entity.ResetPasswordLink;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.properties.DictionaryManager;

import java.util.Map;

public class QueueAddAccountActivationLinkEmail extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String oid = (String) incomingRequest.get("UserProfile_organizationId");
			String userId = (String) incomingRequest.get("UserProfile_userId");
			String transactionId = (String) incomingRequest.get("OrganizationPackage_transactionId");
			String packagePrice = (String) incomingRequest.get("packagePrice");
			String packageName = (String) incomingRequest.get("packageName");
			String renewalPrice = packagePrice;
			String link = "";

		    if (!Utility.isEmpty(userId) && !Utility.isEmpty(oid)) {
		    	Organization organization = OrganizationManager.getInstance().getOrganization(oid);
		    	String defaultSiteUrl = DictionaryManager.getInstance("host", oid).getProperty("reportsUrl");
				String siteUrl = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "URL", defaultSiteUrl);
				String servlet = "/ResetPasswordRedirectServlet" ;
				UserProfile userProfile = UserManager.getInstance().getUser(oid, userId);
				ResetPasswordLink  resetPasswordLink = (ResetPasswordLink) incomingRequest.get("resetPasswordLink");

				if (resetPasswordLink == null) {
					throw new Exception ("ResetPasswordLink was not found.");
				} else {
					link = siteUrl + servlet + "/ack=x100&oid=" + oid + "&userId="+ userId  + "&edoc=" + resetPasswordLink.getIcLink();
				}

				int ind = siteUrl.indexOf("/puridiom");
				if (ind > 0) {
					siteUrl = siteUrl.substring(0, ind);
				}

				if (!packagePrice.startsWith("US $")) {
					packagePrice = "US " + packagePrice;
					renewalPrice = packagePrice;
				}
				if (packageName.toUpperCase().indexOf("FREE") > 0) {
					packageName = packageName + " / Individual";
					renewalPrice = "US $500.00";
				}

			    String	message = "";
			    message += "\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";
			    message += "\n\n" + "Welcome to Puridiom 4.0!";
			    message += "\n" + "You have successfully completed the registration process.  Below is your confirmation information.";
			    message += "\n" + "Please keep this information for your records.";
			    message += "\n\n" + "Puridiom 4.0 Website: " + siteUrl;
			    message += "\n\n" + "Organization Id: " + oid;
			    message += "\n" + "Company Name: " + organization.getOrganizationName();
			    message += "\n" + "User Name: " + userProfile.getDisplayName();
			    message += "\n" + "Email Address (Login Id): " + userProfile.getMailId();
			    message += "\n" + "Package Selection: " + packageName + " Subscription";
			    message += "\n" + "Package Price: " + packagePrice;
			    message += "\n" + "Transaction #: " + transactionId;
			    message += "\n" + "Date Registered: " + Dates.today("MM-dd-yyyy");
			    message += "\n" + "Renewal Date: " + HiltonUtility.getFormattedDate(organization.getDateExpires(), oid, "MM-dd-yyyy");
			    message += "\n" + "Renewal Price: " + renewalPrice;
			    message += "\n\n" + "Please click here to activate your account and setup your password:";
			    message += "\n" + link;

			    String	message2 = "";
			    message2 += "\n\n\n" + "This is an auto-generated email.  If you did not engage in our registration system, ";
			    message2 += "\n" + "please disregard this email.";
			    message2 += "\n\n" + "** PLEASE DO NOT REPLY TO THIS EMAIL **";
			    message2 += "\n\n" + "For any questions or concerns, you can use the Support Request Form at http://www.puridiom.com/contact/support.asp or email us directly at " + PropertiesManager.getInstance(oid).getProperty("APPLICATION", "HELPDESKEMAIL","support@puridiom.com") + " .";
			    message2 += "\n\n" + "Thank you,";
			    message2 += "\n" + "Puridiom 4.0 Help Desk";
			    message2 += "\n" + "Experience the Power of On-Demand Purchasing";

		    	incomingRequest.put("SendQueue_subject","Welcome to Puridiom 4.0!");
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", PropertiesManager.getInstance(oid).getProperty("APPLICATION", "HELPDESKEMAIL","support@puridiom.com"));
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(oid, userId).getMailId());
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message);
				incomingRequest.put("SendQueue_messagetext2", message2);
				incomingRequest.put("organizationId", oid);
				incomingRequest.put("userId", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);
				this.status = process.getStatus() ;

				if (this.getStatus() != Status.SUCCEEDED) {
				    throw new Exception("The email record could not be written to the queue.");
				} else {
				    incomingRequest.put("confirmationMsg", "A link to activate your account and setup your password will be sent to your email address.");
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