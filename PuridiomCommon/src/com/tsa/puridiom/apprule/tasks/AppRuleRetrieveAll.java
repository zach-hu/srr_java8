package com.tsa.puridiom.apprule.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AppRuleRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String queryString = "Select appRule from AppRule as appRule, UserProfile as userProfile where appRule.id.userId = userProfile.userId and userProfile.status <> '03'";
			List userRuleList = dbs.query(queryString) ;
			
			String queryString2 = "Select appRule from AppRule as appRule where appRule.id.userId LIKE '@%'";
			List poolRuleList = dbs.query(queryString2) ;
			
			userRuleList.addAll(poolRuleList);
			
			ret = userRuleList;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Approval Rules List could not eb Loaded. " + e.getMessage(), e);
		}
		return ret;
	}

}