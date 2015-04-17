/*
 * Created on Nov 9, 2004
 */
package com.tsa.puridiom.schedule.tasks;

import com.tsa.puridiom.entity.Schedule;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class ScheduleSaveasList extends Task
{
	
	/**
	 * executeTask
	 * <p>This method takes a Schedule List from incoming request(scheduleList)</p>
	 * <p> and calls schedule-saveas-by-id process for each.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List scheduleList = (List)incomingRequest.get("scheduleList");
		
		for (int row = 0; row < scheduleList.size(); row++)
		{
		    Schedule schedule = (Schedule) scheduleList.get(row);

			incomingRequest.put("schedule", schedule);
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("schedule-saveas-by-id.xml") ;
			
			process.executeProcess(incomingRequest);

			if (process.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("ScheduleSaveAs process failed (schedule-saveas-by-id.xml).");
			}			
			
			schedule = (Schedule) incomingRequest.get("schedule");
			scheduleList.set(row, schedule);

			this.setStatus(Status.SUCCEEDED);
		}
		if(scheduleList.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return scheduleList;
	}

}
