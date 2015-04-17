package com.tsa.puridiom.reportuser.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReportUserSetupFromReportQueue extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			ReportQueue reportQueue = (ReportQueue) incomingRequest.get("reportQueue");
			String organizationId = (String) incomingRequest.get("organizationId");
			incomingRequest.put("ReportUser_icReportQueue",(String) reportQueue.getIcReportQueue().toString());
			incomingRequest.put("ReportUser_docTitle",reportQueue.getAlias());
			incomingRequest.put("ReportUser_docFilename",incomingRequest.get("report"));
			incomingRequest.put("ReportUser_dateSent",reportQueue.getDateSent());
			incomingRequest.put("ReportUser_timeSent",reportQueue.getTimeSent());
			incomingRequest.put("ReportUser_publicView", PropertiesManager.getInstance(organizationId).getProperty("REPORT OPTIONS", "FILE PUBLIC ACCESS", ""));
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
