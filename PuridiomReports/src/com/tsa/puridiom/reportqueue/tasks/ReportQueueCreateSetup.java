package com.tsa.puridiom.reportqueue.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class ReportQueueCreateSetup extends Task
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
			String userId = (String)incomingRequest.get("userId");
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy/MM/dd");
			String	today = formatter.format(new Date());
			String now = Dates.getTimeString(new Date().toString());

			incomingRequest.put("ReportQueue_icReportQueue",ukg.getUniqueKey().toString());
			incomingRequest.put("ReportQueue_owner",userId);
			incomingRequest.put("ReportQueue_dateAdded", today);
			incomingRequest.put("ReportQueue_timeAdded",now);

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
