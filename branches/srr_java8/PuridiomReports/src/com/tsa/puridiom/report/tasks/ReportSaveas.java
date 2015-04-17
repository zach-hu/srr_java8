package com.tsa.puridiom.report.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Report;
import com.tsa.puridiom.entity.ReportPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class ReportSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain report */
			ReportPK comp_id = new ReportPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Report	originalReport = (Report) incomingRequest.get("report");
			Report	report = new Report();

			comp_id.setReportTitle(originalReport.getComp_id().getReportTitle());
			report.setReportDatawindow(originalReport.getReportDatawindow());
			comp_id.setReportModule(originalReport.getComp_id().getReportModule());
			report.setReportDescription(originalReport.getReportDescription());
			report.setComp_id(comp_id);

			incomingRequest.put("report", report);

			ReportAdd addTask = new ReportAdd();
			report = (Report) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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