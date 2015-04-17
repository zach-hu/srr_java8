package com.tsa.puridiom.budgetcenter.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.Map;

public class BudgetCenterAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		String userId = (String) incomingRequest.get("userId");
		BigDecimal tranIc = new BigDecimal(ukg.getUniqueKey().toString()) ;

		try
		{
			BudgetCenter budgetCenter = (BudgetCenter)incomingRequest.get("budgetCenter");
			if (budgetCenter == null)
			{
				throw new Exception ("BudgetCenter was not found.");
			}

			budgetCenter.setLastChangeBy(userId) ;
			budgetCenter.setLastChangeDate(new java.util.Date()) ;
			budgetCenter.setLastTranId(tranIc) ;

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(budgetCenter);

			result = budgetCenter;
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