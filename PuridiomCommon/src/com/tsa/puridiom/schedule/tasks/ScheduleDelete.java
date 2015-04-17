package com.tsa.puridiom.schedule.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.Schedule;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class ScheduleDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Schedule schedule = (Schedule)incomingRequest.get("schedule");
		if(schedule == null)
		{
			schedule = new Schedule();
		}
		ScheduleSetValuesPK setValues = new ScheduleSetValuesPK();
		setValues.setValues(incomingRequest, schedule);
		dbs.delete(schedule);
		this.setStatus(dbs.getStatus()) ;
		return schedule ;
	}

}