package com.tsa.puridiom.reportqueue.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ReportQueueSetPreviewValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
            String userId = (String)incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
            String browseName = HiltonUtility.ckNull((String)incomingRequest.get("browseName"));

            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }
            String today = Dates.today(userDateFormat, userTimeZone);
            String now = Dates.today("hh:mm:ss", userTimeZone);

            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy/MM/dd");
            String  todaySimp = formatter.format(new Date());

			incomingRequest.put("ReportQueue_frequency","O");
			incomingRequest.put("ReportQueue_startDate",today);
			incomingRequest.put("ReportQueue_endDate",today);
			incomingRequest.put("ReportQueue_status","05");
			incomingRequest.put("ReportQueue_owner",userId);
			incomingRequest.put("ReportQueue_sendTo","");
            //Date Sent is stored as a String, therefore it needs to always be in the format yyyy/MM/dd
			incomingRequest.put("ReportQueue_dateSent",todaySimp);
			incomingRequest.put("ReportQueue_timeSent",now);
			incomingRequest.put("ReportQueue_deliveryTime",now);
            //Delivery Date is stored as a String, therefore it needs to always be in the format yyyy/MM/dd
			incomingRequest.put("ReportQueue_deliveryDay",todaySimp);
			incomingRequest.put("ReportQueue_nextRun",today);
			incomingRequest.put("ReportQueue_publicView",propertiesManager.getProperty("REPORT OPTIONS", "PUBLIC ACCESS", "Y"));
			if (!HiltonUtility.isEmpty(browseName)) {
				incomingRequest.put("ReportQueue_name", browseName);
			}
			incomingRequest.put("webreport", "Y");
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