package com.tsa.puridiom.reportuser.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReportUserGetSendToList extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		List sendToList = new ArrayList();
		try
		{
			Map incomingRequest = (Map) object;
			ReportQueue reportQueue = (ReportQueue) incomingRequest.get("reportQueue");
			String sendTo = reportQueue.getSendTo();
			String sendToArray[] = sendTo.split(";");
			for (int i=0; i<sendToArray.length; i++) {
				sendToList.add(sendToArray[i]);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return sendToList;
	}
}
