package com.tsa.puridiom.report.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReportRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String reportTitle = (String ) incomingRequest.get("Report_reportTitle");
			String reportModule = (String ) incomingRequest.get("Report_reportModule");

			String queryString = "from Report as Report where Report.id.reportTitle = ? and Report.id.reportModule = ? ";
			List resultList = dbs.query(queryString, new Object[] {reportTitle, reportModule, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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