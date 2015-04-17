package com.tsa.puridiom.vendorinsurancedefault.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class VendorInsuranceDefaultSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String userId = (String) incomingRequest.get("userId");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");
			if (Utility.isEmpty(userDateFormat)) {
				userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
			}
			String today = Dates.today(userDateFormat, userTimeZone);

			incomingRequest.put("VendorInsuranceDefault_vendorId", "");
			incomingRequest.put("VendorInsuranceDefault_insuranceContact", "");
			incomingRequest.put("VendorInsuranceDefault_insuranceOverride", "");
			incomingRequest.put("VendorInsuranceDefault_insuranceNotes", "");
			incomingRequest.put("VendorInsuranceDefault_coverage1", "");
			incomingRequest.put("VendorInsuranceDefault_expires1", "");
			incomingRequest.put("VendorInsuranceDefault_named1", "");
			incomingRequest.put("VendorInsuranceDefault_waiver1", "");
			incomingRequest.put("VendorInsuranceDefault_limit1", "0");
			incomingRequest.put("VendorInsuranceDefault_coverage2", "");
			incomingRequest.put("VendorInsuranceDefault_expires2", "");
			incomingRequest.put("VendorInsuranceDefault_named2", "");
			incomingRequest.put("VendorInsuranceDefault_waiver2", "");
			incomingRequest.put("VendorInsuranceDefault_limit2", "0");
			incomingRequest.put("VendorInsuranceDefault_coverage3", "");
			incomingRequest.put("VendorInsuranceDefault_expires3", "");
			incomingRequest.put("VendorInsuranceDefault_named3", "");
			incomingRequest.put("VendorInsuranceDefault_waiver3", "");
			incomingRequest.put("VendorInsuranceDefault_limit3", "0");
			incomingRequest.put("VendorInsuranceDefault_coverage4", "");
			incomingRequest.put("VendorInsuranceDefault_expires4", "");
			incomingRequest.put("VendorInsuranceDefault_named4", "");
			incomingRequest.put("VendorInsuranceDefault_waiver4", "");
			incomingRequest.put("VendorInsuranceDefault_limit4", "0");
			incomingRequest.put("VendorInsuranceDefault_coverage5", "");
			incomingRequest.put("VendorInsuranceDefault_expires5", "");
			incomingRequest.put("VendorInsuranceDefault_named5", "");
			incomingRequest.put("VendorInsuranceDefault_waiver5", "");
			incomingRequest.put("VendorInsuranceDefault_limit5", "0");
			incomingRequest.put("VendorInsuranceDefault_coverage6", "");
			incomingRequest.put("VendorInsuranceDefault_expires6", "");
			incomingRequest.put("VendorInsuranceDefault_named6", "");
			incomingRequest.put("VendorInsuranceDefault_waiver6", "");
			incomingRequest.put("VendorInsuranceDefault_limit6", "0");
			incomingRequest.put("VendorInsuranceDefault_insuranceStatus", "");
			incomingRequest.put("VendorInsuranceDefault_complianceContact", "");
			incomingRequest.put("VendorInsuranceDefault_complianceNotes", "");
			incomingRequest.put("VendorInsuranceDefault_certifiedDate1", "");
			incomingRequest.put("VendorInsuranceDefault_certStatus1", "");
			incomingRequest.put("VendorInsuranceDefault_certUdf1", "");
			incomingRequest.put("VendorInsuranceDefault_certifiedDate2", "");
			incomingRequest.put("VendorInsuranceDefault_certStatus2", "");
			incomingRequest.put("VendorInsuranceDefault_certUdf2", "");
			incomingRequest.put("VendorInsuranceDefault_certifiedDate3", "");
			incomingRequest.put("VendorInsuranceDefault_certStatus3", "");
			incomingRequest.put("VendorInsuranceDefault_certUdf3", "");
			incomingRequest.put("VendorInsuranceDefault_certifiedDate4", "");
			incomingRequest.put("VendorInsuranceDefault_certStatus4", "");
			incomingRequest.put("VendorInsuranceDefault_certUdf4", "");
			incomingRequest.put("VendorInsuranceDefault_certifiedDate5", "");
			incomingRequest.put("VendorInsuranceDefault_certStatus5", "");
			incomingRequest.put("VendorInsuranceDefault_certUdf5", "");
			incomingRequest.put("VendorInsuranceDefault_certifiedDate6", "");
			incomingRequest.put("VendorInsuranceDefault_certStatus6", "");
			incomingRequest.put("VendorInsuranceDefault_certUdf6", "");
			incomingRequest.put("VendorInsuranceDefault_expiredLetter", today);
			incomingRequest.put("VendorInsuranceDefault_notifiedDate", today);
			incomingRequest.put("VendorInsuranceDefault_lastChangeDate", today);
			incomingRequest.put("VendorInsuranceDefault_lastChangeBy", userId);
			

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
