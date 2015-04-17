/**
 * 
 */
package com.tsa.puridiom.budget.tasks;

import java.util.Map;

import com.puridiom.service.budget.BudgetReturn;
import com.puridiom.service.budget.BudgetServiceJob;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetServiceUnlock extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

			BudgetReturn budgetReturn = (BudgetReturn) incomingRequest.get("budgetReturn");
			String[][] budgetDistinctKeys = (String[][]) incomingRequest.get("budgetDistinctKeys");
			String organizationId = (String) incomingRequest.get("organizationId");

			budgetServiceJob.unlockBudget(budgetDistinctKeys, budgetReturn.getLockingToken(), organizationId);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetServiceGet error " + e.getMessage());

			throw e;
		}

		return result;
	}
}
