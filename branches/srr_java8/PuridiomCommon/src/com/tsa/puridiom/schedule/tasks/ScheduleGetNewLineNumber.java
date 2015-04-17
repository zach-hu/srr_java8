package com.tsa.puridiom.schedule.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ScheduleGetNewLineNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String scheduleType = (String) incomingRequest.get("Schedule_scheduleType");
			String documentType = (String) incomingRequest.get("Schedule_documentType");
			String icHeaderString = (String) incomingRequest.get("Schedule_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			BigDecimal lineNumber = new BigDecimal("0");

			String queryString = "select max(Schedule.id.lineNumber) from Schedule as Schedule where " + 
				"Schedule.id.scheduleType = ? and Schedule.id.documentType = ? and Schedule.id.icHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {scheduleType, documentType, icHeader } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				if (resultList.get(0) != null)
				{
					lineNumber = (BigDecimal) resultList.get(0);
				}
			}
			result = String.valueOf(lineNumber.add(new BigDecimal(1)));
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}