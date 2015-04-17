package com.tsa.puridiom.budgetdrawer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BudgetDrawerAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BudgetDrawer budgetDrawer = (BudgetDrawer)incomingRequest.get("budgetDrawer");
			if (budgetDrawer == null)
			{
				throw new Exception ("BudgetDrawer was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(budgetDrawer);

			result = budgetDrawer;
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