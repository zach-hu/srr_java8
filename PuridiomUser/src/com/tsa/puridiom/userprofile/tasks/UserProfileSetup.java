package com.tsa.puridiom.userprofile.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class UserProfileSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("UserProfile_mailId", "");
			incomingRequest.put("UserProfile_organizationId", "");
			incomingRequest.put("UserProfile_userId", "");
			incomingRequest.put("UserProfile_firstName", "");
			incomingRequest.put("UserProfile_middleInit", "");
			incomingRequest.put("UserProfile_lastName", "");
			incomingRequest.put("UserProfile_title", "");
			incomingRequest.put("UserProfile_phoneNumber", "");
			incomingRequest.put("UserProfile_faxNumber", "");
			incomingRequest.put("UserProfile_phoneFormat", "");
			incomingRequest.put("UserProfile_faxFormat", "");
			incomingRequest.put("UserProfile_status", "02");
			incomingRequest.put("UserProfile_owner", "");
			incomingRequest.put("UserProfile_dateEntered", Dates.today("", ""));
			incomingRequest.put("UserProfile_dateExpires", Dates.today("", ""));
			incomingRequest.put("UserProfile_signature", "");
			incomingRequest.put("UserProfile_signaturePassword", "");
			incomingRequest.put("UserProfile_functionFlags", "");
			incomingRequest.put("UserProfile_department", "");
			incomingRequest.put("UserProfile_userPassword", "");
			incomingRequest.put("UserProfile_buyer", "");
			incomingRequest.put("UserProfile_requisitioner", "");
			incomingRequest.put("UserProfile_authorizedBy", "");
			incomingRequest.put("UserProfile_receiver", "");
			incomingRequest.put("UserProfile_administeredBy", "");
			incomingRequest.put("UserProfile_approver", "");
			incomingRequest.put("UserProfile_nameUdf1", "");
			incomingRequest.put("UserProfile_nameUdf2", "");
			incomingRequest.put("UserProfile_nameUdf3", "");
			incomingRequest.put("UserProfile_nameUdf4", "");
			incomingRequest.put("UserProfile_nameUdf5", "");
			incomingRequest.put("UserProfile_approvalAmount", "0");
			incomingRequest.put("UserProfile_approveByLine", "");
			incomingRequest.put("UserProfile_mailPassword", "");
			incomingRequest.put("UserProfile_callForward", "");
			incomingRequest.put("UserProfile_excludeLess", "0");
			incomingRequest.put("UserProfile_warrantAmount", "0");
			incomingRequest.put("UserProfile_mailStop", "");
			incomingRequest.put("UserProfile_remoteUser", "");
			incomingRequest.put("UserProfile_passChanged", Dates.today("", ""));
			incomingRequest.put("UserProfile_lockLogin", "");
			incomingRequest.put("UserProfile_empid", "");
			incomingRequest.put("UserProfile_viewReqs", "");
			incomingRequest.put("UserProfile_vchApp", "");
			incomingRequest.put("UserProfile_reqClass", "");
			incomingRequest.put("UserProfile_userType", "");
			incomingRequest.put("UserProfile_locale", "");
			incomingRequest.put("UserProfile_apBatchid", "");
			incomingRequest.put("UserProfile_appointedFlag", "");
			incomingRequest.put("UserProfile_formValidate", "");
			incomingRequest.put("UserProfile_shipToCode", "");
			incomingRequest.put("UserProfile_routing", "");
			incomingRequest.put("UserProfile_overrider", "");
			incomingRequest.put("UserProfile_securityQuestion", "");
			incomingRequest.put("UserProfile_securityAnswer", "");
			incomingRequest.put("UserProfile_costCenter", "");
			incomingRequest.put("UserProfile_reviewProfile", "");
			incomingRequest.put("UserProfile_forwardOffDate", Dates.today("", ""));
			incomingRequest.put("UserProfile_adminBuyer", "");
			incomingRequest.put("UserProfile_contractAmount", "0");
			incomingRequest.put("UserProfile_priorPassword", "");
			incomingRequest.put("UserProfile_backupApprover", "");
			incomingRequest.put("UserProfile_emailVersion", "");
			incomingRequest.put("UserProfile_externalId", "");
			incomingRequest.put("UserProfile_expandBrowse", "");
			incomingRequest.put("UserProfile_language", "");
			incomingRequest.put("UserProfile_currencyCode", "");
			incomingRequest.put("UserProfile_timeZone", "");
			incomingRequest.put("UserProfile_dateFormat", "");
			incomingRequest.put("UserProfile_inspector", "N");
			incomingRequest.put("UserProfile_engineer", "N");
			incomingRequest.put("UserProfile_fpe", "N");
			incomingRequest.put("UserProfile_marker", "N");
			incomingRequest.put("UserProfile_targetCenter", "");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
