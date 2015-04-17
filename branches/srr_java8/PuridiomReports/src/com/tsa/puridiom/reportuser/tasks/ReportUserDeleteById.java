package com.tsa.puridiom.reportuser.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReportUser;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class ReportUserDeleteById extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ReportUser reportUser = (ReportUser)incomingRequest.get("reportUser");
		if(reportUser == null)
		{
			reportUser = new ReportUser();
		}
		ReportUserSetValuesPK setValues = new ReportUserSetValuesPK();
		setValues.setValues(incomingRequest, reportUser);
		dbs.delete(reportUser);
		this.setStatus(dbs.getStatus()) ;
		return reportUser;
	}

}