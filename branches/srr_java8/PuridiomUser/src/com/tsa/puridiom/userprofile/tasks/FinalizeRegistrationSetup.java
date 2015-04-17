package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class FinalizeRegistrationSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			String	icPackage = (String) incomingRequest.get("icPackage");
			String	packageName = (String) incomingRequest.get("packageName");
			String	status = GeneralStatus.STATUS_ON_HOLD;
			String	dateExpires = Dates.add(Dates.today("yyyy-MM-dd"), 30, "yyyy-MM-dd");
			String	ccResult = (String) incomingRequest.get("ccResult");
			String	ccTranId = (String) incomingRequest.get("ccTranId");
			List	userRoleList = new ArrayList();			
			userRoleList.add("ADMIN");
			
			if (HiltonUtility.ckNull(ccResult).equals("APPROVED") && !HiltonUtility.isEmpty(packageName)) {
				if (packageName.toUpperCase().startsWith("FREE")) {
					status = GeneralStatus.STATUS_TEMPORARY;
					dateExpires = Dates.add(Dates.today(""), 30);
				} else {
					status = GeneralStatus.STATUS_PERMANENT;
					dateExpires = Dates.add(Dates.today(""), 365);
				}				
			}

			incomingRequest.put("Organization_organizationId", organizationId);
			incomingRequest.put("Organization_status", status);
			incomingRequest.put("Organization_dateExpires", dateExpires);

			incomingRequest.put("UserProfile_buyer", "Y");
			incomingRequest.put("UserProfile_requisitioner", "Y");
			incomingRequest.put("UserProfile_receiver", "Y");
			incomingRequest.put("UserProfile_approver", "Y");
			incomingRequest.put("UserProfile_overrider", "Y");
			incomingRequest.put("UserProfile_approvalAmount", "0");
			incomingRequest.put("UserProfile_warrantlAmount", "0");
			incomingRequest.put("UserProfile_vchApp", "N");
			incomingRequest.put("UserProfile_lockLogin", "N");
			incomingRequest.put("UserProfile_reviewProfile", "N");
			incomingRequest.put("UserProfile_status", status);
			incomingRequest.put("UserProfile_shipToCode", "DEFAULT");
			incomingRequest.put("UserProfile_nameUdf1", "DEFAULT"); // bill to code

			incomingRequest.put("UserGroupRel_groupId", "ADMIN");
			incomingRequest.put("UserGroupRel_userId", userId.toUpperCase());
			incomingRequest.put("userRoleList", userRoleList);

			incomingRequest.put("OrganizationPackage_organizationId", organizationId);
			incomingRequest.put("OrganizationPackage_icPackage", icPackage);
			incomingRequest.put("OrganizationPackage_status", status);
			incomingRequest.put("OrganizationPackage_datePaid", Dates.today(""));
			incomingRequest.put("OrganizationPackage_transactionId", ccTranId);
			
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