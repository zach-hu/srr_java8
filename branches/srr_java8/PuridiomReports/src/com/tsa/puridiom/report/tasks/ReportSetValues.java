package com.tsa.puridiom.report.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Report;
import com.tsa.puridiom.entity.ReportPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReportSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReportPK comp_id = new ReportPK();
			Report report = (Report) incomingRequest.get("report");
			if (report == null)
			{
				report = new Report();
			}

			if (incomingRequest.containsKey("Report_reportTitle"))
			{
				String reportTitle = (String ) incomingRequest.get("Report_reportTitle");
				comp_id.setReportTitle(reportTitle);
			}
			if (incomingRequest.containsKey("Report_reportDatawindow"))
			{
				String reportDatawindow = (String ) incomingRequest.get("Report_reportDatawindow");
				report.setReportDatawindow(reportDatawindow);
			}
			if (incomingRequest.containsKey("Report_reportModule"))
			{
				String reportModule = (String ) incomingRequest.get("Report_reportModule");
				comp_id.setReportModule(reportModule);
			}
			if (incomingRequest.containsKey("Report_reportDescription"))
			{
				String reportDescription = (String ) incomingRequest.get("Report_reportDescription");
				report.setReportDescription(reportDescription);
			}
			if (incomingRequest.containsKey("Report_reportXml"))
			{
				String reportXml = (String ) incomingRequest.get("Report_reportXml");
				report.setReportDatawindow(reportXml);
			}
			if (incomingRequest.containsKey("Report_userAccessControl"))
			{
				String userAccessControl = (String ) incomingRequest.get("Report_userAccessControl");
				report.setUserAccessControl(userAccessControl);
			}
			report.setComp_id(comp_id);

			result = report;
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