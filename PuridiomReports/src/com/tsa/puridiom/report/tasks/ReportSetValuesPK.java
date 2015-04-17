package com.tsa.puridiom.report.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Report;
import com.tsa.puridiom.entity.ReportPK;

public class ReportSetValuesPK
{
	public void setValues(Map incomingRequest, Report report ) throws Exception
	{
		try
		{
			String reportTitle = (String ) incomingRequest.get("Report_reportTitle");
			String reportModule = (String ) incomingRequest.get("Report_reportModule");
			ReportPK comp_id = new ReportPK();
			comp_id.setReportModule(reportModule);
			comp_id.setReportTitle(reportTitle);
			report.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}