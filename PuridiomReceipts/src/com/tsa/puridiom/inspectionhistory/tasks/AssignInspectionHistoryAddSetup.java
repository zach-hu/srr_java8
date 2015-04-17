package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;

public class AssignInspectionHistoryAddSetup extends Task
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

			ReceiptLine recLine =(ReceiptLine)  incomingRequest.get("receiptLine") ;
            UserProfile user = UserManager.getInstance().getUser(organizationId,  recLine.getInspectorAssigned());

    		String description="Receipt Number: " + recLine.getReceiptNumber() +" was assigned to inspector "+ user.getDisplayName()+".";

			incomingRequest.put("InspectionHistory_icHistory", ukg.getUniqueKey().toString());

			incomingRequest.put("InspectionHistory_icRecLine", incomingRequest.get("ReceiptLine_icRecLine"));
			incomingRequest.put("InspectionHistory_icInspNo", incomingRequest.get("InspectionHeader_icInspNo"));
			incomingRequest.put("InspectionHistory_icMsrLine", incomingRequest.get("InspectionHeader_icMsrLine"));
			incomingRequest.put("InspectionHistory_recType", "Inspection");
			incomingRequest.put("InspectionHistory_quantity", incomingRequest.get("qtyInspected"));

			incomingRequest.put("InspectionHistory_area", incomingRequest.get("InspectionHeader_area")) ;
			incomingRequest.put("InspectionHistory_storage", incomingRequest.get("InspectionHeader_storage")) ;
			incomingRequest.put("InspectionHistory_location", incomingRequest.get("InspectionHeader_location")) ;

			incomingRequest.put("InspectionHistory_status", recLine.getStatus());
			incomingRequest.put("InspectionHistory_owner", userId);
			incomingRequest.put("InspectionHistory_statusDate", today);
			incomingRequest.put("InspectionHistory_historyText", description);

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