package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;

public class DiscrepInspectionHistoryAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

			String	today = Dates.today(userDateFormat, userTimeZone) ;

			incomingRequest.put("InspectionHistory_icHistory", ukg.getUniqueKey().toString());

			incomingRequest.put("InspectionHistory_icRecLine", incomingRequest.get("InspectionDiscrep_icRecLine"));
			incomingRequest.put("InspectionHistory_icInspNo", incomingRequest.get("InspectionDiscrep_icInspNo"));
			incomingRequest.put("InspectionHistory_icMsrLine", incomingRequest.get("InspectionDiscrep_icMsrLine"));
			incomingRequest.put("InspectionHistory_recType", "Discrepancy");
			incomingRequest.put("InspectionHistory_quantity", incomingRequest.get("InspectionDiscrep_inspQuantity"));

			incomingRequest.put("InspectionHistory_status", incomingRequest.get("ReceiptLine_inspectionStatus"));
			incomingRequest.put("InspectionHistory_owner", userId);
			incomingRequest.put("InspectionHistory_statusDate", today);
			incomingRequest.put("InspectionHistory_historyText", incomingRequest.get("inspectionComment"));

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