package com.tsa.puridiom.reportqueue.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ReportQueueSetNextRunFrequency extends Task
{
	public Object  executeTask (Object object) throws Exception
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
			result = this.setNextRun(reportQueue, this.getCalendarField(), this.getFrequency());
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	/**
	 * Method to overwrite in specific setFrequency tasks.
	 * used in combination with getCalendarField().
	 * @return days/months/year to add
	 * 1 - daily
	 * 7 - weekly
	 * 1 - month
	 * 3 - month
	 * 1 - year
	 */
	public int getFrequency()
	{
		return 1;
	}

	/**
	 * Method to overwrite in specific setFrequency tasks.
	 * used in combination with getFrequency().
	 * @return value of frequency
	 * Calendar.DATE for day
	 * Calendar.DATE for week
	 * Calendar.MONTH for month
	 * Calendar.MONTH for quarter
	 * Calendar.YEAR for year
	 */
	public int getCalendarField()
	{
		return Calendar.DATE;
	}

	/**
	 * @param reportQueue
	 * @param calendarField DATE FOR DAY/MONTH/YEAR
	 * @param frequency 1-DAILY/7-WEEKLY/1-MONTH/1-YEAR
	 * @return
	 */
	public ReportQueue setNextRun(ReportQueue reportQueue, int calendarField, int frequency)
	{
		Date current = reportQueue.getNextRun();
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.add(calendarField, frequency);
		reportQueue.setNextRun(cal.getTime());

		return reportQueue;
	}

}