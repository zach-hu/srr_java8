package com.tsa.puridiom.disbheader.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

import java.util.Map;

public class DisbHeaderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			incomingRequest.put("DisbHeader_icDsbHeader", "0");
			incomingRequest.put("DisbHeader_disbNumber", "N/A");
			incomingRequest.put("DisbHeader_disbDate", Dates.today("", userTimeZone));
			incomingRequest.put("DisbHeader_status", "");
			incomingRequest.put("DisbHeader_internalComments", "");
			incomingRequest.put("DisbHeader_fiscalYear", "");
			incomingRequest.put("DisbHeader_owner", "");
			incomingRequest.put("DisbHeader_dateEntered", Dates.today("", userTimeZone));
			incomingRequest.put("DisbHeader_itemLocation", "");
			incomingRequest.put("DisbHeader_icAccount", "0");
			incomingRequest.put("DisbHeader_subtotal", "0");
			incomingRequest.put("DisbHeader_approved", "");
			incomingRequest.put("DisbHeader_appBy", "");
			incomingRequest.put("DisbHeader_appDate", Dates.today("", userTimeZone));
			incomingRequest.put("DisbHeader_appSigned", "");
			incomingRequest.put("DisbHeader_lastChgBy", "");
			incomingRequest.put("DisbHeader_lastChgDate", Dates.today("", userTimeZone));
			incomingRequest.put("DisbHeader_disbType", "");
			incomingRequest.put("DisbHeader_icReqHeader", "0");
			incomingRequest.put("DisbHeader_requisitionNumber", "");
			incomingRequest.put("DisbHeader_requisitionerCode", "");
			incomingRequest.put("DisbHeader_printed", "");
			incomingRequest.put("DisbHeader_icHeaderHistory", "0");
            incomingRequest.put("DisbHeader_timeZone", userTimeZone);

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}