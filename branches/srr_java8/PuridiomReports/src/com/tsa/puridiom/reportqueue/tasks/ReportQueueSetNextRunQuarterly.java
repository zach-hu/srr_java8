package com.tsa.puridiom.reportqueue.tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.utility.Dates;

public class ReportQueueSetNextRunQuarterly extends ReportQueueSetNextRunFrequency
{
	public int getFrequency()
	{
		return 3;
	}

	public int getCalendarField()
	{
		return Calendar.MONTH;
	}

	public static void main(String[] args)
	{
		try
		{
			System.out.println("start");
			ReportQueueSetNextRunQuarterly frequency = new ReportQueueSetNextRunQuarterly();
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