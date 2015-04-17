package com.tsa.puridiom.apprule.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AppRuleRetrieveWhere extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String queryString = "Select appRule from AppRule as appRule ";
			String whereClause = (String) incomingRequest.get("appRuleWhereClause");
			if (!HiltonUtility.isEmpty(whereClause)) {
				queryString = queryString + "WHERE " + whereClause;
			}

            List userRuleList = dbs.query(queryString) ;

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