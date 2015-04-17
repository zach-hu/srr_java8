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
public class BudgetServiceGet extends Task
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

			String[][] budgetDistinctKeys = (String[][]) incomingRequest.get("budgetDistinctKeys");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");

			BudgetReturn budgetReturn = (BudgetReturn) budgetServiceJob.getBudget(true, budgetDistinctKeys, userId, organizationId);
			
			result = budgetReturn;

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
