/*
 * Created on September 12, 2003
 */
package com.tsagate.foundation.utility;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author kelli
 */
public class GetDaysAfter extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		int daysAfter = 0;

		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String	beginDate = "";
			String	endDate = "";

			try
			{
				if (incomingRequest.containsKey("beginDate"))
				{
					beginDate = (String) incomingRequest.get("beginDate");
					beginDate = beginDate.replace('/', '-');
					//system.out.println("beginDate = " + beginDate);
					beginDate = formatter.format(formatter.parse(beginDate));
				}
				else
				{
					endDate = Dates.today("yyyy-MM-dd", userTimeZone);
				}

				if (incomingRequest.containsKey("endDate"))
				{
					endDate = (String) incomingRequest.get("endDate");
					endDate = endDate.replace('/', '-');
					endDate = formatter.format(formatter.parse(endDate));
				}
				else
				{
					endDate = Dates.today("yyyy-MM-dd", userTimeZone);
				}
			}
			catch(ParseException pe)
			{
					pe.printStackTrace();
					throw pe;
			}

			int	yearBegin = Integer.valueOf(beginDate.substring(0,4)).intValue();
			int	monthBegin = Integer.valueOf(beginDate.substring(4,6)).intValue();
			int	dayBegin = Integer.valueOf(beginDate.substring(6,8)).intValue();
			int	yearEnd = Integer.valueOf(endDate.substring(0,4)).intValue();
			int	monthEnd = Integer.valueOf(endDate.substring(4,6)).intValue();
			int	dayEnd = Integer.valueOf(endDate.substring(6,8)).intValue();
			int	stopDays = 0;
			Calendar begin = Calendar.getInstance();
			Calendar end = Calendar.getInstance();

			if (incomingRequest.containsKey("stopDays"))
			{
				stopDays = ((Integer) incomingRequest.get("stopDays")).intValue();
			}

			begin.set(yearBegin, monthBegin, dayBegin);
			end.set(yearEnd, monthEnd, dayEnd);

			while (begin.before(end) && ((stopDays == 0) || (daysAfter < stopDays)))
			{
				begin.add(Calendar.DAY_OF_MONTH, 1);
				daysAfter++;
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			return new Integer(daysAfter);
		}
	}

}
