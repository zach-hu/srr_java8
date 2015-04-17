package com.tsa.puridiom.vendorinsurance.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class VendorInsuranceSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String today = Dates.today("", "");

			incomingRequest.put("VendorInsurance_icPoHeader", "");
			incomingRequest.put("VendorInsurance_vendorId", "");
			incomingRequest.put("VendorInsurance_contNumber", "");
			incomingRequest.put("VendorInsurance_contType", "");
			incomingRequest.put("VendorInsurance_contEffective", today);
			incomingRequest.put("VendorInsurance_contExpires", today);
			incomingRequest.put("VendorInsurance_contAdmin", "");
			incomingRequest.put("VendorInsurance_contModFlag", "");
			incomingRequest.put("VendorInsurance_contModUserid", "");
			incomingRequest.put("VendorInsurance_contModDate", today);
			incomingRequest.put("VendorInsurance_contOwner", "");
			incomingRequest.put("VendorInsurance_contStatus", "");
			incomingRequest.put("VendorInsurance_contRequestDate", today);
			incomingRequest.put("VendorInsurance_contDescription", "");
			incomingRequest.put("VendorInsurance_contUdf1", "");
			incomingRequest.put("VendorInsurance_contUdf2", "");
			incomingRequest.put("VendorInsurance_contUdf3", "");
			incomingRequest.put("VendorInsurance_contUdf4", "");
			incomingRequest.put("VendorInsurance_contUdf5", "");
			incomingRequest.put("VendorInsurance_insuranceStatus", "");
			incomingRequest.put("VendorInsurance_insuranceContact", "");
			incomingRequest.put("VendorInsurance_expiredLetter", today);
			incomingRequest.put("VendorInsurance_coverage1", "");
			incomingRequest.put("VendorInsurance_expires1", "");
			incomingRequest.put("VendorInsurance_named1", "");
			incomingRequest.put("VendorInsurance_waiver1", "");
			incomingRequest.put("VendorInsurance_limit1", "");
			incomingRequest.put("VendorInsurance_coverage2", "");
			incomingRequest.put("VendorInsurance_expires2", "");
			incomingRequest.put("VendorInsurance_named2", "");
			incomingRequest.put("VendorInsurance_waiver2", "");
			incomingRequest.put("VendorInsurance_limit2", "");
			incomingRequest.put("VendorInsurance_coverage3", "");
			incomingRequest.put("VendorInsurance_expires3", "");
			incomingRequest.put("VendorInsurance_named3", "");
			incomingRequest.put("VendorInsurance_waiver3", "");
			incomingRequest.put("VendorInsurance_limit3", "");
			incomingRequest.put("VendorInsurance_coverage4", "");
			incomingRequest.put("VendorInsurance_expires4", "");
			incomingRequest.put("VendorInsurance_named4", "");
			incomingRequest.put("VendorInsurance_waiver4", "");
			incomingRequest.put("VendorInsurance_limit4", "");
			incomingRequest.put("VendorInsurance_coverage5", "");
			incomingRequest.put("VendorInsurance_expires5", "");
			incomingRequest.put("VendorInsurance_named5", "");
			incomingRequest.put("VendorInsurance_waiver5", "");
			incomingRequest.put("VendorInsurance_limit5", "");
			incomingRequest.put("VendorInsurance_coverage6", "");
			incomingRequest.put("VendorInsurance_expires6", "");
			incomingRequest.put("VendorInsurance_named6", "");
			incomingRequest.put("VendorInsurance_waiver6", "");
			incomingRequest.put("VendorInsurance_limit6", "");
			incomingRequest.put("VendorInsurance_certifiedDate1", "");
			incomingRequest.put("VendorInsurance_certStatus1", "");
			incomingRequest.put("VendorInsurance_certifiedDate2", "");
			incomingRequest.put("VendorInsurance_certStatus2", "");
			incomingRequest.put("VendorInsurance_certifiedDate3", "");
			incomingRequest.put("VendorInsurance_certStatus3", "");
			incomingRequest.put("VendorInsurance_certifiedDate4", "");
			incomingRequest.put("VendorInsurance_certStatus4", "");
			incomingRequest.put("VendorInsurance_certifiedDate5", "");
			incomingRequest.put("VendorInsurance_certStatus5", "");
			incomingRequest.put("VendorInsurance_certUdf1", "");
			incomingRequest.put("VendorInsurance_certUdf2", "");
			incomingRequest.put("VendorInsurance_certUdf3", "");
			incomingRequest.put("VendorInsurance_certUdf4", "");
			incomingRequest.put("VendorInsurance_certUdf5", "");
			incomingRequest.put("VendorInsurance_certContact", "");
			incomingRequest.put("VendorInsurance_contractNotes", "");
			incomingRequest.put("VendorInsurance_complianceNotes", "");
			incomingRequest.put("VendorInsurance_insuranceNotes", "");
			incomingRequest.put("VendorInsurance_notifiedDate", today);
			incomingRequest.put("VendorInsurance_dollarValue", "0");
			incomingRequest.put("VendorInsurance_certifiedDate6", "");
			incomingRequest.put("VendorInsurance_certStatus6", "");
			incomingRequest.put("VendorInsurance_certUdf6", "");
			incomingRequest.put("VendorInsurance_certifiedDate7", "");
			incomingRequest.put("VendorInsurance_certStatus7", "");
			incomingRequest.put("VendorInsurance_certUdf7", "");
			incomingRequest.put("VendorInsurance_dateExpires1", "");
			incomingRequest.put("VendorInsurance_dateExpires2", "");
			incomingRequest.put("VendorInsurance_dateExpires3", "");
			incomingRequest.put("VendorInsurance_dateExpires4", "");
			incomingRequest.put("VendorInsurance_dateExpires5", "");
			incomingRequest.put("VendorInsurance_dateExpires6", "");
			incomingRequest.put("VendorInsurance_dateExpires7", "");
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
