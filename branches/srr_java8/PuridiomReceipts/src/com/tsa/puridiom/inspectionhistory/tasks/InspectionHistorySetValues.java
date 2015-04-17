package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionHistorySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
        }

		try
		{
			InspectionHistory inspectionHistory = (InspectionHistory) incomingRequest.get("inspectionHistory");
			if (inspectionHistory == null)
			{
				inspectionHistory = new InspectionHistory();
			}

			if (incomingRequest.containsKey("InspectionHistory_icHistory"))
			{
				String icHistoryString = (String) incomingRequest.get("InspectionHistory_icHistory");
				if (Utility.isEmpty(icHistoryString))
				{
					icHistoryString = "0";
				}
				BigDecimal icHistory = new BigDecimal ( icHistoryString );
				inspectionHistory.setIcHistory(icHistory);
			}
			if (incomingRequest.containsKey("InspectionHistory_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("InspectionHistory_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				inspectionHistory.setIcRecLine(icRecLine);
			}
			if (incomingRequest.containsKey("InspectionHistory_icInspNo"))
			{
				String icInspNoString = (String) incomingRequest.get("InspectionHistory_icInspNo");
				if (Utility.isEmpty(icInspNoString))
				{
					icInspNoString = "0";
				}
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );
				inspectionHistory.setIcInspNo(icInspNo);
			}
			if (incomingRequest.containsKey("InspectionHistory_icMsrLine"))
			{
				String icMsrLineString = (String) incomingRequest.get("InspectionHistory_icMsrLine");
				if (Utility.isEmpty(icMsrLineString))
				{
					icMsrLineString = "0";
				}
				BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );
				inspectionHistory.setIcMsrLine(icMsrLine);
			}
			if (incomingRequest.containsKey("InspectionHistory_recType"))
			{
				String recType = (String) incomingRequest.get("InspectionHistory_recType");
				inspectionHistory.setRecType(recType);
			}
			if (incomingRequest.containsKey("InspectionHistory_statusDate"))
			{
				String statusDateString = (String) incomingRequest.get("InspectionHistory_statusDate");
				Date statusDate = Dates.getSqlDate(statusDateString, userDateFormat);

				inspectionHistory.setStatusDate(statusDate);
			}
			if (incomingRequest.containsKey("InspectionHistory_quantity"))
			{
				String quantityString = (String) incomingRequest.get("InspectionHistory_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				inspectionHistory.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("InspectionHistory_area"))
			{
				String area = (String) incomingRequest.get("InspectionHistory_area");
				inspectionHistory.setArea(area);
			}
			if (incomingRequest.containsKey("InspectionHistory_storage"))
			{
				String storage = (String) incomingRequest.get("InspectionHistory_storage");
				inspectionHistory.setStorage(storage);
			}
			if (incomingRequest.containsKey("InspectionHistory_location"))
			{
				String location = (String) incomingRequest.get("InspectionHistory_location");
				inspectionHistory.setLocation(location);
			}
			if (incomingRequest.containsKey("InspectionHistory_status"))
			{
				String status = (String) incomingRequest.get("InspectionHistory_status");
				inspectionHistory.setStatus(status);
			}
			if (incomingRequest.containsKey("InspectionHistory_owner"))
			{
				String owner = (String) incomingRequest.get("InspectionHistory_owner");
				inspectionHistory.setOwner(owner);
			}
			if (incomingRequest.containsKey("InspectionHistory_historyText"))
			{
				String historyText = (String) incomingRequest.get("InspectionHistory_historyText");
				inspectionHistory.setHistoryText(historyText);
			}

			result = inspectionHistory;
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