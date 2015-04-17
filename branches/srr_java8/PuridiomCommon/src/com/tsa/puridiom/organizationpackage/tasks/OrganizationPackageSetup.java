package com.tsa.puridiom.organizationpackage.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class OrganizationPackageSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("OrganizationPackage_icOrgPackage", "0");
			incomingRequest.put("OrganizationPackage_organizationId", "");
			incomingRequest.put("OrganizationPackage_icPackage", "0");
			incomingRequest.put("OrganizationPackage_packageType", "");
			incomingRequest.put("OrganizationPackage_unitPrice", "0");
			incomingRequest.put("OrganizationPackage_quantity", "0");
			incomingRequest.put("OrganizationPackage_total", "0");
			incomingRequest.put("OrganizationPackage_buyerCount", "0");
			incomingRequest.put("OrganizationPackage_requisitionerCount", "0");
			incomingRequest.put("OrganizationPackage_purchasedBy", "");
			incomingRequest.put("OrganizationPackage_datePurchased", Dates.today("", ""));
			incomingRequest.put("OrganizationPackage_datePaid", Dates.today("", ""));
			incomingRequest.put("OrganizationPackage_transactionId", "");
			incomingRequest.put("OrganizationPackage_status", "");
			incomingRequest.put("OrganizationPackage_dateActive", Dates.today("", ""));
			incomingRequest.put("OrganizationPackage_dateExpires", Dates.today("", ""));

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