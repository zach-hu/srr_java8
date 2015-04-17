package com.tsa.puridiom.budgetaudit.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.util.Map;

public class BudgetAuditAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BudgetAudit budgetAudit = (BudgetAudit)incomingRequest.get("budgetAudit");
			if (budgetAudit == null)
			{
				throw new Exception ("BudgetAudit was not found.");
			}

			Log.info(this, "Adding Audit: " +budgetAudit );

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(budgetAudit);

			result = budgetAudit;
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