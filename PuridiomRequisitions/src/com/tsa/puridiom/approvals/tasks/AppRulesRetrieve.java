package com.tsa.puridiom.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AppRulesRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString =   "FROM AppRule rules, UserProfile u " +
			 "WHERE (rules.id.userId = u.userId (+)) and (u.approver = 'Y') " +   
			 "ORDER BY rules.id.userId ASC, rules.id.udf1Code ASC," +   
            "rules.id.udf2Code ASC, rules.id.udf3Code ASC, rules.id.udf4Code ASC," +   
            "rules.id.udf5Code ASC, rules.id.udf6Code ASC, rules.id.udf7Code ASC," +   
            "rules.udf8Code ASC, rules.udf9Code ASC, rules.udf10Code ASC" ;
			
			result = dbs.query(queryString) ;
					
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