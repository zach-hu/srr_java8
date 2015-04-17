package com.tsa.puridiom.reportqueue.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;

public class ReportQueueSetNextRunOneTime extends ReportQueueSetNextRunFrequency
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReportQueue reportQueue = (ReportQueue)incomingRequest.get("reportQueue");
			if (reportQueue == null)
			{
				throw new Exception ("ReportQueue was not found.");
			}
			//Do Nothing. This report will be run only one time.
			result = reportQueue;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	public static void main(String[] args)
	{
		try
		{
			System.out.println("start");
			ReportQueueSetNextRunOneTime frequency = new ReportQueueSetNextRunOneTime();
			Map incomingRequest = new HashMap();
			ReportQueue reportQueue = new ReportQueue();
			reportQueue.setNextRun(Dates.getDate(""));
			incomingRequest.put("reportQueue", reportQueue);
			reportQueue = (ReportQueue)frequency.executeTask(incomingRequest);
			System.out.println("nextrun: " + reportQueue.getNextRun().toString());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}