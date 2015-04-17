package com.tsa.puridiom.reportuser.tasks;


import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReportUserSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReportUser reportUser = (ReportUser) incomingRequest.get("reportUser");
			if (reportUser == null)
			{
				reportUser = new ReportUser();
			}
			if (incomingRequest.containsKey("ReportUser_icReportUser"))
			{
				String s_icReporUser = (String) incomingRequest.get("ReportUser_icReportUser");
				BigDecimal icReportUser = new BigDecimal(s_icReporUser);
				reportUser.setIcReportUser(icReportUser);
			}
			if (incomingRequest.containsKey("ReportUser_icReportQueue"))
			{
				String s_icReporQueue = (String) incomingRequest.get("ReportUser_icReportQueue");
				BigDecimal icReportQueue = new BigDecimal(s_icReporQueue);
				reportUser.setIcReportQueue(icReportQueue);
			}
			if (incomingRequest.containsKey("ReportUser_userId"))
			{
				String userId = (String ) incomingRequest.get("ReportUser_userId");
				reportUser.setUserId(userId);
			}
			if (incomingRequest.containsKey("ReportUser_docTitle"))
			{
				String docTitle = (String ) incomingRequest.get("ReportUser_docTitle");
				reportUser.setDocTitle(docTitle);
			}
			if (incomingRequest.containsKey("ReportUser_docFilename"))
			{
				String docFilename = (String ) incomingRequest.get("ReportUser_docFilename");
				reportUser.setDocFilename(docFilename);
			}
			if (incomingRequest.containsKey("ReportUser_dateSent"))
			{
				String ReportUser_dateSent = (String ) incomingRequest.get("ReportUser_dateSent");
				reportUser.setDateSent(ReportUser_dateSent);
			}
			if (incomingRequest.containsKey("ReportUser_timeSent"))
			{
				String ReportUser_timeSent = (String ) incomingRequest.get("ReportUser_timeSent");
				reportUser.setTimeSent(ReportUser_timeSent);
			}
			if (incomingRequest.containsKey("ReportUser_publicView"))
			{
				String ReportUser_publicView = (String ) incomingRequest.get("ReportUser_publicView");
				reportUser.setPublicView(ReportUser_publicView);
			}
			result = reportUser;
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